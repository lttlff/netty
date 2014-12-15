package com.zjhcsoft.icrm.contacts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjhcsoft.common.BaseController;
import com.zjhcsoft.common.page.Page;
import com.zjhcsoft.common.util.AppConstant;
import com.zjhcsoft.common.util.JsonUtil;
import com.zjhcsoft.common.util.ResponseEnvelope;
import com.zjhcsoft.icrm.contacts.domain.ContactWay;
import com.zjhcsoft.icrm.contacts.domain.Contacts;
import com.zjhcsoft.icrm.contacts.vo.ContactsVO;
import com.zjhcsoft.icrm.customer.domain.Customer;
import com.zjhcsoft.icrm.customer.vo.CustomerVO;
import com.zjhcsoft.rpc.RequestDeployRoute;
import com.zjhcsoft.rpc.context.impl.ResponseContext;

/**
 * 
* @Description: 联系人
* @Author zhunb 
* @Date 2014-5-13 上午9:39:44
*
 */
@Controller
@RequestMapping("/contactsController/")
public class ContactsController extends BaseController{

	private static final String contactsService = "contactsService";//联系人
	private static final String contactWayService = "contactWayService";//联系方式
	@Autowired
	private JsonUtil jsonUtil;
	
	
	@RequestMapping("index")
	public String list(Contacts contacts,HttpServletRequest request,HttpServletResponse response){
		loadContactsPageList(contacts,request);
		return "/icrm/contacts/contacts.main";
	}
	
	/**
	 * 
	 * @Description: 联系人修改
	 * @Date 2014-5-15 上午9:29:44
	 * @Author zhunb
	 */
	@RequestMapping("doEdit/{id}")
	public String doEdit(@PathVariable String id,HttpServletRequest request,HttpServletResponse response){
		if(StringUtils.isNotEmpty(id)){
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("id", id);
			ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
					contactsService, "getContactsVOInfo", paramMap);
			if (result != null) {
				ContactsVO contacts = (ContactsVO)result.getResult();
				request.setAttribute("contacts",contacts);
				request.setAttribute("type","m");
				
			}
		}else{
			request.setAttribute("type", "a");
		}
		loadCommonData(request);
		return "/icrm/contacts/contacts.form";
	}
	
	
	/**
	 * 
	 * @Description: 联系人信息提交
	 * @Date 2014-5-15 上午9:29:32
	 * @Author zhunb
	 */
	@RequestMapping("submitEdit")
	public void submitEdit(ContactsVO contactsVO,HttpServletRequest request,HttpServletResponse response){
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("contactsVO", contactsVO);
			
			ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
					contactsService, "insertOrUpdate", paramMap);
			
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
	 * @Description: 联系人删除
	 * @Date 2014-5-15 上午9:29:19
	 * @Author zhunb
	 */
	@RequestMapping("delete/{id}")
	public void deleteById(@PathVariable String id,HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object> paramMap = new HashMap<Object,Object>();
		paramMap.put("id", id);
		
		ResponseContext result =RequestDeployRoute.call(AppConstant.APP1, contactsService, "deleteByPrimaryKey", paramMap);
		ResponseEnvelope<Object> re = null;
		if (result != null) {
			re = (ResponseEnvelope) result.getResult();
		}
		jsonUtil.outJson(re, response);
	}
	
	/**
	 * 
	 * @Description: 联系人选择弹出框
	 * @Date 2014-5-13 上午9:42:11
	 * @Author zhunb
	 */
	@RequestMapping("listWin")
	public String listWin(Contacts contacts,HttpServletRequest request,HttpServletResponse response){
		loadContactsPageList(contacts,request);
		return "/icrm/contacts/contacts.win";
	}
	
	
	/**
	 * 
	 * @Description: 联系人分配主页
	 * @Date 2014-5-15 上午9:31:04
	 * @Author zhunb
	 */
	@RequestMapping("allotIndex")
	public String allotIndex(){
		return "/icrm/contacts/allot/index";
	}
	
	/**
	 * 
	 * @Description: 联系人--已分配页面
	 * @Date 2014-5-15 上午9:31:49
	 * @Author zhunb
	 */
	@RequestMapping("alloted")
	public String alloted(Contacts contacts,HttpServletRequest request,HttpServletResponse response){
		contacts.setAssignSts(2);
		loadContactsPageList(contacts,request);
		return "/icrm/contacts/allot/alloted";
	}
	
	/**
	 * 
	 * @Description: 联系人分配-未分配
	 * @Date 2014-5-15 上午9:32:29
	 * @Author zhunb
	 */
	@RequestMapping("disAllot")
	public String disAllot(Contacts contacts,HttpServletRequest request,HttpServletResponse response){
		contacts.setAssignSts(1);
		loadContactsPageList(contacts,request);
		return "/icrm/contacts/allot/disAllot";
	}
	
	
	
	
	
	/**
	 * 加载公共数据
	 */
	private void loadCommonData(HttpServletRequest request) {
		String prefix = "crm_contact_way";
		String[] field_codes = {"contact_type"};
		super.addDicList(request,prefix,field_codes);
		prefix = "crm_contacts";
		field_codes = new String[]{"assign_sts","sts"};
		super.addDicList(request,prefix,field_codes);
	}
	
	/**
	 * 
	 * @Description: 加载联系人页面信息
	 * @Date 2014-5-15 上午9:48:38
	 * @Author zhunb
	 */
	private void loadContactsPageList(Contacts contacts,HttpServletRequest request){
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("domain", contacts);
		ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
				contactsService, "getContactsTotalCount", paramMap);
		Page page = null;
		if (result != null) {
			page = super.executePage(request, (Long) result.getResult());
		}
		paramMap.put("start", (page.getCurrentPage()-1)*page.getEveryPage());
		paramMap.put("limit", page.getEveryPage());
		result = RequestDeployRoute.call(AppConstant.APP1,
				contactsService, "getContactsPageList", paramMap);
		if (result != null) {
			page.setDataList((List<ContactsVO>) result.getResult());
		}
		request.setAttribute("page", page);
		request.setAttribute("contacts", contacts);
	}
	
}
