package com.zjhcsoft.icrm.customer.domain;

import java.io.Serializable;
import java.util.Date;

public class CustomerAcct implements Serializable{
    private static final long serialVersionUID = 5873717795438739451L;

	private String rowId;

    private String createPerson;

    private Date createDate;

    private String lastPerson;

    private Date lastDate;

    private String acctNbr;

    private String acctName;

    private String acctSimple;

    private String custId;

    private String branchName;

    private String bankAcct;

    private String acctAddrId;

    private String acctMail;

    private Integer sts;

    private Date stsDate;

    private String remark;

	private String contactWay_text;

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId == null ? null : rowId.trim();
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastPerson() {
        return lastPerson;
    }

    public void setLastPerson(String lastPerson) {
        this.lastPerson = lastPerson == null ? null : lastPerson.trim();
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public String getAcctNbr() {
        return acctNbr;
    }

    public void setAcctNbr(String acctNbr) {
        this.acctNbr = acctNbr == null ? null : acctNbr.trim();
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName == null ? null : acctName.trim();
    }

    public String getAcctSimple() {
        return acctSimple;
    }

    public void setAcctSimple(String acctSimple) {
        this.acctSimple = acctSimple == null ? null : acctSimple.trim();
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId == null ? null : custId.trim();
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName == null ? null : branchName.trim();
    }

    public String getBankAcct() {
        return bankAcct;
    }

    public void setBankAcct(String bankAcct) {
        this.bankAcct = bankAcct == null ? null : bankAcct.trim();
    }

    public String getAcctAddrId() {
        return acctAddrId;
    }

    public void setAcctAddrId(String acctAddrId) {
        this.acctAddrId = acctAddrId == null ? null : acctAddrId.trim();
    }

    public String getAcctMail() {
        return acctMail;
    }

    public void setAcctMail(String acctMail) {
        this.acctMail = acctMail == null ? null : acctMail.trim();
    }

    public Integer getSts() {
        return sts;
    }

    public void setSts(Integer sts) {
        this.sts = sts;
    }

    public Date getStsDate() {
        return stsDate;
    }

    public void setStsDate(Date stsDate) {
        this.stsDate = stsDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getContactWay_text() {
		return contactWay_text;
	}

	public void setContactWay_text(String contactWay_text) {
		this.contactWay_text = contactWay_text;
	}
    
}