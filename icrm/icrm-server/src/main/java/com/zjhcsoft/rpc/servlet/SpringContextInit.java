package com.zjhcsoft.rpc.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringContextInit implements ServletContextListener {

	private static WebApplicationContext springContext;

	public SpringContextInit() {
		super();
	}

	public void contextInitialized(ServletContextEvent event) {
		springContext = WebApplicationContextUtils
				.getWebApplicationContext(event.getServletContext());
		String[] names  = springContext.getBeanDefinitionNames();
		for(String name: names){
			System.out.println(BeanFactoryUtils.isFactoryDereference(name));
			System.out.println(name);
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
	}

	public static ApplicationContext getApplicationContext() {
		return springContext;
	}

}