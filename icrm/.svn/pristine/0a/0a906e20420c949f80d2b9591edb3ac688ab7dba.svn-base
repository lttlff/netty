package com.zjhcsoft.rpc.monitor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.zjhcsoft.rpc.cfg.domain.DeployDomain;

public class DeployStatus {

	private DeployDomain deployDomain;

	private Date createTime;

	private Date lastDetectedDate;
	private List<Date> exceptionDate=new ArrayList<Date>();

	private AtomicLong detectedTimes = new AtomicLong(0);
	
	public DeployStatus(DeployDomain deployDomain){
		this.deployDomain = deployDomain;
		this.createTime = new Date();
		this.lastDetectedDate = new Date();
		this.exceptionDate.add(new Date());
	}

	public DeployDomain getDeployDomain() {
		return deployDomain;
	}

	public void setDeployDomain(DeployDomain deployDomain) {
		this.deployDomain = deployDomain;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastDetectedDate() {
		return lastDetectedDate;
	}

	public void setLastDetectedDate(Date lastDetectedDate) {
		this.lastDetectedDate = lastDetectedDate;
	}

	public AtomicLong getDetectedTimes() {
		return detectedTimes;
	}

	public void setDetectedTimes(AtomicLong detectedTimes) {
		this.detectedTimes = detectedTimes;
	}

	public List<Date> getExceptionDate() {
		return exceptionDate;
	}

	public void addExceptionDate(Date date) {
		this.exceptionDate.add(date);
	}

}
