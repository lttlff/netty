package com.zjhcsoft.icrm.customer.domain;

import java.io.Serializable;

/**
 * 
 * @Description: 客户证书信息
 * @Author zhunb
 * @Date 2014-4-24 上午10:03:56
 * 
 */
public class CustSubInfo implements Serializable {
	private static final long serialVersionUID = 9080246672873180554L;

	private String rowId;

	private String custId;

	private Integer regType;

	private String regNbr;

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId == null ? null : rowId.trim();
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId == null ? null : custId.trim();
	}

	public Integer getRegType() {
		return regType;
	}

	public void setRegType(Integer regType) {
		this.regType = regType;
	}

	public String getRegNbr() {
		return regNbr;
	}

	public void setRegNbr(String regNbr) {
		this.regNbr = regNbr == null ? null : regNbr.trim();
	}
}