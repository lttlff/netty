package com.zjhcsoft.rpc.context.impl;

import java.io.Serializable;

import com.zjhcsoft.rpc.tcpserver.protocol.TcpConstants;

public class ResponseContext implements Serializable {

	private static final long serialVersionUID = 7861588701360508108L;

	private Object result;

	private String code;

	private String message="ok";
	private String sessionId;
	private String requestId;
	private String host;
	private int port;
	private String data_protocol = TcpConstants.HessianTcpNIO;

	private String stacktrace;

	private long callStartTimeMillis;

	private long callEndTimeMillis;//这个应该在response里面实现

	private boolean isSuccess = true;//默认是成功的

	/**
	 * 返回的字节长度
	 */
	private Long responseLength;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}

	public Object get() {
		return this.result;
	}

	public void setCode(String resultCode) {
		this.code = resultCode;
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

	public void setMessage(String resultMessage) {
		this.message = resultMessage;
	}

	public void setStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
	}

	public void setResponseLength(Long responseLength) {
		this.responseLength = responseLength;
	}

	public Long getResponseLength() {
		return this.responseLength;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getStacktrace() {
		return stacktrace;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public long getCallStartTimeMillis() {
		return callStartTimeMillis;
	}

	public void setCallStartTimeMillis(long callStartTimeMillis) {
		this.callStartTimeMillis = callStartTimeMillis;
	}

	public long getCallEndTimeMillis() {
		return callEndTimeMillis;
	}

	public void setCallEndTimeMillis(long callEndTimeMillis) {
		this.callEndTimeMillis = callEndTimeMillis;
	}

	public String getData_protocol() {
		return data_protocol;
	}

	public void setData_protocol(String data_protocol) {
		this.data_protocol = data_protocol;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
}
