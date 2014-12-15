package com.zjhcsoft.icrm.dynamic.page.web.form;

import java.util.HashMap;
import java.util.Map;

import com.zjhcsoft.common.util.VelocityUtil;
import com.zjhcsoft.icrm.dynamic.page.web.form.vm.VMconstant;


public class TextField extends BaseField {

	public TextField() {
		super();
	}



	private static String TYPE="type='text'";
	
	
	
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
			htmlBf.append("value='"+this.getValue()+"' ");
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
		params.put("type", "text");
		return VelocityUtil.getVelocityXmlStr(VMconstant.BASEFIELD, params);
	}

	
	
}
