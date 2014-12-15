/**
 * 
 */
package com.zjhcsoft.icrm.customer.controller;

import java.util.ArrayList;
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
import com.zjhcsoft.icrm.customer.domain.Addr;
import com.zjhcsoft.icrm.customer.vo.AddrComboVo;
import com.zjhcsoft.icrm.customer.vo.AddrTreeVo;
import com.zjhcsoft.icrm.customer.vo.AddrVo;
import com.zjhcsoft.rbac.user.domain.User;
import com.zjhcsoft.rbac.user.domain.UserSession;
import com.zjhcsoft.rpc.RequestDeployRoute;
import com.zjhcsoft.rpc.context.impl.ResponseContext;

/**
 * @Description:  地址信息
 * @Author zhunb 
 * @Date 2014-5-16 下午4:34:31
 *
 */
@Controller
@RequestMapping("/addrController/")
@SessionAttributes(UserSession.USER_SESSION_ID)
public class AddrController extends BaseController{

	@Autowired
	private JsonUtil jsonUtil;
	
	private static String addrService = "addrService";
	
	
	@RequestMapping("test")
	public String test(){
		return "html:/addr/taobaoAddr";
	}
	
	/**
	 * 
	 * @Description: 地址信息主页
	 * @Date 2014-6-12 上午9:38:06
	 * @Author zhunb
	 */
	@RequestMapping("/addressIndex")
	public String addressMag(){
		return "/icrm/addr/addressIndex";
	}
	
	/**
	 * 
	 * @Description: 地址信息对应列表
	 * @Date 2014-6-12 上午9:41:50
	 * @Author zhunb
	 */
	@RequestMapping("addressList")
	public String addressList(AddrVo addrVo,HttpServletRequest request){
		loadAddrPageList(addrVo, request);
		return "/icrm/addr/addressList";
	}
	
	/**
	 * 
	 * @Description: 地址信息弹出框
	 * @Date 2014-6-12 下午2:21:01
	 * @Author zhunb
	 */
	@RequestMapping("showAddrWin")
	public String showTownWin(){
		
		return "/icrm/addr/address.win";
	}
	
	
	/**
	 * 
	 * @Description: 新增/编辑地址信息
	 * @Date 2014-6-12 下午2:23:35
	 * @Author zhunb
	 */
	@RequestMapping("{id}/doEdit")
	public String addrToEdit(@PathVariable String id,HttpServletRequest request,HttpServletResponse response){
		if(StringUtils.isNotEmpty(id)){
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("id", id);
			ResponseContext result = RequestDeployRoute.call(AppConstant.APP1, addrService,
						"getAddrById", paramMap);
				if (result != null) {
					Addr addr = (Addr)result.getResult();
					if(addr==null){
						//要修改的数据不存在
						request.setAttribute("type", "a");
					}else{
						//判断地址使用状态
						request.setAttribute("domain",addr);
						request.setAttribute("type","m");
					}
				}
		}else{
			request.setAttribute("type", "a");
		}
		return "/icrm/addr/addressForm";
	}
	
	/**
	 * 
	 * @Description: 新增
	 * @Date 2014-6-12 下午3:01:21
	 * @Author zhunb
	 */
	@RequestMapping("doAdd")
	public String doAdd(String parentAddrId,HttpServletRequest request,HttpServletResponse response){
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("id", parentAddrId);
		ResponseContext result = RequestDeployRoute.call(AppConstant.APP1, addrService,
				"getAddrById", paramMap);
		if (result != null) {
			Addr addr = (Addr)result.getResult();
			request.setAttribute("parentAddrFull",addr.getAddrFull());
			request.setAttribute("parentAddrId",addr.getRowId());
		}
		return "/icrm/addr/addressForm";
	}
	
