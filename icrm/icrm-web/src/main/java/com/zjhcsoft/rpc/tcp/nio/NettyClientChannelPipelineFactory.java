package com.zjhcsoft.rpc.tcp.nio;

import com.zjhcsoft.rpc.tcp.TcpClient;
import com.zjhcsoft.rpc.tcp.nio.netty.NettyClientHandler;
import com.zjhcsoft.rpc.tcp.nio.netty.ProtocolDecoder;
import com.zjhcsoft.rpc.tcp.nio.netty.ProtocolEncoder;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;

public class NettyClientChannelPipelineFactory implements
		ChannelPipelineFactory {
	private TcpClient client;
	public NettyClientChannelPipelineFactory(TcpClient client){
		this.client = client;
	}

	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline();
		pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));  
		pipeline.addLast("frameEncode", new LengthFieldPrepender(4, false));
		pipeline.addLast("encoder", new ProtocolEncoder());
		pipeline.addLast("decoder", new ProtocolDecoder());
		pipeline.addLast("handler", new NettyClientHandler(this.getClient()));
		return pipeline;
	}

	public TcpClient getClient() {
		return client;
	}

	public void setClient(TcpClient client) {
		this.client = client;
	}
}
