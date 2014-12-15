package com.zjhcsoft.rbac.user.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zjhcsoft.common.BaseController;
import com.zjhcsoft.common.page.Page;
import com.zjhcsoft.common.util.AppConstant;
import com.zjhcsoft.common.util.BeanToMapUtil;
import com.zjhcsoft.common.util.CommonUtil;
import com.zjhcsoft.common.util.JsonUtil;
import com.zjhcsoft.common.util.ResponseEnvelope;
import com.zjhcsoft.icrm.contacts.domain.Contacts;
import com.zjhcsoft.rbac.user.domain.User;
import com.zjhcsoft.rbac.user.domain.UserSession;
import com.zjhcsoft.rpc.RequestDeployRoute;
import com.zjhcsoft.rpc.context.impl.ResponseContext;

@Controller
@RequestMapping("/userController/")
public class UserController extends BaseController{

	private static final String userService = "userService";
	
	private static final String roleService = "roleService";

	@Autowired
	private JsonUtil jsonUtil;

	
	/**
	 * 用户信息列表
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("index")
	public String index(HttpServletRequest request,User user) {
		loadUserPageList(user,request);
		return "/rbac/user/user";
	}
	
	/**
	 * 用户基本信息编辑或新增
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("doEdit/{id}")
	public String doEdit(@PathVariable String id,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
		if(StringUtils.isNotEmpty(id)){
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("id", id);
			ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
					userService, "selectByPrimaryKey", paramMap);
			if (result != null) {
				User user = (User)result.getResult();
				request.setAttribute("user",user);
				request.setAttribute("type","m");
			}
		}else{
			request.setAttribute("type", "a");
		}
		String[] field_codes = new String[]{"user_type"};
		super.addDicList(request, "tuser", field_codes);
		
		//角色
		Map<Object, Object> roleMap = new HashMap<Object, Object>();
		roleMap.put("user_id", id);
		ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
				roleService, "getRolesByUser", roleMap);
		if(result !=null){
			modelMap.addAttribute("haveRoleList", result.getResult());
		}
		//未拥有
		result = RequestDeployRoute.call(AppConstant.APP1,
				roleService, "getSelectRolesByUser", roleMap);
		if(result !=null){
			modelMap.addAttribute("unhaveRoleList", result.getResult());
		}
		return "/rbac/user/user_form";
	}
	
	/**
	 * 通过主键删除
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping("delete/{id}")
	public void deleteById(@PathVariable String id,HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object> paramMap = new HashMap<Object,Object>();
		paramMap.put("id", id);
		
		ResponseContext result =RequestDeployRoute.call(AppConstant.APP1, userService, "deleteLogic", paramMap);
		ResponseEnvelope<Object> re = null;
		if (result != null) {
			re = (ResponseEnvelope) result.getResult();
		}
		jsonUtil.outJson(re, response);
	}

	@RequestMapping("submitEdit")
	public void submitEdit(User user,String[] roleids,HttpServletRequest request,HttpServletResponse response){
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("user", user);
			User userSession = (User)request.getSession().getAttribute(UserSession.USER_SESSION_ID);
			paramMap.put("userSession", userSession);
			if(roleids !=null && roleids.length>0){
				paramMap.put("roleids", StringUtils.join(roleids, ","));
			}
			if(user.getGetMessage()==null){
				user.setGetMessage("0");
			}
			user.setSaleArea("1");
			
			ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
					userService, "insertOrUpdate", paramMap);
			ResponseEnvelope<Object> re = null;
			if (result != null) {
				re = (ResponseEnvelope) result.getResult();
			}else{
				re = new ResponseEnvelope("提交失败",false);
			}
			jsonUtil.outJson(re, response);
	}

	/**
	 * 
	 * @Description: 人员弹出框
	 * @Date 2014-5-15 下午2:29:12
	 * @Author zhunb
	 */
	@RequestMapping("userWin")
	public String userWinList(User user,HttpServletRequest request){
		loadUserPageList(user, request);
		return "/rbac/user/user.win";
	}

	private void loadUserPageList(User user,HttpServletRequest request){
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("domain", user);
		ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
				userService, "getUserTotalCount", paramMap);
		Page page = null;
		if (result != null) {
			page = super.executePage(request, (Long) result.getResult());
		}
		paramMap.put("start", (page.getCurrentPage()-1)*page.getEveryPage());
		paramMap.put("limit", page.getEveryPage());
		result = RequestDeployRoute.call(AppConstant.APP1,
				userService, "getUserPageList", paramMap);
		if (result != null) {
			List<User> list = (List<User>) result.getResult();
			String[] fieldCodes = new String[]{"tuser-user_type"};
			super.setDicTextInList(list, fieldCodes);
			page.setDataList(list);
		}
		request.setAttribute("page", page);
		request.setAttribute("user", user);
	}
}
