package com.zjhcsoft.rpc.cfg.domain;

import java.util.ArrayList;
import java.util.List;

public class InterceptorStackDomain {
	private String name;
	private String pattern;
	private String type;
	private String mode;
//	private String className;
	private List<InterceptorDomain> interceptorList;
	
	public void addInterceptorDomain(InterceptorDomain interceptorDomain){
		if(interceptorList == null){
			this.interceptorList = new ArrayList<InterceptorDomain>();
		}
		this.getInterceptorList().add(interceptorDomain);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

//	public String getClassName() {
//		return className;
//	}
//
//	public void setClassName(String className) {
//		this.className = className;
//	}

	public List<InterceptorDomain> getInterceptorList() {
		return interceptorList;
	}

	public void setInterceptorList(List<InterceptorDomain> interceptorList) {
		this.interceptorList = interceptorList;
	}
}
