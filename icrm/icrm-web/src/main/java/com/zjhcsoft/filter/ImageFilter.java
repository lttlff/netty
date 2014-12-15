package com.zjhcsoft.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageFilter implements Filter {
	public void destroy() {

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		String yanzhengm = request.getParameter("validCode");
		String sessionyanz = (String) request.getSession(true).getAttribute(
				"validCode");
		System.out.println("sessionyanz:" + sessionyanz);
		System.out.println("yanzhengm:" + yanzhengm);
		if (yanzhengm.equals(sessionyanz)) {
			arg2.doFilter(request, response);
		} else {
			response.sendRedirect("/login.do");
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}
}
