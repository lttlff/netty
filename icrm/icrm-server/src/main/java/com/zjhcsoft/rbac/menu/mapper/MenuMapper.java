package com.zjhcsoft.rbac.menu.mapper;

import java.util.List;
import java.util.Map;

import com.zjhcsoft.rbac.menu.domain.Menu;

public interface MenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    
    List<Menu> selectAllMenu();
    
    public List<Menu> getPageList(Map<String, Object> map);
    
    public long getPageCount(Map<String, Object> map);
}