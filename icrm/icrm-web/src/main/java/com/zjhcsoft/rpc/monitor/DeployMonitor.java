package com.zjhcsoft.rpc.monitor;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjhcsoft.rpc.cfg.domain.DeployDomain;
 
public class DeployMonitor {
	
	private static final Map<String, DeployStatus> ErrorDeployMap = new ConcurrentHashMap<String, DeployStatus>();
////	private static JobFactory jobFactory=new JobFactory("deploy-monitor-scheduler-", true);
//	private static ScheduledExecutorService deployDetectedScheduler = Executors.newScheduledThreadPool(1);
	private static final int EXCEPTIONNUM_TO_DEAD=3;
	private static Log LOG = LogFactory.getLog(DeployMonitor.class);
	
//	static{
//		/**
//		 * 延迟5秒启动，每隔3秒检查是否恢复
//		 */
//		final Runnable deployDetectedJob = new DeployDetectedJob(deployDetectedScheduler,ErrorDeployMap,new Long(5));//秒,不需要放入deployDetectedScheduler对象
////		jobFactory.newThread(deployDetectedJob);
//		deployDetectedScheduler.scheduleAtFixedRate(deployDetectedJob, 5, 2, TimeUnit.SECONDS);
////		deployDetectedScheduler.schedule(deployDetectedJob,new Long(5), TimeUnit.SECONDS);
//	}
	
	public static Map<String, DeployStatus> getErrorDeployMap() {
		return ErrorDeployMap;
	}
	public static void socketConnectedSuccess(Map<String, DeployStatus> errorDeployMap, DeployDomain deployDomain) {
		if (containsErrorDeployDomain(deployDomain)) {
			System.out.println("============连接成功，从异常库清除===============");
			errorDeployMap.remove(deployDomain.getHost() + "_"
					+ deployDomain.getPort());
			if (deployDomain.isDead()) {// 如果已经死亡
				deployDomain.setDead(false);// 是否上面的新增可以写在这个接口里面
			}
		}
	}
	
	private static boolean containsErrorDeployDomain(DeployDomain deployDomain) {
		return ErrorDeployMap.containsKey(deployDomain.getHost() + "_" + deployDomain.getPort());
	}
	
	public static void addDeployDomainNoDead(DeployDomain deployDomain) {
		if (!deployDomain.isDead()) {
			DeployMonitor.addErrorDeployDomain(deployDomain);
		} else {
			DeployMonitor.getErrorDeployStatus(deployDomain).addExceptionDate(
					new Date());
		}
	}

	public static void addErrorDeployDomain(DeployDomain deployDomain) {
		if(!containsErrorDeployDomain(deployDomain)){
			LOG.error("app name[" + deployDomain.getAppDefDomain().getAppName() + "],deploy host["
					+ deployDomain.getHost() + "] port[" + deployDomain.getPort()
					+ "] error");
			DeployStatus status = new DeployStatus(deployDomain);
			ErrorDeployMap.put(deployDomain.getHost() + "_" + deployDomain.getPort(), status);
			System.out.println("发送请求===第一次发现错误的部署error DeployDomain==host:=="+deployDomain.getHost()+" ==port:=="+deployDomain.getPort());
		}else{
			DeployStatus deployStatus=DeployMonitor.getErrorDeployStatus(deployDomain);
			deployStatus.addExceptionDate(new Date());//增加的时候可能会触发定义死亡事件
			System.out.println("发送请求===多次发现错误的部署error DeployDomain==host:=="+deployDomain.getHost()+" ==port:=="+deployDomain.getPort());

			if (deployStatus.getExceptionDate() != null&& deployStatus.getExceptionDate().size() >= EXCEPTIONNUM_TO_DEAD) {// 超过5次异常
				if(!deployDomain.isDead()){
				deployDomain.setDead(true);// 调用该接口的时候把它删除
				System.out.println("错误部署定义死亡error DeployDomain==host:=="+deployDomain.getHost()+" ==port:=="+deployDomain.getPort());
				}
			}
		}
		
	}
//if no deploy in map
 	public static boolean removeErrorDeployDomain(DeployDomain deployDomain) {
		LOG.error("app name[" + deployDomain.getAppDefDomain().getAppName() + "],deploy host["
				+ deployDomain.getHost() + "] port[" + deployDomain.getPort()
				+ "], log un error");
			ErrorDeployMap.remove(deployDomain.getHost() + "_" + deployDomain.getPort());//删除的时候要考虑是否重新加入，置活操作
		return true;
	}
 	
 	public static DeployStatus getErrorDeployStatus(DeployDomain deployDomain){
 		return ErrorDeployMap.get(deployDomain.getHost() + "_" + deployDomain.getPort());
 	}
 	
 	public static void main(String[] args){
 		System.out.println("DeployMonitor startUp......");
// 		DeployMonitor a=new DeployMonitor();
 	}

}
