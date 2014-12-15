package com.zjhcsoft.rpc.interceptor;

import com.zjhcsoft.rpc.invocation.ActionInvocation;

public class ConcurrencyInterceptor9 implements Interceptor {

	public Object invoke(ActionInvocation invocation) {
		System.out.println("ConcurrencyInterceptor9===========9");
		return invocation.invoke();
	}

}
