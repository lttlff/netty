package com.zjhcsoft.icrm.cache.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.icrm.dynamic.domain.SFieldDef;
import com.zjhcsoft.icrm.dynamic.domain.SFormDef;
import com.zjhcsoft.icrm.dynamic.domain.SFormFieldRel;
import com.zjhcsoft.icrm.dynamic.service.SFieldDefService;
import com.zjhcsoft.icrm.dynamic.service.SFormDefService;
import com.zjhcsoft.icrm.dynamic.service.SFormFieldRelService;

@Service
public class DynamicFormCache {

	private static List<SFieldDef> fieldDefList;
	private static List<SFormDef> formDefList;
	private static List<SFormFieldRel> formFieldRelList;
	
	private static Map<String,List<SFormFieldRel>> formFieldRelCache;//key:form_def_id
	private static Map<String,SFieldDef> fieldDefCache;//key:field_def_id
	private static Map<String,SFormDef> formDefCache;//key:form_def_id
	
	@Autowired
	private SFormDefService sformDefService;
	@Autowired
	private SFieldDefService sfieldDefService;
	@Autowired
	private SFormFieldRelService sformFieldRelService;
	
	@PostConstruct
	private void ininCache(){
		if(formFieldRelCache==null){
			formFieldRelCache = new HashMap<String,List<SFormFieldRel>>();
		}
		if(fieldDefCache==null){
			fieldDefCache = new HashMap<String,SFieldDef>();
		}
		if(formDefCache==null){
			formDefCache = new HashMap<String,SFormDef>();
		}
//		load();
	}
	
	private void load(){
		formDefList = sformDefService.selectAllEffective();
		fieldDefList = sfieldDefService.selectAllEffective();
		formFieldRelList = sformFieldRelService.selectAllEffective();
		for(int i=0;i<formDefList.size();i++){
			List<SFormFieldRel> list = new ArrayList<SFormFieldRel>();
			for(int j=0;j<formFieldRelList.size();j++){
				if(formDefList.get(i).getId().equals(formFieldRelList.get(j).getFormDefId())){
					list.add(formFieldRelList.get(j));
				}
			}
			formFieldRelCache.put(formDefList.get(i).getId(), list);
			formDefCache.put(formDefList.get(i).getId(), formDefList.get(i));
		}
		
		for(int i=0;i<fieldDefList.size();i++){
			fieldDefCache.put(fieldDefList.get(i).getId(), fieldDefList.get(i));
		}
		
	}
	
	
	public  SFormDef getFormById(String id){
		return formDefCache.get(id);
	}
	
	public List<SFormFieldRel> getFormFieldRelByFormId(String form_def_id){
		return formFieldRelCache.get(form_def_id);
	}
	
	public SFieldDef getFieldDefById(String field_def_id){
		return fieldDefCache.get(field_def_id);
	}

	public static List<SFieldDef> getFieldDefList() {
		return fieldDefList;
	}

	public static void setFieldDefList(List<SFieldDef> fieldDefList) {
		DynamicFormCache.fieldDefList = fieldDefList;
	}

	public static List<SFormDef> getFormDefList() {
		return formDefList;
	}

	public static void setFormDefList(List<SFormDef> formDefList) {
		DynamicFormCache.formDefList = formDefList;
	}

	public static List<SFormFieldRel> getFormFieldRelList() {
		return formFieldRelList;
	}

	public static void setFormFieldRelList(List<SFormFieldRel> formFieldRelList) {
		DynamicFormCache.formFieldRelList = formFieldRelList;
	}
	
	
	
	
	
}
