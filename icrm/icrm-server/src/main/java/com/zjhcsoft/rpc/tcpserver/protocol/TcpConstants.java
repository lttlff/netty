package com.zjhcsoft.rpc.tcpserver.protocol;

public class TcpConstants {
	
	public final static String HessianTcpOIOShortCode = "0000";
	public final static String HessianTcpOIOShort = "HessianTcpOIOShort";
	public final static String HessianTcpOIOLongCode = "0001";
	public final static String HessianTcpOIOLong = "HessianTcpOIOLong";
	public final static String HessianTcpNIOCode = "0002";
	public final static String HessianTcpNIO = "HessianTcpNIO";

	public final static String JSONTcpOIOShortCode = "0010";
	public final static String JSONTcpOIOShort = "JSONTcpOIOShort";
	public final static String JSONTcpOIOLongCode = "0011";
	public final static String JSONTcpOIOLong = "JSONTcpOIOLong";
	public final static String JSONTcpNIOCode = "0012";
	public final static String JSONTcpNIO = "JSONTcpNIO";

	public final static String XMLTcpOIOShortCode = "0020";
	public final static String XMLTcpOIOShort = "XMLTcpOIOShort";
	public final static String XMLTcpOIOLongCode = "0021";
	public final static String XMLTcpOIOLong = "XMLTcpOIOLong";
	public final static String XMLTcpNIOCode = "0022";
	public final static String XMLTcpNIO = "XMLTcpNIO";
	
	public final static String LiveTestCode = "9999";
	
	public final static String protocol = "tcp";
	
	/**
	 * 末位为1，则为长连接
	 * @param protocol
	 */
	public static boolean isLongConnection(String protocol){
		return protocol.endsWith("10") || protocol.endsWith("11");
	}
	
	/**
	 * 首位1，则为测试
	 * @param protocol
	 * @return
	 */
	public static boolean isLiveTest(String protocol){
		return protocol.endsWith("1") ;
	}
	
}
