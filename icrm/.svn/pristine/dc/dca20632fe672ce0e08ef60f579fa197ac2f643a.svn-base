package com.zjhcsoft.rpc.cfg.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.zjhcsoft.rpc.tcp.protocol.TcpConstants;


public class DeployDomain implements Serializable{

	private static final long serialVersionUID = 5661344696289417347L;
	private String name;
	private String host;
	private int port;
	private boolean isDead = false;
	
	private AppDefDomain appDefDomain;
	private String transport_protocol;
	private String data_protocol=TcpConstants.HessianTcpNIO;
	
	private Date startTime;
//	private String description;
	
	private Map<String, String> deployParamMap;

	public void addDeployParamMap(String name, String value) {
		if (this.deployParamMap == null) {
			this.deployParamMap = new HashMap<String, String>();
		}
		this.deployParamMap.put(name, value);
	}

	public Map<String, String> getDeployParamMap() {
		return deployParamMap;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
		if (isDead) {
			appDefDomain.removeDeployServer(this);
		} else {
			if (!appDefDomain.containsDeployDomain(this)) {
				appDefDomain.addDeployServer(this);
			}
		}
	}

	public AppDefDomain getAppDefDomain() {
		return appDefDomain;
	}

	public void setAppDefDomain(AppDefDomain appDefDomain) {
		this.appDefDomain = appDefDomain;
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

}
