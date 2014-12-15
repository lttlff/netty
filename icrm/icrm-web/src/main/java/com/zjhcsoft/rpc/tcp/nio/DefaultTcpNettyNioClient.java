package com.zjhcsoft.rpc.tcp.nio;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import com.zjhcsoft.rpc.cfg.domain.DeployDomain;
import com.zjhcsoft.rpc.context.impl.RequestContext;
import com.zjhcsoft.rpc.context.impl.ResponseContext;
import com.zjhcsoft.rpc.monitor.DeployMonitor;
import com.zjhcsoft.rpc.monitor.DeployStatus;
import com.zjhcsoft.rpc.monitor.SocketHelper;
import com.zjhcsoft.rpc.tcp.TcpClient;

public class DefaultTcpNettyNioClient implements TcpClient {

	private static Log LOG = LogFactory.getLog(DefaultTcpNettyNioClient.class);

	/**
	 * build queue for every host_port
	 */
	
	private static Map<String, LinkedBlockingQueue<RequestContext>> queueMap = new ConcurrentHashMap<String, LinkedBlockingQueue<RequestContext>>();// LinkedBlockingQueue

	private static Map<String, NettyNioClientTask> taskMap = new ConcurrentHashMap<String, NettyNioClientTask>();

	private static Map<String, ConcurrentHashMap<String,ResponseContext>> resultMap = new HashMap<String, ConcurrentHashMap<String,ResponseContext>>();

	/**
	 * keep up timeout request
	 */
	private static Map<String, ConcurrentHashMap<String,ResponseContext>> resultTimeoutMap = new HashMap<String, ConcurrentHashMap<String,ResponseContext>>();

	private static ReentrantLock LOCK = new ReentrantLock();
	
