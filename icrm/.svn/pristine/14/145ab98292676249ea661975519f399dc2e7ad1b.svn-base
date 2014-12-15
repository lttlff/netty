package com.zjhcsoft.rpc.tcp.protocol;

import java.util.Map;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

public interface TcpProtocol {
	
	public String getProtocolCode();
	
	public String getProtocolName();
 
	/**
	 * 资源的关闭由 实现类控制，因为要区分长短连接，长连接不能关闭socket
	 * @param clientSocket
	 * @param is
	 * @param os
	 * @param server
	 */
//	public void read(Socket clientSocket,InputStream is,OutputStream os,TcpServer server,Long requestLength);
	
	public Object read(ChannelBuffer channelBuffer,int length);
	
	public boolean isTest(Object object);
	
	/**
	 * 资源的关闭由 实现类控制，因为要区分长短连接，长连接不能关闭socket
	 * @param socket
	 * @param is
	 * @param os
	 * @param object
	 * @return
	 */
//	public Object write(Socket socket,InputStream is,OutputStream os,Object object);
	
	public void write(ChannelHandlerContext ctx,MessageEvent e,String protocol);
	
//	public void writeProtocolCode(OutputStream os);
//	
//	public String readProtocolCode(InputStream is);
	
    public Map getParamMap();
	
	public void setParamMap(Map paramMap);
	
	public Long getObjectSize(Object result); 
	
}
