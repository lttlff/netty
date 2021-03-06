package com.zjhcsoft.icrm.sale.domain;

import java.io.Serializable;
import java.util.Date;

public class SaleMethodPeriod implements Serializable {
    private static final long serialVersionUID = 584960820645839490L;

	private String rowId;

    private String createPerson;

    private Date createDate;

    private String lastPerson;

    private Date lastDate;

    private String methodId;

    private String periodId;

    private Integer step;

    private String remark;

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

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId == null ? null : methodId.trim();
    }

    public String getPeriodId() {
        return periodId;
    }

    public void setPeriodId(String periodId) {
        this.periodId = periodId == null ? null : periodId.trim();
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}