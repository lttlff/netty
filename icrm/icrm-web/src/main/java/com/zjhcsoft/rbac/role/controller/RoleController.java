package com.zjhcsoft.rbac.role.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjhcsoft.common.BaseController;
import com.zjhcsoft.common.page.Page;
import com.zjhcsoft.common.util.AppConstant;
import com.zjhcsoft.common.util.CommonUtil;
import com.zjhcsoft.common.util.JsonUtil;
import com.zjhcsoft.common.util.ResponseEnvelope;
import com.zjhcsoft.rbac.permission.domain.Permission;
import com.zjhcsoft.rbac.role.domain.Role;
import com.zjhcsoft.rbac.user.domain.User;
import com.zjhcsoft.rbac.user.domain.UserSession;
import com.zjhcsoft.rpc.RequestDeployRoute;
import com.zjhcsoft.rpc.context.impl.ResponseContext;

/**
 * @date 2014-6-6 下午02:32:32
 * @Title: RoleController.java
 * @author lff
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

	private static final String roleService = "roleService";
	private static final String permissionService = "permissionService";
	@Autowired
	private JsonUtil jsonUtil;

	/**
	 * 首页
	 * 
	 * @date 2014-6-6 下午02:56:11
	 * @author lff
	 * @param modelMap
	 * @param request
	 * @param role
	 * @return
	 */
	@RequestMapping("index")
	public String index(ModelMap modelMap, HttpServletRequest request, Role role) {
		loadUserPageList(modelMap, request, role);
		return "/rbac/role/role";
	}

	public void loadUserPageList(ModelMap modelMap, HttpServletRequest request,
			Role role) {
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		// CommonUtil.filterToSelect(role);
		paramMap.put("domain", role);
		ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
				roleService, "getTotalCount", paramMap);
		Page page = null;
		if (result != null) {
			page = super.executePage(request, (Long) result.getResult());
		}
		paramMap
				.put("start", (page.getCurrentPage() - 1) * page.getEveryPage());
		paramMap.put("limit", page.getEveryPage());
		result = RequestDeployRoute.call(AppConstant.APP1, roleService,
				"getPageList", paramMap);
		if (result.getResult() != null) {
			List<Role> list = (List<Role>) result.getResult();
			if (list != null && list.size() > 0) {
				String[] fieldCodes = new String[] { "trole-role_type" };
				super.setDicTextInList(list, fieldCodes);
			}
			page.setDataList(list);
		}

		modelMap.addAttribute("page", page);
		modelMap.addAttribute("role", role);
	}

	/**
	 * 删除
	 * 
	 * @date 2014-6-6 下午05:03:38
	 * @author lff
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public ResponseEnvelope delete(String id) {
		ResponseEnvelope re = new ResponseEnvelope();
		try {
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("id", id);
			ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
					roleService, "deleteLogic", paramMap);
			System.out.println(result.getCode());
			re.setState(true);
			re.setMessage("删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			re.setState(false);
			re.setMessage("删除失败:" + e.getMessage());
		}
		return re;
	}

	/**
	 * 跳转修改新增页面
	 * 
	 * @date 2014-6-6 下午06:36:52
	 * @author lff
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("doEdit")
	public String doEdit(ModelMap modelMap, String id,
			HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isNotEmpty(id)) {
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("id", id);
			ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
					roleService, "getById", paramMap);
			if (result != null) {
				Role role = (Role) result.getResult();
				request.setAttribute("role", role);
				request.setAttribute("type", "modify");
			}
		} else {
			request.setAttribute("type", "add");
		}

		// 权限列表
		// 已拥有
		Map<Object, Object> per_map = new HashMap<Object, Object>();
		per_map.put("role_id", id);
		List<Permission> permList = (List<Permission>) RequestDeployRoute.call(
				AppConstant.APP1, permissionService, "getPermissionsByRole",
				per_map).get();
		// 未拥有
		List<Permission> unPermList = (List<Permission>) RequestDeployRoute
				.call(AppConstant.APP1, permissionService,
						"getUnPermissionsByRole", per_map).get();
		modelMap.addAttribute("permList", permList);
		modelMap.addAttribute("unPermList", unPermList);
		String[] field_codes = new String[] { "role_type" };
		super.addDicList(request, "trole", field_codes);
		return "/rbac/role/role_form";
	}

	/**
	 * 新增角色
	 * 
	 * @date 2014-6-9 上午08:56:00
	 * @author lff
	 * @param role
	 * @param request
	 * @param response
	 */
	@RequestMapping("/insertRole")
	@ResponseBody
	public ResponseEnvelope insertRole(Role role, String[] perids,
			HttpServletRequest request, HttpServletResponse response) {
		ResponseEnvelope re = new ResponseEnvelope();
		if (role == null) {
			re.setMessage("传入参数不对!");
			re.setState(false);
			return re;
		}
		try {
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("role", role);
			User userSession = (User) request.getSession().getAttribute(
					UserSession.USER_SESSION_ID);
			paramMap.put("userSession", userSession);
			paramMap.put("perids", StringUtils.join(perids, ","));
			ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
					roleService, "insertRole", paramMap);
			re.setMessage("新增成功!");
			re.setState(true);
		} catch (Exception e) {
			e.printStackTrace();
			re.setMessage("新增失败!" + e.getMessage());
			re.setState(false);
		}
		return re;
	}

	/**
	 * 修改角色信息
	 * 
	 * @date 2014-6-9 上午09:49:17
	 * @author lff
	 * @param role
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updateRole")
	@ResponseBody
	public ResponseEnvelope updateRole(Role role, String[] perids,
			HttpServletRequest request, HttpServletResponse response) {
		ResponseEnvelope re = new ResponseEnvelope();
		if (role == null) {
			re.setMessage("传入参数不对!");
			re.setState(false);
			return re;
		}
		try {
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("role", role);
			User userSession = (User) request.getSession().getAttribute(
					UserSession.USER_SESSION_ID);
			paramMap.put("userSession", userSession);
			paramMap.put("perids", StringUtils.join(perids, ","));
			ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
					roleService, "updateRole", paramMap);
			re.setMessage("修改成功!");
			re.setState(true);
		} catch (Exception e) {
			e.printStackTrace();
			re.setMessage("修改失败!" + e.getMessage());
			re.setState(false);
		}
		return re;
	}
}
