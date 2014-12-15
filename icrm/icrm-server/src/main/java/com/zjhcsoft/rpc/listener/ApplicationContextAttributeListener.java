package com.zjhcsoft.rpc.listener;

public interface ApplicationContextAttributeListener {
	
	public void attributeAdded(ApplicationContextAttributeEvent event);

	public void attributeRemoved(ApplicationContextAttributeEvent event) ;

	public void attributeReplaced(ApplicationContextAttributeEvent event) ;
}
