package com.zjhcsoft.icrm.customer.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjhcsoft.common.util.AppConstant;
import com.zjhcsoft.common.util.JsonUtil;
import com.zjhcsoft.common.util.ResponseEnvelope;
import com.zjhcsoft.rpc.RequestDeployRoute;
import com.zjhcsoft.rpc.context.impl.ResponseContext;

@Controller
@RequestMapping("/custContactsRelaController/")
public class CustContactsRelaController {

	private static final String custContactsRelaService = "custContactsRelaService"; 
	@Autowired
	private JsonUtil jsonUtil;
	
	@RequestMapping("delete/{id}")
	public void deleteById(@PathVariable String id,HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object> paramMap = new HashMap<Object,Object>();
		paramMap.put("id", id);
		
		ResponseContext result =RequestDeployRoute.call(AppConstant.APP1, custContactsRelaService, "deleteByPrimaryKey", paramMap);
		ResponseEnvelope<Object> re = null;
		if (result != null) {
			re = (ResponseEnvelope) result.getResult();
		}
		jsonUtil.outJson(re, response);
	}
	


}
