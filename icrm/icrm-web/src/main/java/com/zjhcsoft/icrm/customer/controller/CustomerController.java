package com.zjhcsoft.icrm.customer.controller;

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
import com.zjhcsoft.icrm.contacts.domain.Contacts;
import com.zjhcsoft.icrm.contacts.vo.ContactsVO;
import com.zjhcsoft.icrm.customer.vo.CustContactsRelaVO;
import com.zjhcsoft.icrm.customer.domain.CustSubInfo;
import com.zjhcsoft.icrm.customer.domain.Customer;
import com.zjhcsoft.icrm.customer.vo.CustomerVO;
import com.zjhcsoft.rpc.RequestDeployRoute;
import com.zjhcsoft.rpc.context.impl.ResponseContext;

@Controller
@RequestMapping("/customerController/")
public class CustomerController extends BaseController{

	private static final String customerService = "customerService";
	private static final String custSubInfoService = "custSubInfoService";
	private static final String custContactsRelaService = "custContactsRelaService"; 
	
	@Autowired
	private JsonUtil jsonUtil;
	
	/**
	 * 加载公共数据
	 */
	private void loadCommonData(HttpServletRequest request) {
		String prefix = "crm_customer";
		String[] field_codes = {"cust_type","reg_type","cust_cut","sts","assign_sts"};
		super.addDicList(request,prefix,field_codes);
		prefix="crm_cust_contacts_rela";
		field_codes = new String[]{"contacts_type"};
		super.addDicList(request,prefix,field_codes);
	}
	
	
	@RequestMapping("index")
	public String list(Customer customer,HttpServletRequest request,HttpServletResponse response){
		loadCustomerPageList(customer, request);
		return "/icrm/customer/customer/customer.main";
	}
	
	
	@RequestMapping("doEdit/{id}")
	public String doEdit(@PathVariable String id,HttpServletRequest request,HttpServletResponse response){
		if(StringUtils.isNotEmpty(id)){
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("id", id);
			ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
					customerService, "selectByPrimaryKey", paramMap);
			if (result != null) {
				Customer customer = (Customer)result.getResult();
				request.setAttribute("customer",customer);
				request.setAttribute("type","m");
				
				paramMap.put("custId", customer.getRowId());
				//查询扩展证件信息列表
				result = RequestDeployRoute.call(AppConstant.APP1,
						custSubInfoService, "getCustSubInfoListByCustId", paramMap);
				request.setAttribute("custSubInfoList",(List<CustSubInfo>)result.getResult());
				//查询联系人信息列表
				result = RequestDeployRoute.call(AppConstant.APP1,
						custContactsRelaService, "getContactsRelaByCustId", paramMap);
				request.setAttribute("custContactsList",(List<CustContactsRelaVO>)result.getResult());
			}
		}else{
			request.setAttribute("type", "a");
		}
		loadCommonData(request);
		return "/icrm/customer/customer/customer.form";
	}
	@RequestMapping("delete/{id}")
	public void deleteById(@PathVariable String id,HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object> paramMap = new HashMap<Object,Object>();
		paramMap.put("id", id);
		
		ResponseContext result =RequestDeployRoute.call(AppConstant.APP1, customerService, "deleteByPrimaryKey", paramMap);
		ResponseEnvelope<Object> re = null;
		if (result != null) {
			re = (ResponseEnvelope) result.getResult();
		}
		jsonUtil.outJson(re, response);
	}
	
	
	@RequestMapping("submitEdit")
	public void submitEdit(CustomerVO customer,HttpServletRequest request,HttpServletResponse response){
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("customer", customer);
			
			ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
					customerService, "insertOrUpdate", paramMap);
			
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
	 * @Description: 加载客户页面信息
	 * @Date 2014-5-15 上午9:48:38
	 * @Author zhunb
	 */
	private void loadCustomerPageList(Customer customer,HttpServletRequest request){
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("domain", customer);
		ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
				customerService, "getCustomerTotalSize", paramMap);
		Page page = null;
		if (result != null) {
			page = super.executePage(request, (Long) result.getResult());
		}
		paramMap.put("start", (page.getCurrentPage()-1)*page.getEveryPage());
		paramMap.put("limit", page.getEveryPage());
		result = RequestDeployRoute.call(AppConstant.APP1,
				customerService, "getCustomerPageList", paramMap);
		if (result != null) {
			page.setDataList((List<Customer>) result.getResult());
		}
		request.setAttribute("page", page);
		request.setAttribute("customer", customer);
	}
	
	
	@RequestMapping("winList")
	public String winList(Customer customer,HttpServletRequest request,HttpServletResponse response){
		loadCustomerPageList(customer, request);
		return "/icrm/customer/customer/customer.win";
	}
	
	/**
	 *  客户分配
	 * @return
	 */
	@RequestMapping("allot")
	public String allot(){
		return "/icrm/customer/customer/allot";
	}
	
	
	
	
}
