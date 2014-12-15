package com.zjhcsoft.rpc.context.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.zjhcsoft.rpc.cfg.domain.DeployDomain;

public class RequestContext implements Serializable {

	private static final long serialVersionUID = -5566934298957141995L;

	private String className;
	private String methodName;

	private String user;

	private String sessionId;
	
	private String requestId;

	private long callStartTimeMillis;

	private long callEndTimeMillis;//这个应该在response里面实现

	private boolean isAsync = false;//是否需要数据返回
	
	private Long requestLength;

	private Map<Object, Object> dataMap = new HashMap<Object, Object>();

	private Map<Object, Object> headerMap = new HashMap<Object, Object>();

	private String clientIp;

	private String clientInfo;
	
	private String webIp;//web ip
	
	private DeployDomain deployDomain;

	/**
	 * 调用堆栈，监控用，调用的时候自动生成
	 */
	private String stackTrace;

	private String cookieId;

	/**
	 * 页面地址，监控用
	 */
	private String webUrl;//监控页面访问量

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public long getCallStartTimeMillis() {
		return callStartTimeMillis;
	}

	public void setCallStartTimeMillis(long callStartTimeMillis) {
		this.callStartTimeMillis = callStartTimeMillis;
	}

	//set应该是在response里面
	public long getCallEndTimeMillis() {
		return callEndTimeMillis;
	}

	public void setCallEndTimeMillis(long callEndTimeMillis) {
		this.callEndTimeMillis = callEndTimeMillis;
	}

	public boolean isAsync() {
		return isAsync;
	}

	public void setAsync(boolean isAsync) {
		this.isAsync = isAsync;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public void setAttribute(Object key, Object value) {
		dataMap.put(key, value);
	}

	public Object getAttribute(Object key) {
		return dataMap.get(key);
	}

	public void setHeader(Object key, Object value) {
		headerMap.put(key, value);
	}

	public Object getHeader(Object key) {
		return headerMap.get(key);
	}

	public Map<Object, Object> getAttributes() {
		return dataMap;
	}

	public void setAttributes(Map<Object, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public Map<Object, Object> getHeaderMap() {
		return headerMap;
	}

	public void setHeaderMap(Map<Object, Object> headerMap) {
		this.headerMap = headerMap;
	}

	public String getClientIp() {
		return this.clientIp;
	}

	public String getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(String clientInfo) {
		this.clientInfo = clientInfo;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getWebIp() {
		return webIp;
	}

	public void setWebIp(String webIp) {
		this.webIp = webIp;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public String getCookieId() {
		return cookieId;
	}

	public void setCookieId(String cookieId) {
		this.cookieId = cookieId;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getWebUrl() {
		return this.webUrl;
	}

	public void setRequestLength(Long requestLength) {
		this.requestLength = requestLength;
	}

	public Long getRequestLength() {
		return this.requestLength;
	}

	public void clear() {

	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public DeployDomain getDeployDomain() {
		return deployDomain;
	}

	public void setDeployDomain(DeployDomain deployDomain) {
		this.deployDomain = deployDomain;
	}
}
