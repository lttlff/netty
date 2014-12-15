package com.zjhcsoft.rpc.monitor.job;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjhcsoft.rpc.cfg.domain.DeployDomain;
import com.zjhcsoft.rpc.monitor.DeployMonitor;
import com.zjhcsoft.rpc.monitor.DeployStatus;
import com.zjhcsoft.rpc.monitor.SocketHelper;

public class DeployDetectedJob implements Runnable {

	private ScheduledExecutorService scheduler;
	private Map<String, DeployStatus> errorDeployMap;
	private Long interval_time;
	private int i=0;

	protected static Log LOG = LogFactory.getLog(DeployDetectedJob.class);

	public DeployDetectedJob(ScheduledExecutorService scheduler,
			Map<String, DeployStatus> errorDeployMap, Long interval_time) {
		this.scheduler = scheduler;
		this.errorDeployMap = errorDeployMap;
		this.interval_time = interval_time;
		if (this.interval_time == null || this.interval_time.longValue() == 0) {
			this.interval_time = new Long(15);
		}
	}

	public void run() {
//		i++;
//		if(i==5){
//			scheduler.shutdown();
//		}
		LOG.debug("DeployDetectedJob start.....");
		System.out.println("DeployDetectedJob start.....");
		try {
			Iterator<String> iter = errorDeployMap.keySet().iterator();
			DeployDomain deployDomain=null;
			DeployStatus deployStatus=null;
			while (iter.hasNext()) {
				Object key = iter.next();
				deployStatus = (DeployStatus) errorDeployMap.get(key);
				deployDomain=deployStatus.getDeployDomain();
				if (deployDomain.getHost() != null&& !"".equalsIgnoreCase(deployDomain.getHost().trim()) && deployDomain.getPort() > 0) {
					deployStatus.getDetectedTimes().incrementAndGet();
					deployStatus.setLastDetectedDate(new Date());// 检测数据设置
					if (SocketHelper.isOpen(deployDomain.getHost(),deployDomain.getPort())) {// 测试如果连通
						DeployMonitor.socketConnectedSuccess(errorDeployMap, deployDomain);//尽管这里已经检测成功，但也许channel通道还没有建立，这种情况下连接还是会错误,应该在通道建立完成以后再调用这个接口socketConnectedSuccess
					} else {// 测试如果不通过
						System.out.println("扫描===连接失败==");
						DeployMonitor.addDeployDomainNoDead(deployDomain);
					}
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} 
//		finally {
//			if (this.scheduler.isShutdown()) {
////				this.scheduler.schedule(this, this.interval_time.longValue(),TimeUnit.SECONDS);
//				scheduler.scheduleAtFixedRate(this, 5, 5, TimeUnit.SECONDS);
//				System.out.println("isShutdown......");
//			}
//		}
	}
}
