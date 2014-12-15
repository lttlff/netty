package com.zjhcsoft.rpc.cfg.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppDefDomain implements Serializable{
	
	private static final long serialVersionUID = -1068401636855014644L;
	private String appName;
	private int soTimeOut;
	private boolean is_valid = true;
	private String description;
	
	private List<DeployDomain> deployList=new ArrayList<DeployDomain>();
//	private Map<String, String> appParamMap;
	
	public void addDeployServer(DeployDomain deployDomain) {
		Iterator<DeployDomain> iterator = deployList.iterator();
		boolean flag = true;
		while (iterator.hasNext()) {
			DeployDomain deployTemp = iterator.next();
			if ((deployTemp.getHost().trim().equalsIgnoreCase(deployDomain.getHost().trim()))&&(deployTemp.getPort()==deployDomain.getPort())) {
				flag = false;
				break;
			}
		}
		if (flag) {
			deployList.add(deployDomain);
		}
	}
	
	public void removeDeployServer(DeployDomain deployDomain) {
		Iterator<DeployDomain> iterator = deployList.iterator();
		while (iterator.hasNext()) {
			DeployDomain deployTemp = iterator.next();
			if ((deployTemp.getHost().trim().equalsIgnoreCase(deployDomain.getHost().trim()))&&(deployTemp.getPort()==deployDomain.getPort())) {
				deployList.remove(deployTemp);
				break;
			}
		}
	}
	
	public boolean containsDeployDomain(DeployDomain deployDomain){
		Iterator<DeployDomain> iterator = deployList.iterator();
		while (iterator.hasNext()) {
			DeployDomain deployTemp = iterator.next();
			if ((deployTemp.getHost().trim().equalsIgnoreCase(deployDomain.getHost().trim()))&&(deployTemp.getPort()==deployDomain.getPort())) {
				return true;
			}
		}
		return false;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public List<DeployDomain> getDeployList() {
		return deployList;
	}

	public void setDeployList(List<DeployDomain> deployList) {
		this.deployList = deployList;
	}

	public boolean isIs_valid() {
		return is_valid;
	}

	public void setIs_valid(boolean is_valid) {
		this.is_valid = is_valid;
	}

	public int getSoTimeOut() {
		return soTimeOut;
	}

	public void setSoTimeOut(int soTimeOut) {
		this.soTimeOut = soTimeOut;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
