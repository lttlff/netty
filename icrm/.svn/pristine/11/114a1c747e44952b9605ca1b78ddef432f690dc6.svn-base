/**
 * 
 */
package com.zjhcsoft.icrm.contacts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjhcsoft.common.BaseController;
import com.zjhcsoft.common.page.Page;
import com.zjhcsoft.common.util.AppConstant;
import com.zjhcsoft.common.util.JsonUtil;
import com.zjhcsoft.icrm.contacts.domain.ContactsGroup;
import com.zjhcsoft.icrm.customer.domain.Customer;
import com.zjhcsoft.rpc.RequestDeployRoute;
import com.zjhcsoft.rpc.context.impl.ResponseContext;

/**
 * @Description: TODO
 * @Author zhunb 
 * @Date 2014-5-14 下午3:50:30
 *
 */
@Controller
@RequestMapping("/contactsGroupController/")
public class ContactsGroupController extends BaseController{


	@Autowired
	private JsonUtil jsonUtil;
	
	private static final String contactsGroupService = "contactsGroupService";
	
	/**
	 * 
	 * @Description: 联系人分组弹出框
	 * @Date 2014-5-14 下午3:52:04
	 * @Author zhunb
	 */
	@RequestMapping("listWin")
	public String listWin(ContactsGroup contactsGroup,HttpServletRequest request,HttpServletResponse response){
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("domain", contactsGroup);
		ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
				contactsGroupService, "getTotalCount", paramMap);
		Page page = null;
		if (result != null) {
			page = super.executePage(request, (Long) result.getResult());
		}
		paramMap.put("start", (page.getCurrentPage()-1)*page.getEveryPage());
		paramMap.put("limit", page.getEveryPage());
		result = RequestDeployRoute.call(AppConstant.APP1,
				contactsGroupService, "getPageList", paramMap);
		if (result != null) {
			page.setDataList((List<ContactsGroup>) result.getResult());
		}
		request.setAttribute("page", page);
		request.setAttribute("customer", contactsGroup);
		return "/icrm/contacts/contactsGroup.win";
	}
	
	
	
	
}
