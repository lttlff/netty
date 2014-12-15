package com.zjhcsoft.rbac.organise.domain;

import java.io.Serializable;
import java.util.Date;

public class Organise implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3516041492654766798L;

	private String  rowId;// ROW_ID
	private String  createPerson;// 创建人
	private Date  createDate;// 创建时间
	private String  lastPerson;// 最近修改人
	private Date  lastDate;// 最近修改时间
	private String  orgCode;// 组织编码
	private String  orgName;// 组织名称
	private Integer  orgLevel;// 组织级别
	private Integer  orgType;// 组织类型
	private String  parOrgId;// 上级组织
	private String  orgDesc;// 组织描述
	private String  orgPath;// 组织全称
	private Integer  orgSts;// 状态
	private Integer  leafFlg;// 是否叶子节点

	//
	private String orgType_text;
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

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getParOrgId() {
        return parOrgId;
    }

    public void setParOrgId(String parOrgId) {
        this.parOrgId = parOrgId == null ? null : parOrgId.trim();
    }

    public String getOrgDesc() {
        return orgDesc;
    }

    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc == null ? null : orgDesc.trim();
    }

    public String getOrgPath() {
        return orgPath;
    }

    public void setOrgPath(String orgPath) {
        this.orgPath = orgPath == null ? null : orgPath.trim();
    }

	public Integer getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
	}

	public Integer getOrgType() {
		return orgType;
	}

	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}

	public Integer getOrgSts() {
		return orgSts;
	}

	public void setOrgSts(Integer orgSts) {
		this.orgSts = orgSts;
	}

	public Integer getLeafFlg() {
		return leafFlg;
	}

	public void setLeafFlg(Integer leafFlg) {
		this.leafFlg = leafFlg;
	}

	public String getOrgType_text() {
		return orgType_text;
	}

	public void setOrgType_text(String orgType_text) {
		this.orgType_text = orgType_text;
	}
}