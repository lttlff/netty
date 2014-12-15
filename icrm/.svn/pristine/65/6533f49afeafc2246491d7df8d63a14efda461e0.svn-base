package com.zjhcsoft.rbac.menu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjhcsoft.common.BaseController;
import com.zjhcsoft.common.page.Page;
import com.zjhcsoft.common.util.AppConstant;
import com.zjhcsoft.rbac.menu.domain.Menu;
import com.zjhcsoft.rbac.permission.domain.Permission;
import com.zjhcsoft.rpc.RequestDeployRoute;
import com.zjhcsoft.rpc.context.impl.ResponseContext;

/** 
 * @date 2014-6-13 下午04:29:32
 * @Title: MenuController.java 
 * @author lff
 */
@RequestMapping("/menu")
@Controller
public class MenuController extends BaseController{
	private String menuService = "menuService";
	
	@RequestMapping("/goMenuSelectWin")
	public String goMenuSelectWin(ModelMap modelMap, Menu menu,
			HttpServletRequest request, HttpServletResponse response){
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		menu.setLeafFlg(0);
		paramMap.put("menu", menu);
		ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
				menuService, "getPageCount", paramMap);
		if (result != null) {
			long totalCount = (Long) result.get();
			Page page = super.executePage(request, totalCount);
			paramMap.put("start", (page.getCurrentPage() - 1)
					* page.getEveryPage());
			paramMap.put("limit", page.getEveryPage());
			result = RequestDeployRoute.call(AppConstant.APP1,
					menuService, "getPageList", paramMap);
			List<Permission> list = (List<Permission>) result.get();
			String[] field_codes = new String[] { "tmenu-menu_type" };
			super.setDicTextInList(list, field_codes);
			page.setDataList(list);
			modelMap.addAttribute("page", page);
			modelMap.addAttribute("menu", menu);
		}
		return "/rbac/menu/menu_select_win";
	}
}
