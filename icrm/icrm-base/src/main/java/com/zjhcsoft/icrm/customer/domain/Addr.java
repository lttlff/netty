package com.zjhcsoft.icrm.customer.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 
* @Description: 地址信息
* @Author zhunb 
* @Date 2014-4-24 上午9:52:41
*
 */
public class Addr implements Serializable{

	private static final long serialVersionUID = 8997542285003087772L;

	private String rowId;

    private String createPerson;

    private Date createDate;

    private String lastPerson;

    private Date lastDate;

    private String addrCode;

    private String address;

    private String addrSimple;

    private Integer addrGrade;

    private String parentAddrId;

    private String postCode;

    private Integer sts;

    private Date stsDate;

    private Integer addrLevel;

    private String addrFull;

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

    public String getAddrCode() {
        return addrCode;
    }

    public void setAddrCode(String addrCode) {
        this.addrCode = addrCode == null ? null : addrCode.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAddrSimple() {
        return addrSimple;
    }

    public void setAddrSimple(String addrSimple) {
        this.addrSimple = addrSimple == null ? null : addrSimple.trim();
    }

    public Integer getAddrGrade() {
        return addrGrade;
    }

    public void setAddrGrade(Integer addrGrade) {
        this.addrGrade = addrGrade;
    }

    public String getParentAddrId() {
        return parentAddrId;
    }

    public void setParentAddrId(String parentAddrId) {
        this.parentAddrId = parentAddrId == null ? null : parentAddrId.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
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

    public Integer getAddrLevel() {
        return addrLevel;
    }

    public void setAddrLevel(Integer addrLevel) {
        this.addrLevel = addrLevel;
    }

    public String getAddrFull() {
        return addrFull;
    }

    public void setAddrFull(String addrFull) {
        this.addrFull = addrFull == null ? null : addrFull.trim();
    }
}