package com.zjhcsoft.icrm.customer.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.zjhcsoft.icrm.customer.domain.CustContactsRela;
import com.zjhcsoft.icrm.customer.domain.CustSubInfo;
import com.zjhcsoft.icrm.customer.domain.Customer;

public class CustomerVO extends Customer implements Serializable{

	private static final long serialVersionUID = 22541775360270902L;

	private List<CustSubInfo> custSubInfoList;
	private List<CustContactsRela> custContactsRela;
	private String addrFull;//地址
	private String saleArea;//销售区域

    private String assignDateStr;
    private String stsDateStr;
    private String custType_text;
    private String custCat_text;
    private String regType_text;
    private String custFlag_text;
    private String assignSts_text;
    private String sts_text;
	
	


	public List<CustSubInfo> getCustSubInfoList() {
		return custSubInfoList;
	}

	public void setCustSubInfoList(List<CustSubInfo> custSubInfoList) {
		this.custSubInfoList = custSubInfoList;
	}

	public List<CustContactsRela> getCustContactsRela() {
		return custContactsRela;
	}

	public void setCustContactsRela(List<CustContactsRela> custContactsRela) {
		this.custContactsRela = custContactsRela;
	}

	public String getAddrFull() {
		return addrFull;
	}

	public void setAddrFull(String addrFull) {
		this.addrFull = addrFull;
	}

	public String getSaleArea() {
		return saleArea;
	}

	public void setSaleArea(String saleArea) {
		this.saleArea = saleArea;
	}

	public String getAssignDateStr() {
		return assignDateStr;
	}

	public void setAssignDateStr(String assignDateStr) {
		this.assignDateStr = assignDateStr;
	}

	public String getStsDateStr() {
		return stsDateStr;
	}

	public void setStsDateStr(String stsDateStr) {
		this.stsDateStr = stsDateStr;
	}

	public String getCustType_text() {
		return custType_text;
	}

	public void setCustType_text(String custType_text) {
		this.custType_text = custType_text;
	}

	public String getCustCat_text() {
		return custCat_text;
	}

	public void setCustCat_text(String custCat_text) {
		this.custCat_text = custCat_text;
	}

	public String getRegType_text() {
		return regType_text;
	}

	public void setRegType_text(String regType_text) {
		this.regType_text = regType_text;
	}

	public String getCustFlag_text() {
		return custFlag_text;
	}

	public void setCustFlag_text(String custFlag_text) {
		this.custFlag_text = custFlag_text;
	}

	public String getAssignSts_text() {
		return assignSts_text;
	}

	public void setAssignSts_text(String assignSts_text) {
		this.assignSts_text = assignSts_text;
	}

	public String getSts_text() {
		return sts_text;
	}

	public void setSts_text(String sts_text) {
		this.sts_text = sts_text;
	}
	
	
	
}