	public Object call(RequestContext rpc) {
		if (rpc == null || rpc.getDeployDomain().getHost() == null || rpc.getDeployDomain().getPort() == 0) {
			throw new RuntimeException("参数不全");
		}

		rpc.setCallStartTimeMillis(System.currentTimeMillis());
		/**
		 * generate request invocation id
		 */
		if (rpc.getRequestId() == null || "".equalsIgnoreCase(rpc.getRequestId())) {
			rpc.setRequestId("id_" + System.nanoTime() + "_" + getRandomString(6));
		}
		/**
		 * judge whether build a queue
		 */
		LinkedBlockingQueue<RequestContext> queue = null;
		if (!queueMap.containsKey(rpc.getDeployDomain().getHost() + "_" + rpc.getDeployDomain().getPort())) {
			try {
				LOCK.lock();
				if (!queueMap.containsKey(rpc.getDeployDomain().getHost() + "_" + rpc.getDeployDomain().getPort())) {
					queue = new LinkedBlockingQueue<RequestContext>();
					queueMap.put(rpc.getDeployDomain().getHost() + "_" + rpc.getDeployDomain().getPort(), queue);
				}
			} finally {
				LOCK.unlock();
			}
		} else {
			queue = queueMap.get(rpc.getDeployDomain().getHost() + "_" + rpc.getDeployDomain().getPort());
		}
		int queueSize = queue.size();
		/**
		 * judge queue whether very large,if so,direct back,in order to no crash
		 */
		if (queueSize >= 2000) {
			throw new RuntimeException("host[" + rpc.getDeployDomain().getHost() + "] port["
					+ rpc.getDeployDomain().getPort() + "],too many queue size[" + queueSize
					+ "]");
		}
		/**
		 * judge whether build a task queue
		 */
		if (!taskMap.containsKey(rpc.getDeployDomain().getHost() + "_" + rpc.getDeployDomain().getPort())) {
			try {
				LOCK.lock();
				if (!taskMap.containsKey(rpc.getDeployDomain().getHost() + "_" + rpc.getDeployDomain().getPort())) {
					NettyNioClientTask task = new NettyNioClientTask(
							rpc.getDeployDomain().getHost(), rpc.getDeployDomain().getPort(), queue, this,rpc.getDeployDomain());
					taskMap.put(rpc.getDeployDomain().getHost() + "_" + rpc.getDeployDomain().getPort(), task);
					task.start();
				}
			} finally {
				LOCK.unlock();
			}
		}
		/**
		 * judge whether build a resultMap and resultTimeoutMap
		 */
		if (!resultMap.containsKey(rpc.getDeployDomain().getHost() + "_" + rpc.getDeployDomain().getPort())) {
			try {
				LOCK.lock();
				if (!resultMap.containsKey(rpc.getDeployDomain().getHost() + "_" + rpc.getDeployDomain().getPort())) {
					resultMap.put(rpc.getDeployDomain().getHost() + "_" + rpc.getDeployDomain().getPort(),
							new ConcurrentHashMap());
					resultTimeoutMap.put(rpc.getDeployDomain().getHost() + "_" + rpc.getDeployDomain().getPort(),
							new ConcurrentHashMap());
				}
			} finally {
				LOCK.unlock();
			}
		}

		/**
		 * judge whether no data come back,if so ,direct back,such as monitor,log
		 */
		//no data come back ,thread direct back,also no need to check connect status
		if (rpc.isAsync()) {
			if (getServerConnectionStatus(rpc.getDeployDomain().getHost(), rpc.getDeployDomain().getPort())) {
				queue.offer(rpc);//add request then direct back
			}
			return null;
		}
		//in other,need data back ,so check channel connection status first
		//首个进入的请求会先去创建通道，并在连接还未成功，且该部署服务还未被定义为死亡的情况下锁定队列标识，
		//有新请求进入会抛出这里的异常,如果一直没连接成功，队列锁定之前进入的会报超时
		//直到重复连接失败次数超过死亡定义，请求会寻找新的通道交互
		if (!getServerConnectionStatus(rpc.getDeployDomain().getHost(), rpc.getDeployDomain().getPort())) {
			/**
			 * if channel exception,mark at once,in order to no crash when resume ,
			 * there are many requests in queue
			 * mark channel status only in doconnection method in task thread
			 */
			throw new RuntimeException("server[" + rpc.getDeployDomain().getHost() + ":"
					+ rpc.getDeployDomain().getPort() + "] still connection error");
		}
		/**
		 * real request deal begin
		 */
//		long startTime = System.currentTimeMillis();
//		int sleepTimeMillis = 1;
		Map hostPortMap = resultMap.get(rpc.getDeployDomain().getHost() + "_" + rpc.getDeployDomain().getPort());//get resultMap
		Map hostPortTimeOutMap = (Map) resultTimeoutMap.get(rpc.getDeployDomain().getHost() + "_"+ rpc.getDeployDomain().getPort());//get resultTimeoutMap

		ReentrantLock waitLock = new ReentrantLock();//lock obj
		Condition waitCondition = waitLock.newCondition();//timeout contorl
		
		
		/***************调试用*****************/
//		if(testNum<=20){
//			testNum++;
//		try {
//			Thread.currentThread().sleep(1 * 1000);//放数据放的慢点，等异常部署服务被识别出，看后续的数据是否都放到了活的部署上
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}}
		/***************调试用*****************/
		
		
		Map sessionMap = new ConcurrentHashMap();
		sessionMap.put("waitLock", waitLock);
		sessionMap.put("waitCondition", waitCondition);
		hostPortMap.put(rpc.getRequestId(), sessionMap);//put request_result in resultMap first
		waitLock.lock();//lock
		long startWaitTime = System.currentTimeMillis();
		try {
			/**
			 * put request_result in resultMap,avoid invocation go back very soon,then can't get it.
			 */
			queue.offer(rpc);
//			waitCondition.await(this.getSoTimeout(), TimeUnit.MILLISECONDS);//timeout control
//			System.out.println("**************"+rpc.getDeployDomain().getAppDefDomain().getSoTimeOut());
			waitCondition.await(rpc.getDeployDomain().getAppDefDomain().getSoTimeOut(), TimeUnit.SECONDS);//timeout control
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			waitLock.unlock();//unlock
		}
		LOG.debug("session[" + rpc.getRequestId() + "] wait time["
				+ System.currentTimeMillis() + "]["
				+ (System.currentTimeMillis() - startWaitTime) + "]");
		if (!((Map) hostPortMap.get(rpc.getRequestId())).containsKey("value")) {
			/**
			 * if timeout,move request from resultMap to resultTimeOutMap
			 */
			hostPortTimeOutMap.put(rpc.getRequestId(), rpc);
			hostPortMap.remove(rpc.getRequestId());//remove from resultMap
			LOG.error("timeout for session[" + rpc.getRequestId() + "]");

			throw new RuntimeException("host[" + rpc.getDeployDomain().getHost() + "] port["
					+ rpc.getDeployDomain().getPort() + "] timeout,rpc value:"+rpc.getRequestId());
		}
		ResponseContext response2 = (ResponseContext) (((Map) hostPortMap.get(rpc
				.getRequestId())).get("value"));//success gain data from resultmap,remove from resultMap
		hostPortMap.remove(rpc.getRequestId());

		if (response2.getResult() != null
				&& response2.getResult() instanceof Throwable) {
			throw new RuntimeException((Throwable) response2.getResult());//如果获取到的是异常就抛出异常
		}
		//这段重要，需要检查下
