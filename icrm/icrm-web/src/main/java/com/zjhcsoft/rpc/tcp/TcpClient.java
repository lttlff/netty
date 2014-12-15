package com.zjhcsoft.rpc.tcp;

import java.util.Map;

import com.zjhcsoft.rpc.context.impl.RequestContext;

public interface TcpClient {

	public Object call(RequestContext rpcParam);

//	public Object call(String host, int port, String protocol, Object obj);

	public Map getParamMap();

	public void setParamMap(Map paramMap);

}
