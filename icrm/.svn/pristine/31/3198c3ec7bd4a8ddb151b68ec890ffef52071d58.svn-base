package com.zjhcsoft.icrm.dynamic.page.web.form;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zjhcsoft.common.util.VelocityUtil;
import com.zjhcsoft.icrm.dynamic.page.web.form.vm.VMconstant;


public class ComboBox extends BaseField {

	private String combo_code;

	public ComboBox(String name) {
		super(name);
	}

	public ComboBox() {
	}

	public String getHtml() {
		StringBuffer htmlBf = new StringBuffer();
		htmlBf.append(FieldConstant.TR_PRE);
		htmlBf.append(FieldConstant.TD_PRE_LABEL);
		htmlBf.append(getLabelHtml());
		htmlBf.append(FieldConstant.TD_END);
		htmlBf.append(FieldConstant.TD_PRE_CONTENT);
		htmlBf.append("<select");
		appendAttrInfos(htmlBf);
		htmlBf.append(">");
		htmlBf.append(getOptions());
		htmlBf.append("</select>");
		htmlBf.append(FieldConstant.TD_END);
		htmlBf.append(FieldConstant.TR_END);
		return htmlBf.toString();
	}

	private String getOptions() {
		return "<option>杭州</option>" + "<option>宁波</option>"
				+ "<option>温州</option>";
	}

	public static void main(String[] args) {
		Method[] methods = ComboBox.class.getMethods();
		for(int i=0;i<methods.length;i++){
			System.out.println(methods[i].getName());
		}
	}

	public String getCombo_code() {
		return combo_code;
	}

	public void setCombo_code(String combo_code) {
		this.combo_code = combo_code;
	}

	@Override
	public String getCssHtml() {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("label", this.getLabel());
		params.put("name", this.getName());
		List<Option> options = new ArrayList<Option>();
		Option option;
		for(int i=0;i<4;i++){
			option = new Option();
			option.setValue(String.valueOf(i));
			option.setText("杭州"+i);
			options.add(option);
		}
//		List<String> options = new ArrayList<String>();
//		options.add("杭州限牌");
//		options.add("浙江省");
//		options.add("杭州市");
		params.put("options", options);
		return VelocityUtil.getVelocityXmlStr(VMconstant.COMBOBOX, params);
	}
	
}
