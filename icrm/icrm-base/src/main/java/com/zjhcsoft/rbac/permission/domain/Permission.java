package com.zjhcsoft.rbac.permission.domain;

import java.io.Serializable;
import java.util.Date;

public class Permission implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7668751461584189124L;

	private String  rowId;// 
	private String  createPerson;// 创建人
	private Date  createDate;// 创建时间
	private String  lastPerson;// 最近修改人
	private Date  lastDate;// 最近修改时间
	private String  perCode;// 权限编码
	private String  perName;// 权限名称
	private String  perDesc;// 权限描述
	private Integer  perType;// 权限类型
	private Integer  perSts;// 状态
	private String  menuId;// 菜单外键
	//
	private String perType_text;
	private String menuName;
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

    public String getPerCode() {
        return perCode;
    }

    public void setPerCode(String perCode) {
        this.perCode = perCode == null ? null : perCode.trim();
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName == null ? null : perName.trim();
    }

    public String getPerDesc() {
        return perDesc;
    }

    public void setPerDesc(String perDesc) {
        this.perDesc = perDesc == null ? null : perDesc.trim();
    }

	public Integer getPerType() {
		return perType;
	}

	public void setPerType(Integer perType) {
		this.perType = perType;
	}

	public Integer getPerSts() {
		return perSts;
	}

	public void setPerSts(Integer perSts) {
		this.perSts = perSts;
	}

	public String getPerType_text() {
		return perType_text;
	}

	public void setPerType_text(String perType_text) {
		this.perType_text = perType_text;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
}