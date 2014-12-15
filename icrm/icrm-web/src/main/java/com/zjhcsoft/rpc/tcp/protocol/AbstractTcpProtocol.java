package com.zjhcsoft.rpc.tcp.protocol;
 
public abstract class AbstractTcpProtocol implements TcpProtocol {

//	public void writeProtocolCode(OutputStream os) {
//		TcpProtocolHelper.writeProtocolCode(os, this.getProtocolCode());
//	}
//
//	public String readProtocolCode(InputStream is) {
//		return TcpProtocolHelper.readProtocolCode(is);
//	}
 

//	public boolean doTest(Socket socket, Object object) {
//		if (object instanceof String
//				&& TcpConstants.LiveTestCode.equalsIgnoreCase((String) object)) {
//			try {
//				TcpProtocolHelper.writeProtocolCode(socket.getOutputStream(), TcpConstants.LiveTestCode);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//		}
//		return true;
//	}

	public boolean isTest(Object object) {
		if (object instanceof String
				&& TcpConstants.LiveTestCode.equalsIgnoreCase((String) object)) {
			return true;
		}
		return false;
	}

}
