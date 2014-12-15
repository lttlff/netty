/**
 * 
 */
package com.zjhcsoft.icrm.contacts.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjhcsoft.common.util.AppConstant;
import com.zjhcsoft.common.util.JsonUtil;
import com.zjhcsoft.common.util.ResponseEnvelope;
import com.zjhcsoft.rpc.RequestDeployRoute;
import com.zjhcsoft.rpc.context.impl.ResponseContext;

/**
 * @Description: 联系人池
 * @Author zhunb 
 * @Date 2014-5-15 下午2:55:53
 *
 */
@Controller
@RequestMapping("/contactsPoolController/")
public class ContactsPoolController {

	private final String contactsPoolService = "contactsPoolService";
	@Autowired
	private JsonUtil jsonUtil;
	
	/**
	 * 
	 * @Description: 根据联系人ids和用户id更新联系人池
	 * @Date 2014-5-15 下午3:03:28
	 * @Author zhunb
	 */
	@RequestMapping("insertByContactsId")
	public void insertByContactsId(HttpServletRequest request,String[] contactsIdArr,String userId,HttpServletResponse response){
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("userId", userId);
		paramMap.put("contactsIdArr", contactsIdArr);
		ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
				contactsPoolService, "insertByContactsIds", paramMap);
		ResponseEnvelope<Object> re = null;
		if (result != null) {
			re = (ResponseEnvelope) result.getResult();
		}else{
			re = new ResponseEnvelope("通信失败",false);
		}
		jsonUtil.outJson(re, response);
	}
	
	/**
	 * 
	 * @Description: 联系人重新分配销售人员
	 * @Date 2014-5-15 下午4:51:06
	 * @Author zhunb
	 */
	@RequestMapping("reAllotByContactsId")
	public void reAllotByContactsId(HttpServletRequest request,String[] contactsIdArr,String userId,HttpServletResponse response){
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("userId", userId);
		paramMap.put("contactsIdArr", contactsIdArr);
		ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
				contactsPoolService, "reAllotByContactsIds", paramMap);
		ResponseEnvelope<Object> re = null;
		if (result != null) {
			re = (ResponseEnvelope) result.getResult();
		}else{
			re = new ResponseEnvelope("通信失败",false);
		}
		jsonUtil.outJson(re, response);
	}
	
	
	@RequestMapping("recoverAllotByContactsIds")
	public void recoverAllotByContactsIds(HttpServletRequest request,String[] contactsIdArr,HttpServletResponse response){
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("contactsIdArr", contactsIdArr);
		ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
				contactsPoolService, "recoverAllotByContactsIds", paramMap);
		ResponseEnvelope<Object> re = null;
		if (result != null) {
			re = (ResponseEnvelope) result.getResult();
		}else{
			re = new ResponseEnvelope("通信失败",false);
		}
		jsonUtil.outJson(re, response);
	}
	
	
	
}
