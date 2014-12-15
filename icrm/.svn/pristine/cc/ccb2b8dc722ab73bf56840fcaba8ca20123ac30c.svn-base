package com.zjhcsoft.rbac.role.mapper;

import java.util.List;
import java.util.Map;

import com.zjhcsoft.rbac.role.domain.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(String rowId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String rowId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    public List<Role> getPageList(Map<String, Object> map);
    
    long getTotalSize(Map<String, Object> map);
    
    public List<Role> getRolesByUser(Map<String, Object> map);
    
    public List<Role> getSelectRolesByUser(Map<String, Object> map);
    
    public void insertRolePermissionBatch(Map<String, Object> map);
    
    public void deleteRolePermissions(Map<String, Object> map);
    
    public List<Role> getRolesByPosition(Map<String, Object> map);
    
    public List<Role> getUnRolesByPosition(Map<String, Object> map);
}