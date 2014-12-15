package com.zjhcsoft.icrm.dynamic.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjhcsoft.common.util.JsonUtil;
import com.zjhcsoft.icrm.dynamic.page.web.FormEngine;
import com.zjhcsoft.icrm.dynamic.page.web.FormValueEngine;
import com.zjhcsoft.icrm.dynamic.service.DynamicFormService;

@Controller
@RequestMapping("/dynamicFormController")
public class DynamicFormController {

	@Autowired
	private DynamicFormService dynamicFormService;
	@Autowired
	private JsonUtil jsonUtil;

	@Autowired
	private FormValueEngine formValueEngine;
	
	@RequestMapping("/{id}/showUser")
	public String showUser(@PathVariable String id, HttpServletRequest request,HttpServletResponse response) {
//		User u = userService.getUserById(id);
//		request.setAttribute("user", u);
		return "showUser";
	}
	
	@RequestMapping("/bootstrap")
	public String testBootstrap(){
		return "/icrm/dynamic/mydemo01";

	}
	
	/**
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * 方法功能说明: FORM 定义
	 * @author	zhunb  2014-3-10
	 */
	@RequestMapping("/showDynamicForm")
	public String showDynamicFormById(String id,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{

//		dynamicFormService.getDynamicFormJsonPageById(id);
		
//		String formInnerHtml = formEngine.getDefineFormHtmlById("2");
		
//		request.setAttribute("formInnerHtml", formInnerHtml);
		request.setAttribute("actionUrl", "dynamicFormController/submitDynamicForm.do");
//		 request.getRequestDispatcher("/showresult.jsp")
//		   .forward(request, response);
		return "/icrm/dynamic/displayForm";
	}
	
	/**
	 * 
	 * @param id	crm_dynamic_form.id
	 * @param request
	 * @param response
	 * @return
	 * 方法功能说明:
	 * @author	zhunb  2014-3-10
	 */
	@RequestMapping("showCRMDynamicForm/{id}")
	public String showCRMDynamicForm(@PathVariable String id,HttpServletRequest request,HttpServletResponse response){
		String formInnerHtml = formValueEngine.get4FormHtmlById(id);
		System.out.println(formInnerHtml);
		request.setAttribute("formInnerHtml", formInnerHtml);
		request.setAttribute("actionUrl", "dynamicFormController/updateDynamicForm/"+id+".do");
		
		
		return "/icrm/dynamic/displayForm";
	}
	
	
	@RequestMapping("/updateDynamicForm/{id}")
	public void updateDynamicForm(@PathVariable String id,String dynamicJson,HttpServletRequest request){
		dynamicFormService.updateDynamicForm(dynamicJson,id);
	}

	@RequestMapping("/submitDynamicForm")
	public void updateOrInsertDynamicForm(String dynamicJson,HttpServletRequest request){
		dynamicFormService.insertDynamicForm(dynamicJson);
		System.out.println(dynamicJson);
	}
	
	
	
	
}
