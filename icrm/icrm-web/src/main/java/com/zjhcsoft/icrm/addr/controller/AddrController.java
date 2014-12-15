/**
 * 
 */
package com.zjhcsoft.icrm.addr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjhcsoft.common.util.JsonUtil;

/**
 * @Description:  地址信息
 * @Author zhunb 
 * @Date 2014-5-16 下午4:34:31
 *
 */
//@Controller
@RequestMapping("/addrController/")
public class AddrController {

	@Autowired
	private JsonUtil jsonUtil;
	
	@RequestMapping("test")
	public String test(){
		return "html:/addr/taobaoAddr";
	}
	
	
	@RequestMapping("showAddrWin")
	public String showTownWin(){
		
		
		
		return "/icrm/addr/address.win";
	}
	
}
