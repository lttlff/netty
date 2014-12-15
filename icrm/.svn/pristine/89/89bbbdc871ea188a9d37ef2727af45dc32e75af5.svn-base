package com.zjhcsoft.icrm.dynamic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.icrm.dynamic.domain.CRMDynamicForm;
import com.zjhcsoft.icrm.dynamic.mapper.CRMDynamicFormMapper;

@Service
public class CRMDynamicFormService {

	@Autowired
	private CRMDynamicFormMapper crmDynamicFormMapper;

	public int deleteByPrimaryKey(String id) {
		return crmDynamicFormMapper.deleteByPrimaryKey(id);
	}

	public int insert(CRMDynamicForm record) {
		return crmDynamicFormMapper.insert(record);
	}

	public int insertSelective(CRMDynamicForm record) {
		return crmDynamicFormMapper.insertSelective(record);
	}

	public CRMDynamicForm selectByPrimaryKey(String id) {
		return crmDynamicFormMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(CRMDynamicForm record) {
		return crmDynamicFormMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(CRMDynamicForm record) {
		return crmDynamicFormMapper.updateByPrimaryKey(record);
	}
}
