package com.zjhcsoft.icrm.dynamic.page.web;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.icrm.cache.service.DynamicFormCache;
import com.zjhcsoft.icrm.dynamic.domain.SFieldDef;
import com.zjhcsoft.icrm.dynamic.domain.SFormFieldRel;
import com.zjhcsoft.icrm.dynamic.mapper.SFormFieldRelMapper;
import com.zjhcsoft.icrm.dynamic.page.web.annotation.WebElementType;
import com.zjhcsoft.icrm.dynamic.page.web.form.BaseField;
import com.zjhcsoft.icrm.dynamic.page.web.form.CheckBox;
import com.zjhcsoft.icrm.dynamic.page.web.form.ComboBox;
import com.zjhcsoft.icrm.dynamic.page.web.form.DateField;
import com.zjhcsoft.icrm.dynamic.page.web.form.Hidden;
import com.zjhcsoft.icrm.dynamic.page.web.form.TextArea;
import com.zjhcsoft.icrm.dynamic.page.web.form.TextField;

@Service
public class FormEngine {

	@Autowired
	private SFormFieldRelMapper sformFieldRelMapper;
	@Autowired
	private DynamicFormCache dynamicFormCache;

	
	
	public String getDefineFormHtmlById(String form_def_id) {
		List fieldList = getWebFormFieldList(form_def_id);
		try {
			return  produceHtmlForm(fieldList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String produceHtmlForm(List fieldList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		StringBuffer formInnerHtml = new StringBuffer();
		Object field = null;
		for (int i = 0; i < fieldList.size(); i++) {
			field = fieldList.get(i);
			formInnerHtml.append(field.getClass().getDeclaredMethod("getHtml", null)
					.invoke(field,  new Object[0]));
			formInnerHtml.append("</br>");
		}
		return formInnerHtml.toString();
	}

	private List getWebFormFieldList(String form_def_id) {
		List<SFormFieldRel> formFieldList = sformFieldRelMapper
				.selectByFormId(form_def_id);
		SFormFieldRel sfr;
		SFieldDef fd;
		List list = new ArrayList();
		BaseField field = null;
		for (int i = 0; i < formFieldList.size(); i++) {
			sfr = formFieldList.get(i);
			fd = dynamicFormCache.getFieldDefById(sfr.getFieldDefId());
			switch (WebElementType.toType(fd.getFieldType().toUpperCase())) {
			case TEXT:
				field = new TextField();
				break;
			case TEXTAREA:
				field = new TextArea();
				break;
			case COMBO:
				field = new ComboBox();
				break;
			case HIDDEN:
				field = new Hidden();
				break;
			case DATE:
				field = new DateField();
				break;
			case NUMBERER:
				break;
			case TRIGGER:
				break;
			case CHECKBOX:
				field = new CheckBox();
				break;
			default:
				break;
			}
			field.setName(fd.getFieldCode());
			field.setLabel(fd.getFieldName());
			list.add(field);
		}
		return list;
	}

}
