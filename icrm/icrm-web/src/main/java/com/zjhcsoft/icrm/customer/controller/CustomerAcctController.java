/**
 * 
 */
package com.zjhcsoft.icrm.customer.controller;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.zjhcsoft.common.BaseController;
import com.zjhcsoft.common.page.Page;
import com.zjhcsoft.common.util.AppConstant;
import com.zjhcsoft.common.util.JsonUtil;
import com.zjhcsoft.common.util.ResponseEnvelope;
import com.zjhcsoft.icrm.contacts.vo.CommRecordVO;
import com.zjhcsoft.icrm.customer.vo.CustomerAcctVO;
import com.zjhcsoft.rbac.user.domain.User;
import com.zjhcsoft.rbac.user.domain.UserSession;
import com.zjhcsoft.rpc.RequestDeployRoute;
import com.zjhcsoft.rpc.context.impl.ResponseContext;

/**
 * @Description: 账户信息
 * @Author zhunb 
 * @Date 2014-5-16 下午9:59:14
 *
 */
@Controller
@RequestMapping("/customerAcctController/")
@SessionAttributes(UserSession.USER_SESSION_ID)
public class CustomerAcctController extends BaseController{

	private static final String CUSTOMERACCTSERVICE = "customerAcctService";
	@Autowired
	private JsonUtil jsonUtil;
	
	
	@RequestMapping("index")
	public String list(CustomerAcctVO domain,HttpServletRequest request,HttpServletResponse response){

		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("domain", domain);
		ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
				CUSTOMERACCTSERVICE, "getTotalSize", paramMap);
		Page page = null;
		if (result != null) {
			page = super.executePage(request, (Long) result.getResult());
		}
		paramMap.put("start", (page.getCurrentPage()-1)*page.getEveryPage());
		paramMap.put("limit", page.getEveryPage());
		result = RequestDeployRoute.call(AppConstant.APP1,
				CUSTOMERACCTSERVICE, "getPageList", paramMap);
		if (result != null) {
			List<CustomerAcctVO> list = (List<CustomerAcctVO>) result.getResult();
			String[] fieldCodes = {"crm_comm_record-contact_way"};
			super.setDicTextInList(list, fieldCodes);
			page.setDataList(list);
		}
		request.setAttribute("page", page);
		request.setAttribute("domain", domain);
		loadCommonData(request);
		return "/icrm/customer/acct/customerAcct";
	}
	
	@RequestMapping("{id}/doEdit")
	public String doEdit(@PathVariable String id,HttpServletRequest request,HttpServletResponse response){
		if(StringUtils.isNotEmpty(id)&&!"-1".equals(id)){
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("id", id);
			ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
					CUSTOMERACCTSERVICE, "getCustomerAcctVOInfo", paramMap);
			if (result != null) {
				CustomerAcctVO record = (CustomerAcctVO)result.getResult();
				request.setAttribute("domain",record);
				request.setAttribute("type","m");
				
			}
		}else{
			request.setAttribute("type", "a");
		}
		loadCommonData(request);
		return "/icrm/customer/acct/customerAcct.form";
	}
	
	@RequestMapping("delete/{id}")
	public void deleteById(@PathVariable String id,HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object> paramMap = new HashMap<Object,Object>();
		paramMap.put("id", id);
		ResponseContext result =RequestDeployRoute.call(AppConstant.APP1, CUSTOMERACCTSERVICE, "deleteByPrimaryKey", paramMap);
		ResponseEnvelope<Object> re = null;
		if (result != null) {
			re = (ResponseEnvelope) result.getResult();
		}
		jsonUtil.outJson(re, response);
	}
	
	@RequestMapping("submitEdit")
	public void submitEdit(CommRecordVO commRecordVO,ModelMap modelMap, HttpServletRequest request,HttpServletResponse response){
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
		    User user = (User)modelMap.get("USER_SESSION_ID");
		    commRecordVO.setLastPerson(user.getRowId());
		    paramMap.put("domain", commRecordVO);
			ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
					CUSTOMERACCTSERVICE, "insertOrUpdate", paramMap);
			ResponseEnvelope<Object> re = null;
			if (result != null) {
				re = (ResponseEnvelope) result.getResult();
			}else{
				re = new ResponseEnvelope("提交失败",false);
			}
			jsonUtil.outJson(re, response);
	}
	
	
	private void loadCommonData(HttpServletRequest request) {
		String prefix = "crm_customer_acct";
		String[] field_codes = {"sts"};
		super.addDicList(request,prefix,field_codes);
	}
}
