package com.zjhcsoft.rpc.tcpserver.nio.netty;

import com.zjhcsoft.rpc.context.impl.RequestContext;
import com.zjhcsoft.rpc.context.impl.ResponseContext;
import com.zjhcsoft.rpc.tcpserver.protocol.TcpProtocolFactory;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class ProtocolEncoder extends SimpleChannelHandler {
	public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) {
		Object obj = e.getMessage();
		if (obj instanceof RequestContext) {
			String protocol = ((RequestContext) obj).getDeployDomain().getData_protocol();

			//first static build ProtocolMap,then base protocol to get data TcpProtocol
			//final write data protocol and obj
			TcpProtocolFactory.getProtocol(protocol).write(ctx, e,
					TcpProtocolFactory.getProtocol(protocol).getProtocolCode());
		}
		
		if (obj instanceof ResponseContext) {
			String protocol = ((ResponseContext) obj).getData_protocol();

			//first static build ProtocolMap,then base protocol to get data TcpProtocol
			//final write data protocol and obj
			TcpProtocolFactory.getProtocol(protocol).write(ctx, e,
					TcpProtocolFactory.getProtocol(protocol).getProtocolCode());
		}
	}
}
