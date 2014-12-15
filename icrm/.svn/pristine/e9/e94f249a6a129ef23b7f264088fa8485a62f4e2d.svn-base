package com.zjhcsoft.rbac.organise.mapper;

import java.util.List;
import java.util.Map;

import com.zjhcsoft.rbac.organise.domain.Organise;

public interface OrganiseMapper {
    int deleteByPrimaryKey(String rowId);

    int insert(Organise record);

    int insertSelective(Organise record);

    Organise selectByPrimaryKey(String rowId);

    int updateByPrimaryKeySelective(Organise record);

    int updateByPrimaryKey(Organise record);
    
    public List<Organise> getOrganiseTreeByMap(Map map);
    
    public List<Organise> getPageList(Map<String, Object> map);
    
    public long getPageCount(Map<String, Object> map);
}