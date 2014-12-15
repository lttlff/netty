package com.zjhcsoft.icrm.contacts.domain;

import java.io.Serializable;
import java.util.Date;

import com.zjhcsoft.common.DateEditor;

/**
 * 
* @Description: 联系人信息
* @Author zhunb 
* @Date 2014-4-24 上午9:48:35
*
 */
public class Contacts implements Serializable{
	private static final long serialVersionUID = -5978406107770938251L;

	private String rowId;

    private String createPerson;

    private Date createDate;

    private String lastPerson;

    private Date lastDate;

    private String contactsName;

    private String contactsSimple;

    private String contactsAddrId;

    private String department;

    private String position;

    private Date birthday;

    private String hobby;

    private String workContent;

    private Integer assignSts;

    private Date assignDate;

    private Integer sts;

    private Date stsDate;
    
    /* 以下非表字段 */
    private String assignDateStr;
    private String stsDateStr;
    private String birthdayStr;
    private String addrFull;
    
    
    
    public String getStsDateStr() {
		return stsDateStr;
	}

	public void setStsDateStr(String stsDateStr) {
		this.stsDateStr = stsDateStr;
	}

	public String getAssignDateStr() {
		return assignDateStr;
	}

	public void setAssignDateStr(String assignDateStr) {
		this.assignDateStr = assignDateStr;
	}

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

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName == null ? null : contactsName.trim();
    }

    public String getContactsSimple() {
        return contactsSimple;
    }

    public void setContactsSimple(String contactsSimple) {
        this.contactsSimple = contactsSimple == null ? null : contactsSimple.trim();
    }

    public String getContactsAddrId() {
        return contactsAddrId;
    }

    public void setContactsAddrId(String contactsAddrId) {
        this.contactsAddrId = contactsAddrId == null ? null : contactsAddrId.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
    	try {
    		this.birthdayStr = DateEditor.getDateAsText(birthday);
		} catch (Exception e) {
		}
        this.birthday = birthday;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby == null ? null : hobby.trim();
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent == null ? null : workContent.trim();
    }

    public Integer getAssignSts() {
        return assignSts;
    }

    public void setAssignSts(Integer assignSts) {
        this.assignSts = assignSts;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
    	try {
    		this.assignDateStr = DateEditor.getDateAsText(assignDate);
		} catch (Exception e) {
		}
        this.assignDate = assignDate;
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
    	try {
    		this.stsDateStr = DateEditor.getDateAsText(stsDate);
		} catch (Exception e) {
		}
        this.stsDate = stsDate;
    }

	public String getBirthdayStr() {
		return birthdayStr;
	}

	public void setBirthdayStr(String birthdayStr) {
		this.birthdayStr = birthdayStr;
	}

	public String getAddrFull() {
		return addrFull;
	}

	public void setAddrFull(String addrFull) {
		this.addrFull = addrFull;
	}
    
}