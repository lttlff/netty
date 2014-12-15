package com.zjhcsoft.icrm.cache.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @Description: 值列表code
 * @Author zhunb
 * @Date 2014-5-5 上午9:46:39
 * 
 */
public class SysConfigDefDao extends SysConfigDef implements Serializable {

	private static final long serialVersionUID = -8627213192245862069L;
	private List<SysConfigValue> configValueList;
	private String fieldCode;

	public List<SysConfigValue> getConfigValueList() {
		return configValueList;
	}

	public void setConfigValueList(List<SysConfigValue> configValueList) {
		this.configValueList = configValueList;
	}

	public String getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

}
