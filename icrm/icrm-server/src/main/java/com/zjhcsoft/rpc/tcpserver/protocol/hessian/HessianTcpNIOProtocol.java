package com.zjhcsoft.rpc.tcpserver.protocol.hessian;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.zjhcsoft.rpc.tcpserver.TcpProtocolHelper;
import com.zjhcsoft.rpc.tcpserver.TcpServer;
import com.zjhcsoft.rpc.tcpserver.protocol.AbstractTcpProtocol;
import com.zjhcsoft.rpc.tcpserver.protocol.TcpConstants;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

public class HessianTcpNIOProtocol extends AbstractTcpProtocol {
	
	private static Log LOG = LogFactory.getLog(HessianTcpNIOProtocol.class);

	public String getProtocolCode() {
		return TcpConstants.HessianTcpNIOCode;
	}
	
	public String getProtocolName() {
		return TcpConstants.HessianTcpNIO;
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

	public Object read(ChannelBuffer channelBuffer,int length) {//这里的length根本没用，这里读取了通道中所有的数据
 		byte[] bytes = new byte[channelBuffer.readableBytes()];//数据容器
		channelBuffer.readBytes(bytes);//从通道中读取数据直到盛满bytes容器
		Hessian2Input input = new Hessian2Input(new ByteArrayInputStream(bytes));//转换成HESSIAN对象
		try {
			return input.readObject();
		} catch (IOException e) {
 			throw new RuntimeException(e);
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void write(ChannelHandlerContext ctx,MessageEvent e,String protocol) {
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		Hessian2Output output = new Hessian2Output(byteArray);
		try {
			if(e.getMessage() instanceof Throwable){
				Throwable cause = (Throwable)e.getMessage();
				output.writeFault("ServiceException", cause.getMessage(), cause);
			}else{
				output.writeObject(e.getMessage());//把对象写到缓存byteArray里面
			}
			
 			output.close();
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
		
		byte[] protocolBytes = new byte[protocol.length()];
		for (int i = 0; i < protocolBytes.length; i++) {
			protocolBytes[i] = Byte.parseByte(protocol.substring(i, i + 1));
		}
		byte[] bytes = byteArray.toByteArray();
		
		ChannelBuffer buf = ChannelBuffers.buffer(bytes.length+protocol.length());//对象的长度+协议编码的长度
		/**
		 * 加4个字节的长度
		 */
		//buf.writeInt(protocol.length()+bytes.length);
 		buf.writeBytes(protocolBytes);//写协议编码的数据(长度)
		buf.writeBytes(bytes);//写对象的数据
  	 	Channels.write(ctx, e.getFuture(), buf);		
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
 		return TcpProtocolHelper.getHessianObjectSize(result);
	}
}
