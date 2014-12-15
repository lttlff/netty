package com.zjhcsoft.rbac.permission.mapper;

import java.util.List;
import java.util.Map;

import com.zjhcsoft.rbac.permission.domain.Permission;

public interface PermissionMapper {
    int deleteByPrimaryKey(String rowId);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(String rowId);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    public List<Permission>  getPermissionsByRole(Map<String, Object> map);
    
    public List<Permission>  getUnPermissionsByRole(Map<String, Object> map);
    
    public List<Permission> getPageList(Map<String, Object> map);
    
    public long getPageCount(Map<String, Object> map);
}