//		System.out.println("come back============"+((String)response2.getResult()));//返回值不一定是hashmap
		
//		rpc.setCallStartTimeMillis(System.currentTimeMillis());
//		response2.getCallEndTimeMillis(callEndTimeMillis);
		return response2;
	}

//	public Object call(String host, int port, String data_protocol, Object obj) {
//		RequestContext param = new RequestContext();
//		param.getDeployDomain().setHost(host);
//		param.getDeployDomain().setPort(port);
//		param.getDeployDomain().setData_protocol(data_protocol);//set data protocol,use when read and write,code is four byte in channel stream
//		ResponseContext result = (ResponseContext) call(param);
//		return result.getResult();
//	}

	private String getRandomString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}
	//invocation by NettyClientHandler,when data come back,NettyServerHandler invocation is to do real service by reflect
	public void writeBackResult(String session, ResponseContext response) {
		response.setCallEndTimeMillis(System.currentTimeMillis());
		System.out.println("***********getPort:*"+response.getPort()+" getHost:"+response.getHost()+" getSessionId:"+response.getRequestId());
		Map hostPortTimeOutMap = (Map) resultTimeoutMap.get(response.getHost() + "_"
				+ response.getPort());//get resultTimeoutMap
		Map hostPortMap = (Map) resultMap.get(response.getHost() + "_"
				+ response.getPort());//get resultMap
		LOG.debug("client writeBackResult[" + System.nanoTime() + "]:"+ response.getRequestId());
		if (hostPortMap.containsKey(response.getRequestId())
				&& !hostPortTimeOutMap.containsKey(response.getRequestId())) {//if request_result still in resultMap
			((Map) hostPortMap.get(response.getRequestId())).put("value", response);//put data in resultMap
			ReentrantLock waitLock = (ReentrantLock) ((Map) hostPortMap.get(response
					.getRequestId())).get("waitLock");
			Condition waitCondition = (Condition) ((Map) hostPortMap.get(response
					.getRequestId())).get("waitCondition");
			/**
			 * must gain again Lock，then ability to signal condition
			 */
			waitLock.lock();
			try {
				waitCondition.signal();
			} finally {
				waitLock.unlock();
			}
		} else {
			if (hostPortTimeOutMap.size() >= 2000) {//if hostPortTimeOutMap more than 2000,clear
				LOCK.lock();
				try {
					if (hostPortTimeOutMap.size() >= 2000) {
						/**
						 * 超过2000条，先清空
						 */
						hostPortTimeOutMap.clear();
					}
				} finally {
					LOCK.unlock();
				}
			}
			hostPortTimeOutMap.put(session, response);//put in timeout request data in hostPortTimeOutMap
		}
		LOG.debug("writeBackResult session[" + session + "]");
	}

	private Map paramMap;

	public Map getParamMap() {
		if (this.paramMap == null) {
			this.paramMap = new HashMap();
		}
		return paramMap;
	}

	public void setParamMap(Map paramMap) {
		if (paramMap != null) {
			this.paramMap = paramMap;
		}
	}
