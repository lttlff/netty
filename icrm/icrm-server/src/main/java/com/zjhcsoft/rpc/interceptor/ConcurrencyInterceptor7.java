package com.zjhcsoft.rpc.interceptor;

import com.zjhcsoft.rpc.invocation.ActionInvocation;

public class ConcurrencyInterceptor7 implements Interceptor {

	public Object invoke(ActionInvocation invocation) {
		System.out.println("ConcurrencyInterceptor7===========7");
		return invocation.invoke();
	}

}
