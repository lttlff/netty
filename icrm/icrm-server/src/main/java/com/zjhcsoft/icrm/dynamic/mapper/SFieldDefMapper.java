package com.zjhcsoft.icrm.dynamic.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.zjhcsoft.icrm.dynamic.domain.SFieldDef;

public interface SFieldDefMapper {
    int deleteByPrimaryKey(String id);

    int insert(SFieldDef record);

    int insertSelective(SFieldDef record);

    SFieldDef selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SFieldDef record);

    int updateByPrimaryKey(SFieldDef record);
    
    List<SFieldDef> selectAllEffective();
    
}