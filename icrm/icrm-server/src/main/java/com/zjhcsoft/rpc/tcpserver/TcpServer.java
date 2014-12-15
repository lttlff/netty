package com.zjhcsoft.rpc.tcpserver;

import com.zjhcsoft.rpc.handler.ServerHandler;

public interface TcpServer {
	
	public int getPort();

	public void setPort(int port);

	public int getMinIdleNum();

	public void setMinIdleNum(int minIdleNum);

	public int getMaxActiveNum();

	public void setMaxActiveNum(int maxActiveNum);
	
	public int getSoTimeout();
	
	public void setSoTimeout(int soTimeOut);
	
	public int getBacklog();
	
	public void setBacklog(int backlog);

	public void start();

	public void stop();
	
	public void run();
	
	public boolean isRunning();
 
	public void setHandler(ServerHandler handler);
	
	public ServerHandler getHandler();
	
}
