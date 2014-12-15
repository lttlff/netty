/*
 * Copyright 2011 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.zjhcsoft.rpc.tcp.nio.netty;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.zjhcsoft.rpc.context.impl.ResponseContext;
import com.zjhcsoft.rpc.exception.ServiceInvokeException;
import com.zjhcsoft.rpc.tcp.TcpClient;
import com.zjhcsoft.rpc.tcp.nio.DefaultTcpNettyNioClient;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelState;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 * Handler implementation for the object echo client. It initiates the ping-pong
 * traffic between the object echo client and server by sending the first
 * message to the server.
 */
public class NettyClientHandler extends SimpleChannelUpstreamHandler {

	private static final Log LOG = LogFactory.getLog(NettyClientHandler.class);

	private TcpClient client;

	/**
	 * Creates a client-side handler.
	 */
	public NettyClientHandler(TcpClient client) {

		this.client = client;
	}

	@Override
	public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e)
			throws Exception {
		if (e instanceof ChannelStateEvent
				&& ((ChannelStateEvent) e).getState() != ChannelState.INTEREST_OPS) {
			LOG.info(e.toString());//OPEN,BOUND,CONNECTED,CLOSE的动作都会调用这里
		}
		super.handleUpstream(ctx, e);
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
		// Send the first message if this handler is a client-side handler.
//		e.getChannel().write("abcd");
	}
//NettyClientHandler when data arrive,write back
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		LOG.debug("client messageReceived[" + System.currentTimeMillis() + "]:"+ e.getMessage());
		try {
			ResponseContext obj = (ResponseContext) e.getMessage();
			LOG.debug("client received:"+obj.getRequestId());		
			((DefaultTcpNettyNioClient) client).writeBackResult(
					obj.getRequestId(), obj);
		}catch (ServiceInvokeException cause) {
			LOG.error(cause.getMessage());
//			System.out.println(cause.getMessage()+"=======ServiceInvokeException");
		} catch (Throwable cause) {
			cause.printStackTrace();//
//			System.out.println(cause.getMessage()+"=======Throwable");
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		LOG.error("Unexpected exception from downstream.", e.getCause());//发起连接失败以后都会调用这个异常接口,打印消息
		e.getChannel().close();
//		System.out.println("====================通道异常**********==============e.getChannel()="+e.getChannel());
//		throw new RuntimeException("通道连接异常================");
//		try {
//			e.getChannel().close();
//			System.out.println("====================释放资源**********===============");
//			e.getFuture().getChannel().getCloseFuture().awaitUninterruptibly();
////			bootstrap.releaseExternalResources();
////			ctx.getChannel().releaseExternalResources();
//		} catch (Throwable e2) {
//			e2.printStackTrace();
//		}
	}
}
