package com.zjhcsoft.icrm.dynamic.page.web.form;

public class Trigger extends BaseField{

	private static String TYPE="type='text'";
	
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
			htmlBf.append("value='"+this.getValue()+"' ");
		}
		htmlBf.append("readonly='readonly' ");
		htmlBf.append("class='trigger' ");
		htmlBf.append("_script=\""+this.getDynamicScript()+"\" ");
		appendAttrInfos(htmlBf);
		htmlBf.append(INPUT_END);
		htmlBf.append(FieldConstant.TD_END);
		htmlBf.append(FieldConstant.TR_END);
		return htmlBf.toString();
	}

	@Override
	public String getCssHtml() {
		return "";
	}

}
