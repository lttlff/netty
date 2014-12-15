package com.zjhcsoft.rpc.cfg.domain;

import java.util.ArrayList;
import java.util.List;

public class RPCDomainServer {

	private List<ProtocolDomain> protocolList;

//	private List<InterceptorDomain> interceptorList;
	
	private List<ServerDomain> serverList;
	
	private List<InterceptorStackDomain> interceptorStackList;
	
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
	
//	public void addInterceptorDomain(InterceptorDomain interceptorDomain){
//	
//		this.getInterceptorList().add(interceptorDomain);
//	}

//	public List getInterceptorList() {
//		if(this.interceptorList == null){
//			this.interceptorList = new ArrayList();
//		}
//		return interceptorList;
//	}
//
//	public void setInterceptorList(List interceptorList) {
//		this.interceptorList = interceptorList;
//	}
	public void addInterceptorStackDomain(InterceptorStackDomain interceptorStackDomain){
		this.getInterceptorStackList().add(interceptorStackDomain);
	}
	
	public List<InterceptorStackDomain> getInterceptorStackList() {
		if(this.interceptorStackList == null){
			this.interceptorStackList = new ArrayList<InterceptorStackDomain>();
		}
//		return interceptorList;
		return interceptorStackList;
	}

//	public void setInterceptorStackList(
//			List<InterceptorStackDomain> interceptorStackList) {
//		this.interceptorStackList = interceptorStackList;
//	}
	
	public List<ServerDomain> getServerList(){
		if(this.serverList == null){
			this.serverList = new ArrayList<ServerDomain>();
		}
		return this.serverList;
	}
	
	public void addServerDomain(ServerDomain serverDomain){
		this.getServerList().add(serverDomain);
	}



}