//重连之前先标识连接状态为false
	protected static void markServerConnectionError(String host, int port) {
		Map hostPortMap = resultMap.get(host + "_" + port);
		if (hostPortMap != null) {
			hostPortMap.put("NIOServerConnectionStatus", false);
		}
	}

	protected static void markServerConnectionResume(String host, int port) {
		Map hostPortMap = resultMap.get(host + "_" + port);
		if (hostPortMap != null) {
			hostPortMap.put("NIOServerConnectionStatus", true);
		}
	}

	protected static boolean getServerConnectionStatus(String host, int port) {
		Map hostPortMap = resultMap.get(host + "_" + port);
		if (hostPortMap != null) {
			Boolean status = (Boolean) hostPortMap
					.get("NIOServerConnectionStatus");
			if (status == null || status == true) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

}

class NettyNioClientTask extends Thread {

	private static Log LOG = LogFactory.getLog(NettyNioClientTask.class);

	private String taskId;

	private String host;

	private int port;

	private TcpClient client;

	private LinkedBlockingQueue<RequestContext> queue;

	private ClientBootstrap bootstrap;

	private ChannelFuture future;
//	private ChannelFactory factory;

	private Channel channel;

	private boolean connectionSuccess = false;
	
	private DeployDomain deployDomain;
	
	private AtomicLong taskExecuteNum = new AtomicLong(0);

	public NettyNioClientTask(String host, int port, LinkedBlockingQueue<RequestContext> queue,
			TcpClient client,DeployDomain deployDomain) {
		this.host = host;
		this.port = port;
		this.client = client;
		this.queue = queue;
		this.deployDomain=deployDomain;
	}

	public void run() {
			while (true) {
					if ((channel==null)||(!channel.isConnected())){
						System.out.println("========================通道连接异常，开始重新创建.....");
//						LOG.error("Socket测试连接Host:"+host+" port:"+port+"  测试连接成功.......");
						doConnection();
					}
					
					Object obj=null;
					try {
						obj = queue.poll(1000, TimeUnit.MILLISECONDS);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					if (obj != null) {
//							try {
//								System.out.println("NettyNioClientTask host:"+host+" port:"+port+" have write "+taskExecuteNum.incrementAndGet()+" obj!!");
//								System.out.println("===========*********写数据延时，测试写动作异常*********================");
//								Thread.sleep(5 * 1000);//放数据放的慢点，等异常部署服务被识别出，看后续的数据是否都放到了活的部署上
//							} catch (InterruptedException e) {
//								e.printStackTrace();
//							}
							
							channel.write(obj);//写数据失败，没有抛出异常
							System.out.println("NettyNioClientTask host:"+host+" port:"+port+" have write "+taskExecuteNum.incrementAndGet()+" obj!!");
							LOG.debug("channel state [isConnected:"
									+ channel.isConnected() + "],[isBound:"
									+ channel.isBound() + "],[isOpen:"
									+ channel.isOpen() + "],[isReadable:"
									+ channel.isReadable() + "],[isWritable:"
									+ channel.isWritable() + "]");

						LOG.debug("client channelConnected  write end["
								+ System.currentTimeMillis() + "]:" + obj);
					} else {
//						LOG.debug("client channelConnected  write end,obj is null");
					}
		}
	}
//before connection sleep 1 seconds,after connection sleep 4 seconds
	//在连接成功之前,好像其他线程都会停滞，感觉并没有开启多线程
	//先进入队列的会检测连接有没创建，如果还没创建成功,就去创建，一直没成功就会导致报超时，
	//后想进入的，因为连接没创建成功,连接会被间隔几秒死循环的去创建直到成功为止，这过程中队列标致已经被锁定，会报still connection error(连接还没创建好，还是错误的),
	//等多次连接次数超过死亡定义次数时会被列入死亡名单，新的请求就不会进入,still connection error的报告将结束,请求将寻找新的通道交互
	private void doConnection() {
		connectionSuccess = false;//reconnection mark status false ,whether sense
		LOG.error("[" + this.getHost() + ":" + this.getPort()+ "] reconnection start");
		DefaultTcpNettyNioClient.markServerConnectionError(this.getHost(),//reconnection mark status false 
				this.getPort());
		while (!connectionSuccess) {
			try {
				Thread.sleep(1 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (channel != null) {
					try {
						System.out.println("====================通道连接发生异常，开始释放资源**********===============");
						channel.close();
						future.getChannel().getCloseFuture().awaitUninterruptibly();
						bootstrap.releaseExternalResources();
						channel=null;//channel被关闭以后对象需要释放，否则一直会存活，导致死循环的资源释放
					} catch (Throwable e) {
						e.printStackTrace();
					}
			}
			
			try {
				//如果没有这段控制，直接对死亡的部署服务进行直连，下面的重建和销毁的代价是很大的
				if (SocketHelper.isOpen(deployDomain.getHost(),deployDomain.getPort())) {//这段将屏蔽NettyClientHandler中定义的在连接失败的情况下的异常抛出
					System.out.println("====================socket测试连接成功，开始创建通道===============");
					bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(
							Executors.newCachedThreadPool(),
							Executors.newCachedThreadPool()));
				// Set up the pipeline factory.传入client在收到数据的时候写入
				ChannelPipelineFactory factory = new NettyClientChannelPipelineFactory(this.client);
				bootstrap.setPipelineFactory(factory);

				long startTime = System.currentTimeMillis();
				bootstrap.setOption("child.tcpNoDelay", true);
				// bootstrap.setOption("child.keepAlive", true);
				// bootstrap.setOption("child.receiveBufferSize", 1024*1024*8);
				// Start the connection attempt.
				future = bootstrap.connect(new InetSocketAddress(host, port));
				channel = future.awaitUninterruptibly().getChannel();
				if (!future.isSuccess()) {
					LOG.error("[" + this.getHost() + ":" + this.getPort()+ "] reconnection ,error");
					future.getCause().printStackTrace();
					System.out.println("====================通道重新创建，但连接不成功===============");
					DeployMonitor.addDeployDomainNoDead(deployDomain);//连接失败会进入这里
				} else {
					DefaultTcpNettyNioClient.markServerConnectionResume(
							this.getHost(), this.getPort());
					LOG.error("[" + this.getHost() + ":" + this.getPort()
							+ "] reconnection  success");
					connectionSuccess = true;
					System.out.println("====================通道重新创建，连接成功===============");
					DeployMonitor.socketConnectedSuccess(DeployMonitor.getErrorDeployMap(), deployDomain);//如果连接成功，就从异常池里面清除
					LOG.debug("connection success["
							+ System.currentTimeMillis() + "]["
							+ (System.currentTimeMillis() - startTime) + "]...");
				}
//				future.getChannel().getCloseFuture().awaitUninterruptibly();//这两段新增
//				bootstrap.releaseExternalResources();
			}else{
				DeployMonitor.addDeployDomainNoDead(deployDomain);
			}//这里只是增加检测的信息，不一定是异常部署,如果是新增异常部署，则接口里面已经新增了检测信息
				DeployStatus deployStatus=DeployMonitor.getErrorDeployStatus(deployDomain);
				if(deployStatus!=null){
				deployStatus.getDetectedTimes().incrementAndGet();
				deployStatus.setLastDetectedDate(new Date());// 当前检测时间,次数设置
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(2 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public TcpClient getClient() {
		return client;
	}

	public void setClient(TcpClient client) {
		this.client = client;
	}

}
