package com.zjhcsoft.icrm.dynamic.page.web.form;


public class Hidden extends BaseField {

	public Hidden(String name) {
		super(name);
	}

	public Hidden() {
	}

	private static String TYPE = "type='hidden'";

	@Override
	public String getHtml() {
		StringBuffer htmlBf = new StringBuffer();
		htmlBf.append(INPUT_PRE);
		htmlBf.append(TYPE);
		appendAttrInfos(htmlBf);
		htmlBf.append(INPUT_END);
		return htmlBf.toString();
	}

	@Override
	public String getCssHtml() {
		// TODO Auto-generated method stub
		return "";
	}

}
