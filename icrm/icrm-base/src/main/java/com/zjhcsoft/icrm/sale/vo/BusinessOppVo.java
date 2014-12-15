package com.zjhcsoft.icrm.sale.vo;

import java.io.Serializable;
import java.util.List;

import com.zjhcsoft.icrm.contacts.domain.CommRecord;
import com.zjhcsoft.icrm.sale.domain.BusinessOpp;
import com.zjhcsoft.icrm.sale.domain.SalePeriod;

public class BusinessOppVo extends BusinessOpp implements Serializable {

	private static final long serialVersionUID = -4136738477220911328L;

	private String custName;
	private String prodName;
	
	private SalePeriod salePeriod;//商机关联的阶段
	
	private List<CommRecord> commRecordList;//商机沟通记录表
	

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public SalePeriod getSalePeriod() {
		return salePeriod;
	}

	public void setSalePeriod(SalePeriod salePeriod) {
		this.salePeriod = salePeriod;
	}

	public List<CommRecord> getCommRecordList() {
		return commRecordList;
	}

	public void setCommRecordList(List<CommRecord> commRecordList) {
		this.commRecordList = commRecordList;
	}

}