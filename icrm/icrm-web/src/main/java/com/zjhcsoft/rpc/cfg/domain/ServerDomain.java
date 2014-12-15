package com.zjhcsoft.rpc.cfg.domain;

import java.util.ArrayList;
import java.util.List;

public class ServerDomain {
	private String name;
	private String className;
	private List<ParamDomain> paramList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public void addParamDomain(ParamDomain paramDomain){
		if(this.paramList == null){
			this.paramList = new ArrayList<ParamDomain>();
		}
		this.getParamList().add(paramDomain);
	}

}
