package com.zjhcsoft.rpc.tcp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zjhcsoft.rpc.cfg.RpcXmlConfigure;
import com.zjhcsoft.rpc.cfg.domain.ParamDomain;
import com.zjhcsoft.rpc.cfg.domain.ProtocolDomain;
import com.zjhcsoft.rpc.tcp.protocol.TcpConstants;
import org.sevenstar.util.BeanHelper;

public class TcpClientFactory {
	private static Map<String, TcpClient> ClientMap = new HashMap<String, TcpClient>();
	static {
		List protocolList = RpcXmlConfigure.getClientOpenCfgDomain()
				.getProtocolList();
		for (int i = 0; i < protocolList.size(); i++) {
			ProtocolDomain protocolDomain = (ProtocolDomain) protocolList
					.get(i);
			if (TcpConstants.protocol.equalsIgnoreCase(protocolDomain
					.getTransport_protocol())) {
				TcpClient client = (TcpClient) BeanHelper
						.newInstance(protocolDomain.getClassName());
				Map paramMap = new HashMap();
				for (int j = 0; j < protocolDomain.getParamList().size(); j++) {
					ParamDomain paramDomain = (ParamDomain) protocolDomain
							.getParamList().get(j);
					if (paramDomain.getName() != null
							&& !"".equalsIgnoreCase(paramDomain.getName()
									.trim())) {
						paramMap.put(paramDomain.getName(),
								paramDomain.getValue());
					}
				}
				client.setParamMap(paramMap);
				ClientMap.put(protocolDomain.getData_protocol(), client);
			}
		}
	}
	
	public static TcpClient getTcpClient(String data_protocol){
		return ClientMap.get(data_protocol);
	}
}
