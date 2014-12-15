package com.zjhcsoft.rpc.context.impl;

import java.util.Map;

import com.zjhcsoft.rbac.user.domain.User;
import com.zjhcsoft.rbac.user.domain.UserSession;
import com.zjhcsoft.rpc.context.RequestContextThreadLocal;

public class Request {

	private static RequestContext getRequest(){
		return RequestContextThreadLocal.getRequestContext();
	}
	//这里获得的是服务本机的IP，要修改成webServerIp
	public static String getHost() {
		return Request.getRequest().getDeployDomain().getHost();
	}

	public static String getUser() {
		return Request.getRequest().getUser();
	}

//	public static String getPasswd() {
//		return Request.getRequest().getPasswd();
//	}
//这里要修改成webServer port
	public static int getPort() {
		return Request.getRequest().getDeployDomain().getPort();
	}

//	public boolean isLiveTest() {
//		return request.isLiveTest();
//	}

//	public Object getParamObject() {
//		return request.getParamObject();
//	}

	public static long getCallStartTimeMillis() {
		return Request.getRequest().getCallStartTimeMillis();
	}
//这里要修改成请求到服务层后的网路通讯时间返回跟上面减一下
	public static long getCallEndTimeMillis() {
		return Request.getRequest().getCallEndTimeMillis();
	}
//默认false,需要返回数据
	public static boolean isAsync() {
		return Request.getRequest().isAsync();
	}

//	public String getData_protocol() {
//		return request.getData_protocol();
//	}
//应该修改成requestId,sessionId记录session的主键
//	public static String getSessionId() {
//		return Request.getRequest().getSessionId();
//	}
	
	public static String getRequestId() {
		return Request.getRequest().getRequestId();
	}

	public static String getTransportProtocol() {
		return Request.getRequest().getDeployDomain().getTransport_protocol();//.getTransportProtocol();
	}

	public static String getDataProtocol() {
		return Request.getRequest().getDeployDomain().getData_protocol();//.getDataProtocol();
	}
	//esb路径访问控制,修改为EsbServiceUrl
//	public static String getServiceUrl() {
//		return Request.getRequest().getServiceUrl();
//	}

	public static String getClientIp() {
		return Request.getRequest().getClientIp();
	}

//	public static String getAppId() {
//		return Request.getRequest().getAppId();
//	}

//	public String getModuleId() {
//		return request.getModuleId();
//	}
	
	public static String getAppName() {
		return Request.getRequest().getDeployDomain().getName();
	}

	public static String getClientInfo() {
		return Request.getRequest().getClientInfo();
	}

	public static String getWebIp() {
		return Request.getRequest().getWebIp();
	}

	public static String getStackTrace() {
		return Request.getRequest().getStackTrace();
	}

	public static String getCookieId() {
		return Request.getRequest().getCookieId();
	}

	public static String getWebUrl() {
		return Request.getRequest().getWebUrl();
	}

	public static Long getRequestLength() {
		return Request.getRequest().getRequestLength();
	}

//	public static void clear() {
//
//	}
	
	public static Map<Object, Object> getHeaderMap() {
		return Request.getRequest().getHeaderMap();
	}
	
	public static void setHeader(Object key, Object value) {
		Request.getRequest().getHeaderMap().put(key, value);
	}

	public static Object getHeader(Object key) {
		return Request.getRequest().getHeaderMap().get(key);
	}

	public static Object getAttribute(Object key) {
		return Request.getRequest().getAttributes().get(key);
	}

	public static void setAttribute(Object key, Object value) {
		Request.getRequest().getAttributes().put(key, value);
	}

	public static Map<Object, Object> getAttributes() {
		return Request.getRequest().getAttributes();
	}

	public static String getClassName() {
		return Request.getRequest().getClassName();
	}

	public static String getMethodName() {
		return Request.getRequest().getMethodName();
	}
	/**
	 * 当前用户 侵入性比较大
	 * @date 2014-6-13 上午10:03:17
	 * @author lff
	 * @return
	 */
	public static User getUserSession(){
		return (User)Request.getRequest().getAttribute(
				UserSession.USER_SESSION_ID);
	}
}
