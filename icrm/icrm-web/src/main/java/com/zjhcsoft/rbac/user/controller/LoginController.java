package com.zjhcsoft.rbac.user.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.zjhcsoft.common.util.AppConstant;
import com.zjhcsoft.rbac.menu.domain.MenuTree;
import com.zjhcsoft.rbac.user.domain.User;
import com.zjhcsoft.rbac.user.domain.UserSession;
import com.zjhcsoft.rpc.RequestDeployRoute;
import com.zjhcsoft.rpc.context.impl.ResponseContext;

@Controller
@RequestMapping("/")
@SessionAttributes(UserSession.USER_SESSION_ID)
public class LoginController {

	private static final String menuService = "menuService";
	
	
	@RequestMapping("login")
	public String login(HttpServletRequest request) {
		return "jsp:redirect:home.do";
	}
	
	@RequestMapping("loginPage")
	public String loginPage() {
		return "/login";
	}

//	@RequestMapping("index")
//	public ModelAndView loginSuccess(HttpServletRequest request,ModelMap modelMap) {
//		//获取菜单列表
//		Map<Object, Object> paramMap = new HashMap<Object, Object>();
//		paramMap.put("start", "0");
//		paramMap.put("limit", "10");
//
//		ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
//				menuService, "getMenuTree", paramMap);
//		if (result != null) {
//			List<MenuTree> menuList =  (List<MenuTree>) result.getResult();
//			request.setAttribute("menuList", menuList);
//		}
//		User user = (User) modelMap.get(UserSession.USER_SESSION_ID);
//		request.setAttribute("userName", user.getRealName());
//		return new ModelAndView("/pages/index.jsp");
//	}
	@RequestMapping("home")
	public String loginSuccess(HttpServletRequest request,ModelMap modelMap) {
		//获取菜单列表
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("start", "0");
		paramMap.put("limit", "10");
		
		ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
				menuService, "getMenuTree", paramMap);
		if (result != null) {
			List<MenuTree> menuList =  (List<MenuTree>) result.getResult();
			request.setAttribute("menuList", menuList);
		}
		User user = (User) modelMap.get(UserSession.USER_SESSION_ID);
		request.setAttribute("user", user);
		return "/home";
	}
	
	
	
	
	@RequestMapping("logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute(UserSession.USER_SESSION_ID);
		response.sendRedirect(request.getContextPath()+"/login.jsp");
//		response.sendError(9001, "");
	}

}
