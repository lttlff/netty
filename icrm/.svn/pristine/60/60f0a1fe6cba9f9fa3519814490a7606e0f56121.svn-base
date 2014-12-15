package com.zjhcsoft.rpc.tcpserver.protocol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zjhcsoft.rpc.cfg.RpcXmlConfigure_server;
import com.zjhcsoft.rpc.cfg.domain.ParamDomain;
import com.zjhcsoft.rpc.cfg.domain.ProtocolDomain;
import com.zjhcsoft.rpc.cfg.domain.RPCDomainServer;
import org.sevenstar.util.BeanHelper;


public class TcpProtocolFactory {

	public static Map ProtocolMap = new HashMap();
	static {
		RPCDomainServer openDomain = RpcXmlConfigure_server.getServerCfgDomain();
		List protocolDomainList = openDomain.getProtocolList();
		for(int i=0;i<protocolDomainList.size();i++){
			ProtocolDomain protocolDomain = (ProtocolDomain)protocolDomainList.get(i);
			if(TcpConstants.protocol.equalsIgnoreCase(protocolDomain.getTransport_protocol())){
				TcpProtocol tcpProtocol = (TcpProtocol)BeanHelper.newInstance(protocolDomain.getClassName());
				Map paramMap = new HashMap();
				List paramList = protocolDomain.getParamList();
				for(int j=0;j<paramList.size();j++){
					ParamDomain paramDomain = (ParamDomain)paramList.get(j);
					paramMap.put(paramDomain.getName(), paramDomain.getValue());
				}
				tcpProtocol.setParamMap(paramMap);
				ProtocolMap.put(tcpProtocol.getProtocolCode(), tcpProtocol);
				ProtocolMap.put(tcpProtocol.getProtocolName(), tcpProtocol);
			}
			
		}
	}

	public static TcpProtocol getProtocol(String code) {
		return (TcpProtocol) ProtocolMap.get(code);
	}
}
