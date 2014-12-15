package com.zjhcsoft.icrm.dynamic.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.icrm.cache.service.DynamicFormCache;
import com.zjhcsoft.icrm.dynamic.DynamicJsonUtil;
import com.zjhcsoft.icrm.dynamic.domain.CRMDynamicForm;
import com.zjhcsoft.icrm.dynamic.domain.SFormDef;
import com.zjhcsoft.icrm.dynamic.domain.SFormFieldRel;
import com.zjhcsoft.icrm.dynamic.page.server.bean.BaseBean;
import com.zjhcsoft.icrm.dynamic.page.server.bean.FormElementBean;

@Service
public class DynamicFormService {

	private static final Logger logger = Logger
			.getLogger(DynamicFormService.class);

	@Autowired
	private DynamicFormCache dynamicFormCache;

	@Autowired
	private CRMDynamicFormService crmDynamicFormService;
	
	public void insertDynamicForm(String dynamicJson){
		
		CRMDynamicForm record = new CRMDynamicForm();
		record.setFormDefId("1");
		record.setData(dynamicJson);
		int c = crmDynamicFormService.insertSelective(record);
	}
	
	public void updateDynamicForm(String dynamicJson,String id) {
		CRMDynamicForm record = new CRMDynamicForm();
		record.setId(id);
		record.setData(dynamicJson);
		int c = crmDynamicFormService.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 
	 * @param request
	 * @param id
	 * 方法功能说明:表单对应的JSON value设置到request
	 * @author	zhunb  2014-3-10
	 */
	public void setValueToRequestAttr(HttpServletRequest request, String id) {
		CRMDynamicForm crmDynamicForm = crmDynamicFormService.selectByPrimaryKey(id);
		String jsonStr = crmDynamicForm.getData();
		Map map = DynamicJsonUtil.getMap4Json(jsonStr);
		 Iterator<String> it = map.keySet().iterator();
		 String key = null;
		 while(it.hasNext()){
			 key = it.next();
			 request.setAttribute(key,map.get(key));
		 }
		
	}
	
	public String getDynamicFormJsonPageById(String form_id) {
		SFormDef sformDef = dynamicFormCache.getFormById(form_id);
		List<SFormFieldRel> formFieldRelList = dynamicFormCache
				.getFormFieldRelByFormId(form_id);
		BaseBean webBean = null;
		List<BaseBean> beanArr = new ArrayList<BaseBean>();
		SFormFieldRel formFieldRel;
		for (int i = 0; i < formFieldRelList.size(); i++) {
			formFieldRel = formFieldRelList.get(i);
			String cc = formFieldRel.getFieldDefId();
			if ("fieldset".equals(dynamicFormCache.getFieldDefById(
					formFieldRel.getFieldDefId()).getFieldType())) {
				webBean = new BaseBean();
				webBean.setLabel(dynamicFormCache.getFieldDefById(
						formFieldRel.getFieldDefId()).getFieldName());
				webBean.setName(dynamicFormCache.getFieldDefById(
						formFieldRel.getFieldDefId()).getFieldCode());
				webBean.setType(dynamicFormCache.getFieldDefById(
						formFieldRel.getFieldDefId()).getFieldType());
				List<SFormFieldRel> fieldSetFormFieldRelList = dynamicFormCache
						.getFormFieldRelByFormId(formFieldRel.getFieldDefId());
				List<FormElementBean> fieldSetBeanArr = new ArrayList<FormElementBean>();
				FormElementBean fieldSetElementBean;
				for (int j = 0; j < fieldSetFormFieldRelList.size(); j++) {
					fieldSetElementBean = new FormElementBean(
							fieldSetFormFieldRelList.get(j).getAllowblankFlg(),
							fieldSetFormFieldRelList.get(j).getHiddenFlg(),
							fieldSetFormFieldRelList.get(j).getReadonlyFlg(),
							fieldSetFormFieldRelList.get(j).getDynamicScript());
					fieldSetElementBean.setValue(fieldSetFormFieldRelList
							.get(j).getDefaultVal());
					fieldSetElementBean.setLabel(dynamicFormCache
							.getFieldDefById(fieldSetFormFieldRelList.get(j).getFieldDefId())
							.getFieldName());
					fieldSetElementBean.setName(dynamicFormCache
							.getFieldDefById(fieldSetFormFieldRelList.get(j).getFieldDefId())
							.getFieldCode());
					fieldSetElementBean.setType(dynamicFormCache
							.getFieldDefById(fieldSetFormFieldRelList.get(j).getFieldDefId())
							.getFieldType());
					fieldSetBeanArr.add(fieldSetElementBean);
				}
				webBean.setValue(fieldSetBeanArr);
			} else {
				webBean = new FormElementBean(formFieldRel.getAllowblankFlg(),
						formFieldRel.getHiddenFlg(),
						formFieldRel.getReadonlyFlg(),
						formFieldRel.getDynamicScript());
				webBean.setValue(formFieldRel.getDefaultVal());
				webBean.setLabel(dynamicFormCache.getFieldDefById(
						formFieldRel.getFieldDefId()).getFieldName());
				webBean.setName(dynamicFormCache.getFieldDefById(
						formFieldRel.getFieldDefId()).getFieldCode());
				webBean.setType(dynamicFormCache.getFieldDefById(
						formFieldRel.getFieldDefId()).getFieldType());
			}
			beanArr.add(webBean);
		}

		String str = DynamicJsonUtil.getJsonString4JavaArr(beanArr);

		logger.info(str);
		return str;
	}

	








	
	
}
