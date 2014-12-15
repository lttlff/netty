package com.zjhcsoft.rpc.interceptor;

import com.zjhcsoft.rpc.invocation.ActionInvocation;

public interface Interceptor {
	public Object invoke(ActionInvocation invocation);
}
