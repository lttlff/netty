package com.zjhcsoft.icrm.dynamic.page.web.form;

import java.util.HashMap;
import java.util.Map;

import com.zjhcsoft.common.util.VelocityUtil;
import com.zjhcsoft.icrm.dynamic.page.web.form.vm.VMconstant;


public class CheckBox extends BaseField{

	private static final String TEMPLATE = "com/zjhcsoft/icrm/dynamic/page/web/form/vm/TextField.vm";
	
	public CheckBox(String name) {
		super(name);
	}


	public CheckBox() {
		super();
	}


	private static String TYPE="type='checkbox' ";

	
	@Override
	public String getHtml() {
		StringBuffer htmlBf = new StringBuffer();
		htmlBf.append(FieldConstant.TR_PRE);
		htmlBf.append(FieldConstant.TD_PRE_LABEL);
		htmlBf.append(getLabelHtml());
		htmlBf.append(FieldConstant.TD_END);
		htmlBf.append(FieldConstant.TD_PRE_CONTENT);
		htmlBf.append(INPUT_PRE);
		htmlBf.append(TYPE);
		if(this.getValue()!=null){
			htmlBf.append("checked=\"checked\" ");
		}
		appendAttrInfos(htmlBf);
		htmlBf.append(INPUT_END);
		htmlBf.append(FieldConstant.TD_END);
		htmlBf.append(FieldConstant.TR_END);
		return htmlBf.toString();
	}


	@Override
	public String getCssHtml() {
		Map<String,String> params = new HashMap<String,String>();
		params.put("label", this.getLabel());
		params.put("name", this.getName());
		params.put("type", "checkbox");
		return VelocityUtil.getVelocityXmlStr(VMconstant.BASEFIELD, params);
	}

}
