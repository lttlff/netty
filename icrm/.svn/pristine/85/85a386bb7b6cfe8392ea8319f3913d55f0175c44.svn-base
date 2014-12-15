package com.zjhcsoft.rpc.context;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.zjhcsoft.rpc.context.impl.RequestContext;
import com.zjhcsoft.rpc.context.impl.ResponseContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RequestContextThreadLocal {
	//application如果需要修改属性的话，需要全局同步，session也一样，需要做所有节点的数据同步机制
	private static final Log LOG = LogFactory.getLog(RequestContextThreadLocal.class);
//	private static final ClassPathXmlApplicationContext serviceContext = new ClassPathXmlApplicationContext("applicationContext-common.xml");
	
	public static final String request="request";
	public static final String response="response";

	private static ThreadLocal<Map<String,Object>> contextThreadLocal = new ThreadLocal<Map<String,Object>>();
	
//	public static ClassPathXmlApplicationContext getServiceBeanContext(){
//		return serviceContext;
//	}
	
	//设置request本地线程变量，从配置表获取所有request的上下文的监听器
	public static void requestStart(RequestContext request) {
		LOG.debug("RequestContext start");
		ResponseContext response=new ResponseContext();
		//when go back ,host and port will be done for search value.
		response.setHost(request.getDeployDomain().getHost());
		response.setCallStartTimeMillis(request.getCallEndTimeMillis());
		//before input Data_protocol is HessianTcpNIO
		response.setData_protocol(request.getDeployDomain().getData_protocol());
		
		//before input Data_protocol is HessianTcpNIO
//		response.setData_protocol(TcpConstants.JSONTcpNIO);
		
		
		response.setPort(request.getDeployDomain().getPort());
		response.setRequestId(request.getRequestId());
		
		Map<String,Object> mapThreadLocal=new HashMap<String,Object>();
		mapThreadLocal.put(RequestContextThreadLocal.request, request);

		mapThreadLocal.put(RequestContextThreadLocal.response, response);
		setContextThreadLocal(mapThreadLocal);//设置request为本地线程变量（业务层的request）并通过RequestContextImpl可以对属性新增和获取，其他接口没实现
	}
	
	public static RequestContext getRequestContext() {
		if (contextThreadLocal != null
				&& contextThreadLocal.get() != null) {
			return (RequestContext) contextThreadLocal.get().get(RequestContextThreadLocal.request);
		}
		return null;
	}
	
	public static ResponseContext getResponseContext() {
		if (contextThreadLocal != null
				&& contextThreadLocal.get() != null) {
			return (ResponseContext) contextThreadLocal.get().get(RequestContextThreadLocal.response);
		}
		return null;
	}

		private static ThreadLocal<Map<String, Object>> getContextThreadLocal() {
		return contextThreadLocal;
	}

		public static void setContextThreadLocal(Map<String,Object> map) {
			contextThreadLocal.set(map);
			}
		
		public static void removeContextThreadLocal(){
			contextThreadLocal.remove();
		}

	public static void main(String[] args) {

	}
}
