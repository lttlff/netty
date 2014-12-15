package com.zjhcsoft.icrm.dynamic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjhcsoft.common.util.JsonUtil;

@Controller
@RequestMapping("/dynamicManagerController")
public class DynamicManagerController {

	@Autowired
	private JsonUtil jsonUtil;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		
//		List list =  DynamicFormCache.getFieldDefList();
//		request.setAttribute("feildList", list);
		
		return "/icrm/dynamic/manager";
	}
	
	
	@RequestMapping("/saveFormDefined")
	public void saveFormDefined(String formStr,HttpServletRequest request,HttpServletResponse response){
		System.out.println(formStr);
		
		
		jsonUtil.outJson("{success:true,message:'success'}", response);
	}
	
}
