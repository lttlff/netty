package com.zjhcsoft.rpc.monitor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SocketHelper {
	private static final Log LOG = LogFactory.getLog(SocketHelper.class);
	public static boolean isOpen(String host, int port) {
		Socket socket = null;
		InetAddress destinationInetAddress;
		try {
			destinationInetAddress = InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			throw new RuntimeException("invalid host[" + host + "]", e);
		}
		try {
			socket = new Socket(destinationInetAddress, port);
			socket.setSoTimeout(30 * 1000);
			socket.setTcpNoDelay(true);
			socket.setReuseAddress(true);
			socket.setKeepAlive(true);
			/**
			 * 默认等待30秒，发送最后数据
			 */
			socket.setSoLinger(true, 30 * 1000);
			LOG.debug("Socket测试连接Host:"+host+" port:"+port+"  测试连接成功.......");
			return true;
		} catch (Throwable e) {
			LOG.error("Socket测试连接Host:"+host+" port:"+port+"  测试连接失败.......");
			return false;
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args){
		for(int i=0;i<10000;i++){
			System.out.println("["+i+"]["+isOpen("127.0.0.1",9091)+"]");
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
