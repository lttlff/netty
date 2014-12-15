package com.zjhcsoft.rpc.context.impl;

import com.zjhcsoft.rpc.context.RequestContextThreadLocal;

public class Response {

	private static ResponseContext getResponse(){
		return RequestContextThreadLocal.getResponseContext();
	}

//	public static String getSessionId() {
//		return Response.getResponse().getSessionId();
//	}

//	public String getCode() {
//		return this.code;
//	}
//
//	public String getMessage() {
//		return this.message;
//	}
//
//	public Object get() {
//		return this.result;
//	}

	public static void setCode(String resultCode) {
		Response.getResponse().setCode(resultCode);
	}

//	public String getHost() {
//		return host;
//	}
//
//	public void setHost(String host) {
//		this.host = host;
//	}

//	public int getPort() {
//		return port;
//	}
//
//	public void setPort(int port) {
//		this.port = port;
//	}

	public static void setMessage(String resultMessage) {
		Response.getResponse().setMessage(resultMessage);
	}

	public static void setStacktrace(String stacktrace) {
		Response.getResponse().setStacktrace(stacktrace);
	}

//	public static void setResponseLength(Long responseLength) {
//		Response.getResponse().setResponseLength(responseLength);
//	}

	public Long getResponseLength() {
 		return Response.getResponse().getResponseLength();
	}

//	public Object getResult() {
//		return result;
//	}

	public static void setResult(Object result) {
		Response.getResponse().setResult(result);
	}

//	public String getStacktrace() {
//		return stacktrace;
//	}

//	public boolean isSuccess() {
//		return isSuccess;
//	}

	public static void setSuccess(boolean isSuccess) {
		Response.getResponse().setSuccess(isSuccess);
	}

//	public long getCallStartTimeMillis() {
//		return callStartTimeMillis;
//	}

//	public void setCallStartTimeMillis(long callStartTimeMillis) {
//		this.callStartTimeMillis = callStartTimeMillis;
//	}

//	public long getCallEndTimeMillis() {
//		return callEndTimeMillis;
//	}

	public static void setCallEndTimeMillis(long callEndTimeMillis) {
		Response.getResponse().setCallEndTimeMillis(callEndTimeMillis);
	}

//	public String getData_protocol() {
//		return data_protocol;
//	}

//	public void setData_protocol(String data_protocol) {
//		this.data_protocol = data_protocol;
//	}

//	public Long getRequestLength() {
////		return requestLength;
//		return response.getRequestLength();
//	}

//	public void setRequestLength(Long requestLength) {
////		this.requestLength = requestLength;
//		response
//	}
}
