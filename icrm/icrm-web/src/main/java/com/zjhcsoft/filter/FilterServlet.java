package com.zjhcsoft.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.zjhcsoft.app.cache.SessionMapThreadLocal;
import com.zjhcsoft.common.util.AppConstant;
import com.zjhcsoft.common.util.JsonUtil;
import com.zjhcsoft.rbac.user.domain.User;
import com.zjhcsoft.rbac.user.domain.UserSession;
import com.zjhcsoft.rpc.RequestDeployRoute;
import com.zjhcsoft.rpc.context.impl.ResponseContext;

public class FilterServlet extends HttpServlet implements Filter {

	private static final long serialVersionUID = 5162189625393315379L;

	private static Logger log = Logger.getLogger(FilterServlet.class);


	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init");
	}

	public void doFilter(ServletRequest sRequest, ServletResponse sResponse,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) sRequest;
		HttpServletResponse response = (HttpServletResponse) sResponse;
		HttpSession session = request.getSession();
		String url = request.getRequestURI();
		System.out.println(url);
		// 不过滤的uri  
        String[] notFilter = new String[] {  };  
        // 请求的uri  
        String uri = request.getRequestURI(); 
        boolean doFilter = true;  
        for (String s : notFilter) {  
            if (uri.indexOf(s) != -1) {  
                doFilter = false;  
                break;  
            }  
        }
        if (doFilter) {
        	//登录校验用户名&密码
        	if(uri.indexOf("login.do")>-1){
        		Map<Object, Object> paramMap = new HashMap<Object, Object>();
        		paramMap.put("userName", request.getParameter("userName"));
        		paramMap.put("userPsw", request.getParameter("userPsw"));

        		ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
        				"userService", "selectByUserNameAndPsw", paramMap);
        		if(result==null){
    				response.sendError(9001, "登录失败，请重新登录!");
        		}else{
        			User user =  (User) result.getResult();
        			if(user==null){
        				response.sendError(9001, "用户名或密码错误!");
        			}else{
        				SessionMapThreadLocal.set(UserSession.USER_SESSION_ID, user);
        				session.setAttribute(UserSession.USER_SESSION_ID, user);
        				filterChain.doFilter(request, response);  
        			}
        		}
        	}else{
        		if(session.getAttribute(UserSession.USER_SESSION_ID)==null){
    				response.sendError(9001, "登录超时，请重新登录!");
        		}else{
        			filterChain.doFilter(request, response);  
        		}
        	}
        }else {  
            filterChain.doFilter(request, response);  
        }
	}

	public void destroy() {
		
	}

	
}
