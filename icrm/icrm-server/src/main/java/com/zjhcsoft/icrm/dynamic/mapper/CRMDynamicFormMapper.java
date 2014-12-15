package com.zjhcsoft.icrm.dynamic.mapper;

import org.springframework.stereotype.Component;

import com.zjhcsoft.icrm.dynamic.domain.CRMDynamicForm;

@Component
public interface CRMDynamicFormMapper {
    int deleteByPrimaryKey(String id);

    int insert(CRMDynamicForm record);

    int insertSelective(CRMDynamicForm record);

    CRMDynamicForm selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CRMDynamicForm record);

    int updateByPrimaryKey(CRMDynamicForm record);
}