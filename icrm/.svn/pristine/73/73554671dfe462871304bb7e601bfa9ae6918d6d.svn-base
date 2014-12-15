package com.zjhcsoft.rpc.cfg.domain;

import java.util.ArrayList;
import java.util.List;

public class RPCDomain {

	private List<ProtocolDomain> protocolList;
	
	public void addProtocolDomain(ProtocolDomain protocolDomain){
		this.getProtocolList().add(protocolDomain);
	}

	public List<ProtocolDomain> getProtocolList() {
		if(this.protocolList == null){
			this.protocolList = new ArrayList<ProtocolDomain>();
		}
		return protocolList;
	}

	public void setProtocolList(List<ProtocolDomain> protocolList) {
		this.protocolList = protocolList;
	}

}
