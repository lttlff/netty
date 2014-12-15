package com.zjhcsoft.rpc.listener;

public interface RequestContextListener   {

 	public void contextDestroyed(RequestContextEvent event);

 	public void contextInitialized(RequestContextEvent event) ;
}
