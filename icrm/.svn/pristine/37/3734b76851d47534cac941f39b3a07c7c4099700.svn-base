package com.zjhcsoft.rpc.tcpserver.nio;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.zjhcsoft.rpc.handler.ServerHandler;
import com.zjhcsoft.rpc.tcpserver.TcpServer;
import com.zjhcsoft.rpc.tcpserver.TcpServerTask;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class DefaultTcpNettyNioServer implements TcpServer {

	private Log LOG = LogFactory.getLog(DefaultTcpNettyNioServer.class);
//	public static final ChannelGroup ALL_CHANNELS=new DefaultChannelGroup("netty_server");
	
	private int port;

	private boolean isStop = false;

	private int minIdleNum;

	private int maxActiveNum;

	private int soTimeOut;

	private ServerHandler handler;

	public ServerHandler getHandler() {
		return this.handler;
	}

	public int getMaxActiveNum() {
		return this.maxActiveNum;
	}

	public int getMinIdleNum() {
		return this.minIdleNum;
	}

	public int getPort() {
		return this.port;
	}

	public int getSoTimeout() {
		return this.soTimeOut;
	}

	public boolean isRunning() {
		return !this.isStop;
	}

 	
 	
 
 
	public void run() {
		 
 			NioServerSocketChannelFactory channelFactory = new NioServerSocketChannelFactory(
					Executors.newCachedThreadPool(),
					Executors.newCachedThreadPool());
			ServerBootstrap bootstrap = new ServerBootstrap(channelFactory);
			bootstrap.setOption("reuseAddress", true);

			// Options for its children
			bootstrap.setOption("child.tcpNoDelay", true);
			/**
			 * 8K
			 */
		  //  bootstrap.setOption("child.receiveBufferSize", 1024*1024*8);
		  //  System.out.println("child.receiveBufferSize="+bootstrap.getOption("child.receiveBufferSize"));
 			NettyServerChannelPipelineFactory channelPipelineFactory = new NettyServerChannelPipelineFactory(this.getHandler(),this.getPort());
			bootstrap.setPipelineFactory(channelPipelineFactory);
			Channel channel=bootstrap.bind(new InetSocketAddress(port));
			
//			ALL_CHANNELS.add(channel);
//			ChannelGroupFuture future=ALL_CHANNELS.close();
//			future.awaitUninterruptibly();
//			channelFactory.releaseExternalResources();
		 
		LOG.debug("NettyNioServer start on port " + port);
	}

	public void setHandler(ServerHandler handler) {
		this.handler = handler;
	}

	public void setMaxActiveNum(int maxActiveNum) {
		this.maxActiveNum = maxActiveNum;
	}

	public void setMinIdleNum(int minIdleNum) {
		this.minIdleNum = minIdleNum;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setSoTimeout(int soTimeOut) {
		this.soTimeOut = soTimeOut;
	}

	public void start() {
		this.isStop = false;
 		TcpServerTask task = new TcpServerTask(this);
		task.start();
 		 
	}

	public void stop() {
		this.isStop = true;
	}

	private int backlog;

	//@Override
	public int getBacklog() {
		if (this.backlog <= 0) {
			this.backlog = -1;
		}
		return this.backlog;
	}

	//@Override
	public void setBacklog(int backlog) {
		this.backlog = backlog;
	}

}
