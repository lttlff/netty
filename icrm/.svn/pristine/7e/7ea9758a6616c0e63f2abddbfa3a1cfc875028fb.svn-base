package com.zjhcsoft.icrm.customer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjhcsoft.common.BaseController;
import com.zjhcsoft.common.page.Page;
import com.zjhcsoft.common.util.AppConstant;
import com.zjhcsoft.icrm.customer.domain.Customer;
import com.zjhcsoft.icrm.customer.vo.CustomerVO;
import com.zjhcsoft.rpc.RequestDeployRoute;
import com.zjhcsoft.rpc.context.impl.ResponseContext;

/**
 * 
* @Description: 客户分配
* @Author zhunb 
* @Date 2014-5-9 上午9:52:26
*
 */
@Controller
@RequestMapping("/customerAllotController/")
public class CustomerAllotController extends BaseController{

	private static final String customerService = "customerService";
	
	private static String[] fieldCodes = {"crm_customer-cust_type","crm_customer-reg_type",
		"crm_customer-cust_cat","crm_customer-sts","crm_customer-assign_sts","crm_customer-cust_flag"};
	
	/**
	 * 
	 * @Description: 客户分配主页
	 * @Date 2014-5-9 上午9:56:02
	 * @Author zhunb 
	 */
	@RequestMapping("index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		
		return "/icrm/customer/customer/allot";
	}
	
	
	/**
	 * 
	 * @Description: 已分配
	 * @Date 2014-5-9 上午10:36:37
	 * @Author zhunb 
	 */
	@RequestMapping("alloted")
	public String alloted(HttpServletRequest request,HttpServletResponse response){
		
		return "/icrm/customer/customer/alloted";
	}
	
	/**
	 * 
	 * @Description: 未分配
	 * @Date 2014-5-9 上午10:36:27
	 * @Author zhunb 
	 */
	@RequestMapping("allot_dis")
	public String allotDis(Customer customer,HttpServletRequest request,HttpServletResponse response){

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
			List<Customer> list = (List<Customer>) result.getResult();
			super.setDicTextInList(list, fieldCodes);
			page.setDataList(list);
		}
		request.setAttribute("page", page);
		request.setAttribute("customer", customer);
		
		return "/icrm/customer/customer/allot_dis";
	}
	
	
	
	
}
