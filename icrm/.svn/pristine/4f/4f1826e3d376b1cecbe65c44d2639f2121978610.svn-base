package com.zjhcsoft.rpc.tcp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.jboss.netty.channel.Channel;

import com.caucho.hessian.io.Hessian2Output;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TcpProtocolHelper {
	//读取4个字节的协议编码
	public static String readProtocolCode(InputStream is) {
		byte[] bytes = new byte[4];
		try {
			is.read(bytes);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		StringBuffer protocol = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			protocol.append(String.valueOf(bytes[i]));
		}
		return protocol.toString();
	}
	
	public static void writeProtocolCode(OutputStream os, String code) {

		byte[] bytes = new byte[code.length()];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = Byte.parseByte(code.substring(i, i + 1));
		}
		try {
			os.write(bytes);
			os.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void writeProtocolCode(Channel channel, String code) {

		byte[] bytes = new byte[code.length()];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = Byte.parseByte(code.substring(i, i + 1));
		}

		channel.write(bytes);

	}
	
//	private static int bufferSize = 8192;
	
//	public static byte[] read(InputStream is){
//		/**
//		 * 先读所有数据，放入 ByteArrayInputStream
//		 */
//		List bufList = new ArrayList();
//		byte[] buf = new byte[bufferSize];
//		int totalSize = 0;
//		try {
//			int size = is.read(buf);
//			while (size != -1) {
//				totalSize = totalSize + size;
//				byte[] readData = new byte[size];
//				System.arraycopy(buf, 0, readData, 0, size);
//				bufList.add(readData);
//				if(size < bufferSize ){
//					break;
//				}
//				buf = new byte[bufferSize];
//				size = is.read(buf);
//			}
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//		byte[] totalData = new byte[totalSize];
//		int pos = 0;
//		for (int i = 0; i < bufList.size(); i++) {
//			byte[] readData = (byte[]) bufList.get(i);
//			System.arraycopy(readData, 0, totalData, pos,
//					readData.length);
//			pos = pos + readData.length;
//		}
//		bufList.clear();
//		return totalData;
//	}
	
//	public static String readString(InputStream is,String encode)  {
//		BufferedInputStream bis = new BufferedInputStream(is);
// 		byte[] totalByte = new byte[0];
//		byte[] readByte = new byte[bufferSize];
//		int readLength = 0;
//		try {
//			while((readLength = bis.read(readByte)) > 0){
//				byte[] newTotalByte = new byte[totalByte.length + readLength];
//				System.arraycopy(totalByte, 0, newTotalByte, 0, totalByte.length);
//				System.arraycopy(readByte, 0, newTotalByte, totalByte.length, readLength);
//				totalByte = newTotalByte;
//				if(readLength < bufferSize){
//					break;
//				}
//			}
//		} catch (IOException e) {
// 			throw new RuntimeException(e);
//		}
//		try {
//			return new String(totalByte,encode);
//		} catch (UnsupportedEncodingException e) {
// 			throw new RuntimeException(e);
//		}
//	}
	
	public static Long getHessianObjectSize(Object result) {
		if(result == null){
			return new Long(0);
		}
		Hessian2Output output = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
 		try {
 			output = new Hessian2Output(bos);
			output.writeObject(result);
			output.flush();
		     bos.flush();
		     return new Long(bos.toByteArray().length);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	 
//	public static Long getJsonObjectSize(Object result) {
//		ObjectMapper mapper = new ObjectMapper();
//		String resultString;
//		try {
//			resultString = mapper.writeValueAsString(result);
//		} catch (JsonGenerationException e) {
//			throw new RuntimeException(e);
// 		} catch (JsonMappingException e) {
// 			throw new RuntimeException(e);
// 		} catch (IOException e) {
// 			throw new RuntimeException(e);
//		}
//		if(HttpContext.getResponse() != null){
//			try {
//				return new Long(resultString.getBytes(HttpContext.getResponse().getCharacterEncoding()).length);
//			} catch (UnsupportedEncodingException e) {
//				throw new RuntimeException(e);
//			}
//		}else{
//			try {
//				return new Long(resultString.getBytes("utf-8").length);
//			} catch (UnsupportedEncodingException e) {
//				throw new RuntimeException(e);
//			}
//		}
// 		
//	}

}
