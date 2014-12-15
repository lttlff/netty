package com.zjhcsoft.rpc.listener;

public interface RequestContextAttributeListener {
	
	public void attributeAdded(RequestContextAttributeEvent event);

	public void attributeRemoved(RequestContextAttributeEvent event) ;

	public void attributeReplaced(RequestContextAttributeEvent event) ;
}
