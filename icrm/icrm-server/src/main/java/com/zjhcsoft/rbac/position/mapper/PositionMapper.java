package com.zjhcsoft.rbac.position.mapper;

import java.util.List;
import java.util.Map;

import com.zjhcsoft.rbac.position.domain.Position;

public interface PositionMapper {
    int deleteByPrimaryKey(String rowId);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(String rowId);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);
    
    public List<Position> getPositionTree(Map<Object, Object> map);
    
    public List<Position> getPageList(Map<String, Object> map);
    
    public long getPageCount(Map<String, Object> map);
    
    public void insertPositionRoles(Map<String, Object> map);
    
    public void deletePositionRoles(Map<String, Object> map);
}