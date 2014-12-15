package com.zjhcsoft.icrm.customer.domain;

import java.io.Serializable;
import java.util.Date;

import com.zjhcsoft.common.DateEditor;

/**
 * 
* @Description: 客户信息
* @author zhunb 
* @Date 2014-4-24 上午9:36:23
*
 */
public class Customer implements Serializable{
	private static final long serialVersionUID = 4507347755865908728L;

	private String rowId;

    private String createPerson;

    private Date createDate;

    private String lastPerson;

    private Date lastDate;

    private String saleAreaId;

    private String custCode;

    private String custName;

    private String custSimple;

    private Integer custType;

    private Integer regType;

    private String regNbr;

    private Integer custCat;

    private String custAddrId;

    private String blackFlag;

    private Integer custFlag;

    private String parent;

    private Integer sts;

    private Date stsDate;

    private Integer assignSts;

    private Date assignDate;

    private String assignDateStr;
    private String stsDateStr;
    private String custType_text;
    private String custCat_text;
    private String regType_text;
    private String custFlag_text;
    private String assignSts_text;
    private String sts_text;
    
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

    public String getSaleAreaId() {
        return saleAreaId;
    }

    public void setSaleAreaId(String saleAreaId) {
        this.saleAreaId = saleAreaId == null ? null : saleAreaId.trim();
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode == null ? null : custCode.trim();
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    public String getCustSimple() {
        return custSimple;
    }

    public void setCustSimple(String custSimple) {
        this.custSimple = custSimple == null ? null : custSimple.trim();
    }

    public Integer getCustType() {
        return custType;
    }

    public void setCustType(Integer custType) {
        this.custType = custType;
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

    public Integer getCustCat() {
        return custCat;
    }

    public void setCustCat(Integer custCat) {
        this.custCat = custCat;
    }

    public String getCustAddrId() {
        return custAddrId;
    }

    public void setCustAddrId(String custAddrId) {
        this.custAddrId = custAddrId == null ? null : custAddrId.trim();
    }

    public String getBlackFlag() {
		return blackFlag;
	}

	public void setBlackFlag(String blackFlag) {
		if("on".equals(blackFlag)||"true".equals(blackFlag)){
			blackFlag = "1";
		}
		this.blackFlag = blackFlag;
	}

	public Integer getCustFlag() {
        return custFlag;
    }

    public void setCustFlag(Integer custFlag) {
        this.custFlag = custFlag;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent == null ? null : parent.trim();
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