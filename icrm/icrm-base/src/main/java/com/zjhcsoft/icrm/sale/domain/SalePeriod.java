package com.zjhcsoft.icrm.sale.domain;

import java.io.Serializable;
import java.util.Date;

public class SalePeriod implements Serializable {
    private static final long serialVersionUID = -7844518303048187854L;

	private String rowId;

    private String createPerson;

    private Date createDate;

    private String lastPerson;

    private Date lastDate;

    private String salePeriodName;

    private String periodDesc;

    private Integer seq;

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

    public String getSalePeriodName() {
        return salePeriodName;
    }

    public void setSalePeriodName(String salePeriodName) {
        this.salePeriodName = salePeriodName == null ? null : salePeriodName.trim();
    }

    public String getPeriodDesc() {
        return periodDesc;
    }

    public void setPeriodDesc(String periodDesc) {
        this.periodDesc = periodDesc == null ? null : periodDesc.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}