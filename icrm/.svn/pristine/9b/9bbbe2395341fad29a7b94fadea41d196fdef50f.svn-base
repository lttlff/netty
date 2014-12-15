package com.zjhcsoft.icrm.dynamic.mapper;

import java.util.List;

import com.zjhcsoft.icrm.dynamic.domain.SFormFieldRel;

public interface SFormFieldRelMapper {
    int deleteByPrimaryKey(String id);

    int insert(SFormFieldRel record);

    int insertSelective(SFormFieldRel record);

    SFormFieldRel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SFormFieldRel record);

    int updateByPrimaryKeyWithBLOBs(SFormFieldRel record);

    int updateByPrimaryKey(SFormFieldRel record);
    
    List<SFormFieldRel> selectAllEffective();
    
    List<SFormFieldRel> selectByFormId(String form_def_id);
}