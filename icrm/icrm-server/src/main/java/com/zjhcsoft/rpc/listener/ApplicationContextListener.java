package com.zjhcsoft.rpc.listener;

public interface ApplicationContextListener{

 	public void contextDestroyed(ApplicationContextEvent event);

 	public void contextInitialized(ApplicationContextEvent event);
}
