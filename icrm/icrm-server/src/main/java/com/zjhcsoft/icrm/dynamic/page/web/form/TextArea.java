package com.zjhcsoft.icrm.dynamic.page.web.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zjhcsoft.common.util.VelocityUtil;
import com.zjhcsoft.icrm.dynamic.page.web.form.vm.VMconstant;


public class TextArea extends BaseField {

	public TextArea(String name) {
		super(name);
	}


	public TextArea() {
	}

	@Override
	public String getHtml() {
		StringBuffer htmlBf = new StringBuffer();
		htmlBf.append(FieldConstant.TR_PRE);
		htmlBf.append(FieldConstant.TD_PRE_LABEL);
		htmlBf.append(getLabelHtml());
		htmlBf.append(FieldConstant.TD_END);
		htmlBf.append(FieldConstant.TD_PRE_CONTENT);
		htmlBf.append("<textarea  rows=\"6\" cols=\"55\" resize='' ");
		appendAttrInfos(htmlBf);
		htmlBf.append(">");
		if(this.getValue()!=null){
			htmlBf.append(this.getValue());
		}
		htmlBf.append("</textarea>");
		htmlBf.append(FieldConstant.TD_END);
		htmlBf.append(FieldConstant.TR_END);

		return htmlBf.toString();
	}


	@Override
	public String getCssHtml() {
		Map<String,String> params = new HashMap<String,String>();
		params.put("label", this.getLabel());
		params.put("name", this.getName());
		params.put("value", "文本域");
		return VelocityUtil.getVelocityXmlStr(VMconstant.TEXTAREA, params);
	}

}
