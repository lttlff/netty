package com.zjhcsoft.rpc.tcpserver.nio.netty;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.zjhcsoft.rpc.context.impl.RequestContext;
import com.zjhcsoft.rpc.context.impl.ResponseContext;
import com.zjhcsoft.rpc.tcpserver.protocol.TcpProtocolFactory;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

public class ProtocolDecoder extends FrameDecoder {

	private static Log LOG = LogFactory.getLog(ProtocolDecoder.class);
	@Override
	protected Object decode(ChannelHandlerContext channelHandlerContext,
			Channel channel, ChannelBuffer channelBuffer) throws Exception {
		LOG.error("readableBytes["+System.currentTimeMillis()+"]="+channelBuffer.readableBytes());
		String protocol = readProtocol(channelBuffer);//get front four byte for data protocol
		int length = channelBuffer.readableBytes(); //get byte size of obj
		Object obj = TcpProtocolFactory.getProtocol(protocol).read( 
				channelBuffer, length);//base data protocol to get obj
		if (obj instanceof RequestContext) {
			((RequestContext) obj).setRequestLength(new Long(length));//set data length
			((RequestContext) obj).getDeployDomain().setData_protocol(protocol);//set data protocol
		}
		if (obj instanceof ResponseContext) {
			((ResponseContext) obj).setResponseLength(new Long(length));//set data length
			((ResponseContext) obj).setData_protocol(protocol);//set data protocol
		}
		return obj;
	}

	private String readProtocol(ChannelBuffer channelBuffer) {
		byte[] protrocolCodebytes = new byte[4];
		channelBuffer.readBytes(protrocolCodebytes);//get front four byte for data protocol
		StringBuffer protocol = new StringBuffer();
		for (int i = 0; i < protrocolCodebytes.length; i++) {
			protocol.append(String.valueOf(protrocolCodebytes[i]));
		}
		return protocol.toString();
	}

}
