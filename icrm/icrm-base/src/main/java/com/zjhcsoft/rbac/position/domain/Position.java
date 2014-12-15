package com.zjhcsoft.rbac.position.domain;

import java.io.Serializable;
import java.util.Date;

public class Position implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6721166787219401841L;
	private String  rowId;// 
	private String  createPerson;// 创建人
	private Date  createDate;// 创建时间
	private String  lastPerson;// 最近修改人
	private Date  lastDate;// 最近修改时间
	private String  posName;// 职位名称
	private Integer  posType;// 职位类型
	private String  orgId;// 所属部门
	private String  posDesc;// 职位描述
	private String  parPosId;// 上级职位
	//
	private String orgName;
	private String posType_text;

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

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName == null ? null : posName.trim();
    }


    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getPosDesc() {
        return posDesc;
    }

    public void setPosDesc(String posDesc) {
        this.posDesc = posDesc == null ? null : posDesc.trim();
    }

    public String getParPosId() {
        return parPosId;
    }

    public void setParPosId(String parPosId) {
        this.parPosId = parPosId == null ? null : parPosId.trim();
    }

	public Integer getPosType() {
		return posType;
	}

	public void setPosType(Integer posType) {
		this.posType = posType;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPosType_text() {
		return posType_text;
	}

	public void setPosType_text(String posType_text) {
		this.posType_text = posType_text;
	}
}