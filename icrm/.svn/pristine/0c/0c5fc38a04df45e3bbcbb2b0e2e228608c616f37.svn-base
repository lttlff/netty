package com.zjhcsoft.common.tree ;

import java.io.Serializable;

/**
 * tree node 基本信息 针对于树节点的信息
 */
public class CommonTreeObj
{
	protected String	id ;	         // ID(必填)
//	protected String	parent ;	         // 父 ID(必填)
	protected String	text ;	         // 节点显示(必填)
	protected Boolean	children	= true ; // 是否有子节点(必填)
	protected String	level ;	         // 显示树的第几层
	protected String	icon ;	     // 显示图标
	protected String	type="folder" ;	     
	protected State	state ;
	                                     
	protected Long	  key ;	         	 // 正在显示的节点的业务ID(可扩展字段)
	                                     
	class State implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -4381998042584431076L;
		private Boolean opened =false;
		private Boolean selected =false;
		private Boolean disabled =false;
		public Boolean getOpened() {
			return opened;
		}
		public void setOpened(Boolean opened) {
			this.opened = opened;
		}
		public Boolean getSelected() {
			return selected;
		}
		public void setSelected(Boolean selected) {
			this.selected = selected;
		}
		public Boolean getDisabled() {
			return disabled;
		}
		public void setDisabled(Boolean disabled) {
			this.disabled = disabled;
		}
	}

	public String getLevel ( )
	{
		return level ;
	}
	
	public void setLevel ( String level )
	{
		this.level = level ;
	}
	
	public String getId ( )
	{
		return id ;
	}
	
	public void setId ( String id )
	{
		this.id = id ;
	}
	
	public String getText ( )
	{
		return text ;
	}
	
	public void setText ( String text )
	{
		this.text = text ;
	}
	
	public Long getKey ( )
	{
		return key ;
	}
	
	public void setKey ( Long key )
	{
		this.key = key ;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Boolean getChildren() {
		return children;
	}

	public void setChildren(Boolean children) {
		this.children = children;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
