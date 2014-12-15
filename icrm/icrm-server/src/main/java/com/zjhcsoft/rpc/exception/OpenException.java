package com.zjhcsoft.rpc.exception;

public class OpenException extends RuntimeException {
 
	public OpenException(String e) {
		super(e);
	}
	 

	public OpenException(String message, Throwable cause) {
		super(message, cause);
 	}

 
	public OpenException(Throwable cause) {
		super(cause);
 	}
}
