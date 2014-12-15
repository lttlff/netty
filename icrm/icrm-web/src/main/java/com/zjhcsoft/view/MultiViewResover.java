/**
 * 
 */
package com.zjhcsoft.view;

import java.util.Locale;
import java.util.Map;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

/**
 * @Description: Spring 多视图配置
 * @Author zhunb
 * @Date 2014-5-20 上午9:41:27
 * 
 */
public class MultiViewResover implements ViewResolver {

	private Map<String, ViewResolver> resolvers;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.ViewResolver#resolveViewName(java.lang
	 * .String, java.util.Locale)
	 */
	@Override
	public View resolveViewName(String viewName, Locale locale)
			throws Exception {
		int n = viewName.indexOf(":"); // 获取
		// viewName(modelAndView中的名字)看其有没有下划线
		ViewResolver resolver = null;
		if (n == (-1)){//默认为jsp页面
			resolver = resolvers.get("jsp");
			return resolver.resolveViewName(viewName, locale);
		}
		// 有的话截取下划线后面的字符串 这里一般是jsp,ftl,vm与配置文件中的<entry key="ftl">的key匹配
		String suffix = viewName.substring(0,n);
		// 根据下划线后面的字符串去获取托管的视图解析类对象
		if("redirect".equals(suffix)){
			suffix = "jsp";
		}
		resolver = resolvers.get(suffix);
		// 取下划线前面的部分 那时真正的资源名.比如我们要使用hello.jsp 那viewName就应该是hello_jsp
		viewName = viewName.substring(n+1, viewName.length());
		if (resolver != null){
			return resolver.resolveViewName(viewName, locale);
		}
		return null;
	}

	public Map<String, ViewResolver> getResolvers() {
		return resolvers;
	}

	public void setResolvers(Map<String, ViewResolver> resolvers) {
		this.resolvers = resolvers;
	}

}
