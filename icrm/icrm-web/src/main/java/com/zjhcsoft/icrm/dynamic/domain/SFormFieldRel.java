package com.zjhcsoft.icrm.dynamic.domain;

import java.io.Serializable;

public class SFormFieldRel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1950609688524089864L;

	private String id;

    private String formDefId;

    private String fieldDefId;

    private String allowblankFlg;

    private String defaultVal;

    private String hiddenFlg;

    private String readonlyFlg;

    private String groupId;

    private Long orderBy;

    private String dynamicScript;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFormDefId() {
        return formDefId;
    }

    public void setFormDefId(String formDefId) {
        this.formDefId = formDefId == null ? null : formDefId.trim();
    }

    public String getFieldDefId() {
        return fieldDefId;
    }

    public void setFieldDefId(String fieldDefId) {
        this.fieldDefId = fieldDefId == null ? null : fieldDefId.trim();
    }

    public String getAllowblankFlg() {
        return allowblankFlg;
    }

    public void setAllowblankFlg(String allowblankFlg) {
        this.allowblankFlg = allowblankFlg == null ? null : allowblankFlg.trim();
    }

    public String getDefaultVal() {
        return defaultVal;
    }

    public void setDefaultVal(String defaultVal) {
        this.defaultVal = defaultVal == null ? null : defaultVal.trim();
    }

    public String getHiddenFlg() {
        return hiddenFlg;
    }

    public void setHiddenFlg(String hiddenFlg) {
        this.hiddenFlg = hiddenFlg == null ? null : hiddenFlg.trim();
    }

    public String getReadonlyFlg() {
        return readonlyFlg;
    }

    public void setReadonlyFlg(String readonlyFlg) {
        this.readonlyFlg = readonlyFlg == null ? null : readonlyFlg.trim();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public Long getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Long orderBy) {
        this.orderBy = orderBy;
    }

    public String getDynamicScript() {
        return dynamicScript;
    }

    public void setDynamicScript(String dynamicScript) {
        this.dynamicScript = dynamicScript == null ? null : dynamicScript.trim();
    }
}