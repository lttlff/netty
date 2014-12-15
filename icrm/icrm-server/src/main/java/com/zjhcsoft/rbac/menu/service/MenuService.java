package com.zjhcsoft.rbac.menu.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.solr.common.util.Hash;
import org.apache.velocity.runtime.directive.Break;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.rbac.menu.domain.Menu;
import com.zjhcsoft.rbac.menu.domain.MenuTree;
import com.zjhcsoft.rbac.menu.mapper.MenuMapper;
import com.zjhcsoft.rpc.context.impl.Request;

@Service
public class MenuService {

	@Autowired
	private MenuMapper menuMapper;
	
	
	
	public List<MenuTree> getMenuTree() throws IllegalAccessException, InvocationTargetException{
		List<MenuTree> menuTreeList = new ArrayList<MenuTree>();
		
		List<Menu> menus = menuMapper.selectAllMenu();
		MenuTree menuTree;
		for(int i=0;i<menus.size();i++){
			if("0".equals(menus.get(i).getParId())){//最上层菜单
				menuTree = new MenuTree();
				BeanUtils.copyProperties(menuTree, menus.get(i));
				menuTreeList.add(menuTree);
			}
		}
		setSubMenuList(menuTreeList,menus);
		return menuTreeList;
	}

	private void setSubMenuList(List<MenuTree> menuTreeList, List<Menu> menus) throws IllegalAccessException, InvocationTargetException {
		MenuTree menuTree;
		List<MenuTree> subMenuTreeList = null;
		for(int i=0;i<menuTreeList.size();i++){
			subMenuTreeList = new ArrayList<MenuTree>();
			for(int j=0;j<menus.size();j++){
				if(menus.get(j).getParId().equals(menuTreeList.get(i).getId())){//最上层菜单
					menuTree = new MenuTree();
					BeanUtils.copyProperties(menuTree, menus.get(j));
					subMenuTreeList.add(menuTree);
				}
			}
			if(subMenuTreeList!=null){
				menuTreeList.get(i).setSubList(subMenuTreeList);
				setSubMenuList(subMenuTreeList,menus);
			}
		}
	}
	/**
	 * 分页数据
	 * @date 2014-6-13 下午04:53:49
	 * @author lff
	 * @return
	 */
	public List<Menu> getPageList(){
		Menu domain = (Menu)Request.getAttribute("menu");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("domain", domain);
		map.put("start", Request.getAttribute("start"));
		map.put("limit", Request.getAttribute("limit"));
		return menuMapper.getPageList(map);
	}
	/**
	 * 总条数
	 * @date 2014-6-13 下午04:53:38
	 * @author lff
	 * @return
	 */
	public long getPageCount(){
		Menu domain = (Menu)Request.getAttribute("menu");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("domain", domain);
		return menuMapper.getPageCount(map);
	}
}