	@RequestMapping("tree")
	public void addrTree(AddrTreeVo addrTreeVo,HttpServletRequest request,HttpServletResponse response){
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("addrTreeVo", addrTreeVo);
		
		ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
				addrService, "getAddrTree", paramMap);
		List<AddrTreeVo> treeList = null;
		if (result != null) {
			treeList = (ArrayList<AddrTreeVo>)result.getResult();
		}
		jsonUtil.outJsonArray(treeList, response);
	}
	

	
	
	/**
	 * 
	 * @Description: 提交新增/修改地址信息
	 * @Date 2014-6-12 下午3:17:53
	 * @Author zhunb
	 */
	@RequestMapping("submitEdit")
	public void submitEdit(Addr addr,HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
	    	User user = (User)modelMap.get("USER_SESSION_ID");
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			addr.setCreatePerson(user.getRowId());
			addr.setLastPerson(user.getRowId());
			paramMap.put("domain", addr);
			ResponseContext result;
			ResponseEnvelope<Object> re = null;
			try {
				result = RequestDeployRoute.call(AppConstant.APP1, addrService,
						"insertOrUpdate", paramMap);
				if (result != null) {
					re = (ResponseEnvelope) result.getResult();
				}else{
					re = new ResponseEnvelope("提交失败",false);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			jsonUtil.outJson(re, response);
	}
	
	/**
	 * 
	 * @Description: 地址信息弹出框
	 * @Date 2014-6-12 下午4:08:51
	 * @Author zhunb
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("addressWin")
	public String addressWin(HttpServletRequest request,HttpServletResponse response){
		//获取默认的省、市、区
		List<AddrComboVo> provinceList = null;
		List<AddrComboVo> cityList;
		List<AddrComboVo> areaList;
		List<AddrComboVo> townList;
		Addr addr = new Addr();
		Map<Object,Object> paramMap = new HashMap<Object,Object>();
		paramMap.put("domain", addr);
		ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
				addrService, "getAddrComboList", paramMap);
		if (result != null) {
			provinceList =  (List<AddrComboVo>) result.getResult();
			addr.setParentAddrId(provinceList.get(0).getRowId());
			cityList = (List<AddrComboVo>) RequestDeployRoute.call(AppConstant.APP1,
					addrService, "getAddrComboList", paramMap).getResult();
			
			addr.setParentAddrId(cityList.get(0).getRowId());
			areaList = (List<AddrComboVo>) RequestDeployRoute.call(AppConstant.APP1,
					addrService, "getAddrComboList", paramMap).getResult();
			addr.setParentAddrId(areaList.get(0).getRowId());
			townList = (List<AddrComboVo>) RequestDeployRoute.call(AppConstant.APP1,
					addrService, "getAddrComboList", paramMap).getResult();
			
			request.setAttribute("provinceList", provinceList);
			request.setAttribute("cityList", cityList);
			request.setAttribute("areaList", areaList);
			request.setAttribute("townList", townList);
		}
		return "/icrm/addr/address.win";
	}
	
	@RequestMapping("addressWinList")
	public String addressWinList(AddrVo addrVo,HttpServletRequest request){
		loadAddrPageList(addrVo, request);
		return "/icrm/addr/address.win.list";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("addressWinCombo")
	public void addressWinCombo(String pid,String type,HttpServletRequest request,HttpServletResponse response){
		List<AddrComboVo> cityList;
		List<AddrComboVo> areaList;
		List<AddrComboVo> townList;
		Addr addr = new Addr();
		List list = new ArrayList();
		Map<Object,Object> paramMap = new HashMap<Object,Object>();
		addr.setParentAddrId(pid);
		paramMap.put("domain", addr);
		Map remap = new HashMap();
		if("province".equals(type)){
			cityList = (List<AddrComboVo>) RequestDeployRoute.call(AppConstant.APP1,
					addrService, "getAddrComboList", paramMap).getResult();
			addr.setParentAddrId(cityList.get(0).getRowId());
			areaList = (List<AddrComboVo>) RequestDeployRoute.call(AppConstant.APP1,
					addrService, "getAddrComboList", paramMap).getResult();
			addr.setParentAddrId(areaList.get(0).getRowId());
			townList = (List<AddrComboVo>) RequestDeployRoute.call(AppConstant.APP1,
					addrService, "getAddrComboList", paramMap).getResult();
			remap.put("city", cityList);
			remap.put("area", areaList);
			remap.put("town", townList);
		}else if("city".equals(type)){
			areaList = (List<AddrComboVo>) RequestDeployRoute.call(AppConstant.APP1,
					addrService, "getAddrComboList", paramMap).getResult();
			addr.setParentAddrId(areaList.get(0).getRowId());
			townList = (List<AddrComboVo>) RequestDeployRoute.call(AppConstant.APP1,
					addrService, "getAddrComboList", paramMap).getResult();
			remap.put("area", areaList);
			remap.put("town", townList);
		}else if("area".equals(type)){
			townList = (List<AddrComboVo>) RequestDeployRoute.call(AppConstant.APP1,
					addrService, "getAddrComboList", paramMap).getResult();
			remap.put("town", townList);
		}
		jsonUtil.outJson(remap, response);
	}
	
	
	private void loadAddrPageList(AddrVo addrVo,HttpServletRequest request){
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("domain", addrVo);
		ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
				addrService, "getTotalSize", paramMap);
		Page page = null;
		if (result != null) {
			page = super.executePage(request, (Long) result.getResult());
		}
		paramMap.put("start", (page.getCurrentPage()-1)*page.getEveryPage());
		paramMap.put("limit", page.getEveryPage());
		result = RequestDeployRoute.call(AppConstant.APP1,
				addrService, "getPageList", paramMap);
		if (result != null) {
			page.setDataList((List<AddrVo>) result.getResult());
		}
		request.setAttribute("page", page);
		request.setAttribute("domain", addrVo);
	}
	
}
