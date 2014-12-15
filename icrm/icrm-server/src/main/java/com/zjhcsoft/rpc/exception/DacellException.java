package com.zjhcsoft.rpc.exception;

public class DacellException extends RuntimeException {
	private boolean needLog = false;
	private String errorCode;

	public DacellException(String e) {
		super(e);
	}
	
	public DacellException(Throwable cause) {
		super(cause);
		this.setNeedLog(true);
	}

	public DacellException(String message, Throwable cause) {
		super(message, cause);
		this.setNeedLog(true);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public boolean isNeedLog() {
		return needLog;
	}

	public void setNeedLog(boolean needLog) {
		this.needLog = needLog;
	}
}
