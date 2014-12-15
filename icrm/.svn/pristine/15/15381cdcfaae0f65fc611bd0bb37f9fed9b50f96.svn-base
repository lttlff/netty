package com.zjhcsoft.rpc.exception;

public class ESBException extends RuntimeException {
	private boolean needLog = false;
	private String errorCode;

	public ESBException(String e) {
		super(e);
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

	public ESBException(String message, Throwable cause) {
		super(message, cause);
		this.setNeedLog(true);
	}

	/**
	 * Constructs a new runtime exception with the specified cause and a detail
	 * message of <tt>(cause==null ? null : cause.toString())</tt> (which
	 * typically contains the class and detail message of <tt>cause</tt>).
	 * This constructor is useful for runtime exceptions that are little more
	 * than wrappers for other throwables.
	 * 
	 * @param cause
	 *            the cause (which is saved for later retrieval by the
	 *            {@link #getCause()} method). (A <tt>null</tt> value is
	 *            permitted, and indicates that the cause is nonexistent or
	 *            unknown.)
	 * @since 1.4
	 */
	public ESBException(Throwable cause) {
		super(cause);
		this.setNeedLog(true);
	}
}
