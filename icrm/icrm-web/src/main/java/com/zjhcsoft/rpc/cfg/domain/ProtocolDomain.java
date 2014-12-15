package com.zjhcsoft.rpc.cfg.domain;

import java.util.ArrayList;
import java.util.List;

public class ProtocolDomain {
	private String transport_protocol;
	private String data_protocol;
	private String interfaceClassName;
	private String className;
	private List<ParamDomain> paramList;
	
	public void addParamDomain(ParamDomain paramDomain){
		if(this.paramList == null){
			this.paramList = new ArrayList<ParamDomain>();
		}
		this.paramList.add(paramDomain);
	}

	public String getTransport_protocol() {
		return transport_protocol;
	}

	public void setTransport_protocol(String transport_protocol) {
		this.transport_protocol = transport_protocol;
	}

	public String getData_protocol() {
		return data_protocol;
	}

	public void setData_protocol(String data_protocol) {
		this.data_protocol = data_protocol;
	}

	public String getInterfaceClassName() {
		return interfaceClassName;
	}

	public void setInterfaceClassName(String interfaceClassName) {
		this.interfaceClassName = interfaceClassName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<ParamDomain> getParamList() {
		return paramList;
	}

	public void setParamList(List<ParamDomain> paramList) {
		this.paramList = paramList;
	}

}
