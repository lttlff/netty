package com.zjhcsoft.icrm.customer.vo;

import java.io.Serializable;

import com.zjhcsoft.icrm.customer.domain.CustomerAcct;

public class CustomerAcctVO extends CustomerAcct implements Serializable {
	private static final long serialVersionUID = -6784246527206686131L;

	private String addrFull;// 详细地址
	private String custName;// 客户名称
	private String custCode;
	private String contactWay_text;


	public String getAddrFull() {
		return addrFull;
	}

	public void setAddrFull(String addrFull) {
		this.addrFull = addrFull;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getContactWay_text() {
		return contactWay_text;
	}

	public void setContactWay_text(String contactWay_text) {
		this.contactWay_text = contactWay_text;
	}

}