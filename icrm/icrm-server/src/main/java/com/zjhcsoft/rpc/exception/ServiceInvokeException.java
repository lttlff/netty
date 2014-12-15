package com.zjhcsoft.rpc.exception;

public class ServiceInvokeException extends RuntimeException {
	 
	private static final long serialVersionUID = -1640648654691692093L;


		public ServiceInvokeException(String e) {
			super(e);
		}
		 

		public ServiceInvokeException(String message, Throwable cause) {
			super(message, cause);
	 	}

	 
		public ServiceInvokeException(Throwable cause) {
			super(cause);
	 	}
	}
