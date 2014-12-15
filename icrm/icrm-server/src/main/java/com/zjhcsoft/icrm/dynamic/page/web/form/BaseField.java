package com.zjhcsoft.icrm.dynamic.page.web.form;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class BaseField {

	public static String INPUT_PRE = "<input ";
	public static String INPUT_END = " />";
	public static String LABEL_PRE = "<label>";
	public static String LABEL_END = "</label>";
	public static String BLANK = " ";

	private String id;
	private String name;
	private boolean readOnly;
	private String value;
	private boolean hidden;
	private String label;
	private String cls;
	private boolean allowBlankFlg;
	private Map<String, String> attrMap = null;
	private String dynamicScript;
	

	public abstract String getHtml();
	
	public abstract String getCssHtml();

	
//	public abstract String getBootstrapHtml();
	
	public BaseField(String name) {
		super();
		this.name = name;
	}

	public BaseField() {
		super();
	}

	public String getLabelHtml() {
		if (label != null) {
			if(!this.allowBlankFlg){
				return "<span>"+label+"</span><font color=\"red\">*</font>";
			}else{
				return "<span>"+label+"</span>";
			}
		}
		return "";
	}

	public void appendAttrInfos(StringBuffer htmlBf) {
		if (getAttrMap() != null) {
			Set<String> set = getAttrMap().keySet();
			Iterator<String> it = set.iterator();
			while (it.hasNext()) {
				htmlBf.append(getAttrMap().get(it.next()));
			}
		}
	}

	public Map<String, String> getAttrMap() {
		if (attrMap == null) {
			attrMap = new HashMap<String, String>();
		}
		attrMap.put("name", " name='" + this.name + "' ");
		if (this.cls != null) {
			attrMap.put("class", " class='" + this.cls + "' ");
		}
		if (this.id != null) {
			attrMap.put("id", " id='" + this.id + "' ");
		}
		if (this.readOnly) {
			attrMap.put("readonly", "readonly=\"readonly\"");
		}
		return attrMap;
	}

	public void addAttr(String attr, String value) {
		if (attrMap == null) {
			attrMap = new HashMap<String, String>();
		}
		attrMap.put(attr, attr + "='" + value + "' ");
	}

	public boolean getHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setAttrMap(Map<String, String> attrMap) {
		this.attrMap = attrMap;
	}

	public boolean isAllowBlankFlg() {
		return allowBlankFlg;
	}

	public void setAllowBlankFlg(boolean allowBlankFlg) {
		this.allowBlankFlg = allowBlankFlg;
	}


	public String getDynamicScript() {
		return dynamicScript;
	}


	public void setDynamicScript(String dynamicScript) {
		this.dynamicScript = dynamicScript;
	}

}
