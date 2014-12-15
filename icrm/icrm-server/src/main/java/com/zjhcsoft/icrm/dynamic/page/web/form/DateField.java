package com.zjhcsoft.icrm.dynamic.page.web.form;


public class DateField extends BaseField {

	public DateField(String name) {
		super(name);
	}

	public DateField() {
	}

	private static String TYPE = "type='text' class=\"Wdate\"";

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
		htmlBf.append("onClick=\"WdatePicker()\" ");
		appendAttrInfos(htmlBf);
		htmlBf.append(INPUT_END);
		htmlBf.append(FieldConstant.TD_END);
		htmlBf.append(FieldConstant.TR_END);
		return htmlBf.toString();
	}

	@Override
	public String getCssHtml() {
		// TODO Auto-generated method stub
		return "";
	}

}
