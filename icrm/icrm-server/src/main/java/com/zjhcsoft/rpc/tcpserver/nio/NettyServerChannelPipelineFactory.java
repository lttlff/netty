package com.zjhcsoft.rpc.tcpserver.nio;

import com.zjhcsoft.rpc.handler.ServerHandler;
import com.zjhcsoft.rpc.tcpserver.nio.netty.NettyServerHandler;
import com.zjhcsoft.rpc.tcpserver.nio.netty.ProtocolDecoder;
import com.zjhcsoft.rpc.tcpserver.nio.netty.ProtocolEncoder;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;
import org.jboss.netty.handler.execution.ExecutionHandler;
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;

public class NettyServerChannelPipelineFactory implements ChannelPipelineFactory {
	
	private ServerHandler serverHandler;
	
	private static final ExecutionHandler executionHandler = new ExecutionHandler(new OrderedMemoryAwareThreadPoolExecutor(160, 1048576, 1048576));
	
	private int port;
	
	public NettyServerChannelPipelineFactory(ServerHandler serverHandler,int port){
		this.serverHandler = serverHandler;
		this.port = port;
	}

	//@Override
	public ChannelPipeline getPipeline() throws Exception {
		
//		ChannelPipeline pipeline = Channels.pipeline(){
//			return Channels.pipeline(
//	                 new DatabaseGatewayProtocolEncoder(),
//	                 new DatabaseGatewayProtocolDecoder(),
//	                 executionHandler, // Must be shared
//	                 new DatabaseQueryingHandler());
			return Channels.pipeline(
	                 new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4),
	                 new LengthFieldPrepender(4, false),
	                 new ProtocolEncoder(),
	                 new ProtocolDecoder(),
	                 executionHandler, // Must be shared
	                 new NettyServerHandler(this.getServerHandler(),this.getPort()));
		}
//		pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));  
//		pipeline.addLast("frameEncode", new LengthFieldPrepender(4, false));
//		pipeline.addLast("encoder", new ProtocolEncoder());
//		pipeline.addLast("decoder", new ProtocolDecoder());
//		pipeline.addLast("handler", new NettyServerHandler(
//				this.getServerHandler(),
//				this.getPort()));
//		return pipeline;
//	/*	return Channels.pipeline(new ProtocolEncoder(),
//				new ProtocolDecoder(), new NettyServerHandler(
//						this.getServerHandler(),
//						this.getPort()));*/
//	}

	public ServerHandler getServerHandler() {
		return serverHandler;
	}

	public void setServerHandler(ServerHandler serverHandler) {
		this.serverHandler = serverHandler;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	 

}
