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
package com.zjhcsoft.rpc.tcpserver.nio.netty;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.zjhcsoft.rpc.context.RequestContextThreadLocal;
import com.zjhcsoft.rpc.context.impl.RequestContext;
import com.zjhcsoft.rpc.context.impl.ResponseContext;
import com.zjhcsoft.rpc.handler.ServerHandler;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelState;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.sevenstar.util.ExceptionHelper;

/**
 * Handles both client-side and server-side handler depending on which
 * constructor was called.
 */
public class NettyServerHandler extends SimpleChannelUpstreamHandler {

	private static final Log LOG = LogFactory.getLog(NettyServerHandler.class);

	public NettyServerHandler() {

	}

	public NettyServerHandler(ServerHandler handler,int port) {
		this.handler = handler;
		this.port = port;
	}

	private ServerHandler handler;
	
	private int port;
	
//	@Override
//	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e){
//		DefaultTcpNettyNioServer.ALL_CHANNELS.add(e.getChannel());
//	}
	
//	@Override
//	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e){
//		Date time=new Date(System.currentTimeMillis()/1000);
//		ChannelFuture f=e.getChannel().write(time);
//		f.addListener(ChannelFutureListener.CLOSE);
//	}

	@Override
	public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e)
			throws Exception {
		if (e instanceof ChannelStateEvent
				&& ((ChannelStateEvent) e).getState() != ChannelState.INTEREST_OPS) {
			LOG.info(e.toString());
		}
		super.handleUpstream(ctx, e);
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		RequestContext request = (RequestContext) e.getMessage();
		//response don't init,value is null.
		ResponseContext response=null;
  		LOG.debug("server received:" + request.getRequestId());
		try {
			
			//input request,come back result value of response ,write in threadLoacl of response
//			Object result = handler.accept(request);
			handler.accept(request);
			response=RequestContextThreadLocal.getResponseContext();
//			RequestContextThreadLocal.getContextThreadLocal().set(null);
			RequestContextThreadLocal.removeContextThreadLocal();
		} catch (Throwable cause) {
			cause.printStackTrace();
			Throwable rootCause = ExceptionHelper.getCause(cause);
			StackTraceElement[] stes = new StackTraceElement[5];
			System.arraycopy(rootCause.getStackTrace(), 0, stes, 0, stes.length);
			rootCause.setStackTrace(stes);
			response.setSuccess(false);
			response.setResult(rootCause);
		}
		e.getChannel().write(response);
		LOG.debug("server write:" + request.getRequestId());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		LOG.error("Unexpected exception from downstream.", e.getCause());
		e.getChannel().close();
	}

	public ServerHandler getHandler() {
		return handler;
	}

	public void setHandler(ServerHandler handler) {
		this.handler = handler;
	}

}
