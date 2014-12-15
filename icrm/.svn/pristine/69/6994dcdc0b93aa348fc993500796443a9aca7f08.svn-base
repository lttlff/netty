package com.zjhcsoft.rpc.tcpserver.protocol.json;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.zjhcsoft.rpc.context.impl.ResponseContext;
import com.zjhcsoft.rpc.tcpserver.TcpProtocolHelper;
import com.zjhcsoft.rpc.tcpserver.TcpServer;
import com.zjhcsoft.rpc.tcpserver.protocol.AbstractTcpProtocol;
import com.zjhcsoft.rpc.tcpserver.protocol.TcpConstants;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTcpNIOProtocol extends AbstractTcpProtocol {
	
	private Log LOG = LogFactory.getLog(JsonTcpNIOProtocol.class);

	public String getProtocolCode() {
		return TcpConstants.JSONTcpNIOCode;
	}
	
	//@Override
	public String getProtocolName() {
 		return TcpConstants.JSONTcpNIO;
	}

	public void read(Socket clientSocket, InputStream is, OutputStream os,
			TcpServer server,Long requestLength) {
		// pass
		throw new RuntimeException("un implements");
	}

	public Object write(Socket socket, InputStream is, OutputStream os,
			Object object) {
		throw new RuntimeException("un implements");
	}

	public Object read(ChannelBuffer channelBuffer, int length) {
		ObjectMapper mapper = new ObjectMapper();
		byte[] bytes = new byte[length];
		channelBuffer.readBytes(bytes);
		LOG.error("read["+System.currentTimeMillis()+"]["+bytes.length+"]:"+new String(bytes));
		InputStream is = new ByteArrayInputStream(bytes);
  		try {
//			Object readValue = mapper.readValue(is, RpcContext.class);
  			Object readValue = mapper.readValue(is, ResponseContext.class);
			return readValue;
		} catch (IOException e) {
			throw new RuntimeException(new String(bytes),e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void write(ChannelHandlerContext ctx, MessageEvent e, String protocol) {
		ObjectMapper mapper = new ObjectMapper();
		byte[] bytes;
		String   result;
		try {
			result = mapper.writeValueAsString(e.getMessage());
			
			bytes = result.getBytes("utf-8");
		} catch (JsonGenerationException ex) {
			throw new RuntimeException(ex);
		} catch (JsonMappingException ex) {
			throw new RuntimeException(ex);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}

		byte[] protocolBytes = new byte[protocol.length()];
		for (int i = 0; i < protocolBytes.length; i++) {
			protocolBytes[i] = Byte.parseByte(protocol.substring(i, i + 1));
		}
 
		ChannelBuffer buf = ChannelBuffers.buffer(bytes.length
				+ protocol.length());
		/**
		 * 加4个字节的长度
		 */
		//buf.writeInt(protocol.length()+bytes.length);
		buf.writeBytes(protocolBytes);
	//	LOG.error("write protocol["+System.currentTimeMillis()+"]["+protocolBytes.length+"]:"+protocol);
		buf.writeBytes(bytes);
	//	LOG.debug("write["+System.currentTimeMillis()+"]["+protocol.length()+bytes.length+"]["+buf.readableBytes()+"]:"+result);
		Channels.write(ctx, e.getFuture(), buf);
	//	LOG.error("write["+System.currentTimeMillis()+"]:success");
  	}

	private Map paramMap;

 	public Map getParamMap() {
		if(this.paramMap == null){
			this.paramMap = new HashMap();
		}
 		return paramMap;
	}

 	public void setParamMap(Map paramMap) {
		if(paramMap != null){
			this.paramMap = paramMap;
		}
	}
 	
 	public Long getObjectSize(Object result) {
// 		return TcpProtocolHelper.getJsonObjectSize(result);
 		return null;
	}

}
