package com.zjhcsoft.rbac.menu.domain;

import java.util.List;

public class MenuTree extends Menu{

	private static final long serialVersionUID = -4079063968809849443L;

	private List<MenuTree> subList;

	private String icon;
	private boolean leaf;
	public List<MenuTree> getSubList() {
		return subList;
	}
	public void setSubList(List<MenuTree> subList) {
		this.subList = subList;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public void addSubMenu(MenuTree menuTree){
		this.subList.add(menuTree);
	}
	
}
