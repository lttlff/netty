package com.zjhcsoft.icrm.dynamic.page.web;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.icrm.cache.service.DynamicFormCache;
import com.zjhcsoft.icrm.dynamic.DynamicJsonUtil;
import com.zjhcsoft.icrm.dynamic.domain.CRMDynamicForm;
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
import com.zjhcsoft.icrm.dynamic.page.web.form.Trigger;
import com.zjhcsoft.icrm.dynamic.service.CRMDynamicFormService;
import com.zjhcsoft.rpc.context.impl.Request;

@Service
public class FormValueEngine {

	@Autowired
	private SFormFieldRelMapper sformFieldRelMapper;
	@Autowired
	private DynamicFormCache dynamicFormCache;

	@Autowired
	private CRMDynamicFormService crmDynamicFormService;

	public String get4FormHtmlById(String crm_dynamic_form_id) {
		CRMDynamicForm f = crmDynamicFormService
				.selectByPrimaryKey(crm_dynamic_form_id);
		List<String> fieldList = getWebFormFieldList(f.getFormDefId());
		Map map = DynamicJsonUtil.getMap4Json(f.getData());
		try {
			return produceHtmlForm(fieldList, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getFormHtmlById() {
		String crm_dynamic_form_id=(String) Request.getAttribute("id");
		CRMDynamicForm f = crmDynamicFormService
				.selectByPrimaryKey(crm_dynamic_form_id);
		List<String> fieldList = getWebFormFieldList(f.getFormDefId());
		Map map = DynamicJsonUtil.getMap4Json(f.getData());
		try {
			return produceHtmlForm(fieldList, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String produceHtmlForm(List fieldList, Map map)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		StringBuffer formInnerHtml = new StringBuffer();
		BaseField field = null;
		for (int i = 0; i < fieldList.size(); i++) {
			field = (BaseField) fieldList.get(i);
			field.setValue(String.valueOf(map.get(field.getName())));
			formInnerHtml.append(field.getCssHtml());
		}
		return formInnerHtml.toString();
	}

	private List getWebFormFieldList(String form_def_id) {
		List<SFormFieldRel> formFieldList = sformFieldRelMapper
				.selectByFormId(form_def_id);
		SFormFieldRel sfr;
		SFieldDef fd;
		List<BaseField> list = new ArrayList<BaseField>();
		for (int i = 0; i < formFieldList.size(); i++) {
			BaseField field = null;
			sfr = formFieldList.get(i);
			fd = dynamicFormCache.getFieldDefById(sfr.getFieldDefId());
			switch (WebElementType.toType(fd.getFieldType())) {
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
				field = new Trigger();
				break;
			case CHECKBOX:
				field = new CheckBox();
				break;
			default:
				break;
			}
			if (field == null) {
				continue;
			}
			field.setName(fd.getFieldCode());
			field.setLabel(fd.getFieldName());
			field.setDynamicScript(sfr.getDynamicScript());
			list.add(field);
		}
		return list;
	}

	
}
