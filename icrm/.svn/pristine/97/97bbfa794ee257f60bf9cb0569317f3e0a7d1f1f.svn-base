package com.zjhcsoft.rbac.position.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjhcsoft.common.BaseController;
import com.zjhcsoft.common.page.Page;
import com.zjhcsoft.common.util.AppConstant;
import com.zjhcsoft.common.util.JsonUtil;
import com.zjhcsoft.common.util.ResponseEnvelope;
import com.zjhcsoft.rbac.position.domain.Position;
import com.zjhcsoft.rbac.position.domain.PositionNode;
import com.zjhcsoft.rbac.role.domain.Role;
import com.zjhcsoft.rpc.RequestDeployRoute;
import com.zjhcsoft.rpc.context.impl.ResponseContext;

/** 
 * @date 2014-6-17 上午08:56:06
 * @Title: PositionController.java 
 * @author lff
 */
@Controller
@RequestMapping("/position")
public class PositionController extends BaseController{
	public String  positionService = "positionService";
	
	public String  roleService = "roleService";
	@Autowired
	JsonUtil jsonUtil;
	
	@RequestMapping("index")
	public String index(){
		return "rbac/position/position";
	}
	/**
	 * 职位树
	 * @date 2014-6-17 上午09:27:56
	 * @author lff
	 * @param modelMap
	 * @param parPosId
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getPositionTree")
	public void getPositionTree(ModelMap modelMap, String parPosId,
			HttpServletRequest request, HttpServletResponse response) {
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("parPosId", parPosId);
		ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
				positionService, "getPositionTree", paramMap);
		if(result!=null ){
			List<PositionNode> nodeList = (List<PositionNode>)result.get();
			jsonUtil.outJsonArray(nodeList, response);
		}
	}
	/**
	 * 主界面
	 * @date 2014-6-17 上午10:15:03
	 * @author lff
	 * @param modelMap
	 * @param position
	 * @param request
	 * @return
	 */
	@RequestMapping("main")
	public String main(ModelMap modelMap, Position position,HttpServletRequest request){
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		// CommonUtil.filterToSelect(role);
		paramMap.put("domain", position);
		ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
				positionService, "getPageCount", paramMap);
		Page page = null;
		if (result != null) {
			page = super.executePage(request, (Long) result.getResult());
		}
		paramMap
				.put("start", (page.getCurrentPage() - 1) * page.getEveryPage());
		paramMap.put("limit", page.getEveryPage());
		result = RequestDeployRoute.call(AppConstant.APP1, positionService,
				"getPageList", paramMap);
		if (result.getResult() != null) {
			List<Position> list = (List<Position>) result.getResult();
			if (list != null && list.size() > 0) {
				String[] fieldCodes = new String[] { "tposition-pos_type" };
				super.setDicTextInList(list, fieldCodes);
			}
			page.setDataList(list);
		}

		modelMap.addAttribute("page", page);
		modelMap.addAttribute("position", position);
		
		return "/rbac/position/position_main";
	}
	/**
	 * 跳转编辑界面
	 * @date 2014-6-17 下午02:57:33
	 * @author lff
	 * @param modelMap
	 * @param id
	 * @param parPosId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/doEdit")
	public String doEdit(ModelMap modelMap, String id,String parPosId,
			HttpServletRequest request, HttpServletResponse response){
		if (StringUtils.isNotEmpty(id)) {
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("id", id);
			ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
					positionService, "getById", paramMap);
			if (result != null) {
				Position position = (Position) result.getResult();
				modelMap.addAttribute("position", position);
				modelMap.addAttribute("type", "modify");
			}
			
			//已拥有角色
			Map<Object, Object> role_map = new HashMap<Object, Object>();
			role_map.put("position_id", id);
			result  = RequestDeployRoute.call(AppConstant.APP1,
					roleService, "getRolesByPosition", role_map);
			List<Role> roleList = (List<Role>)result.get();
			modelMap.addAttribute("roleList", roleList);
		} else {
			Position position = new  Position();
			position.setParPosId(parPosId);
			modelMap.addAttribute("position", position);
			modelMap.addAttribute("type", "add");
		}
		
		//为拥有
		Map<Object, Object> role_map = new HashMap<Object, Object>();
		role_map.put("position_id", id);
		ResponseContext result  = RequestDeployRoute.call(AppConstant.APP1,
				roleService, "getUnRolesByPosition", role_map);
		List<Role> unRoleList = (List<Role>)result.get();
		modelMap.addAttribute("unRoleList", unRoleList);
		//下拉框
		String[] field_codes = new String[] { "pos_type" };
		super.addDicList(request, "tposition", field_codes);
		return "/rbac/position/position_form";
	}
	/**
	 * 添加岗位
	 * @date 2014-6-17 下午02:57:44
	 * @author lff
	 * @param modelMap
	 * @param position
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addPosition")
	public ResponseEnvelope addPosition(ModelMap modelMap, Position position,
			String[] roleids ,HttpServletRequest request, HttpServletResponse response){
		ResponseEnvelope re = new ResponseEnvelope();
		try{
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("position", position);
			paramMap.put("roleids", StringUtils.join(roleids,","));
			ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
					positionService, "addPosition", paramMap);
			re.setMessage("添加成功!");
			re.setState(true);
		}catch(Exception e){
			e.printStackTrace();
			re.setMessage("添加失败!"+e.getMessage());
			re.setState(false);
		}
		return re;
	}
	/**
	 * 修改
	 * @date 2014-6-17 下午03:28:46
	 * @author lff
	 * @param modelMap
	 * @param position
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/modifyPosition")
	public ResponseEnvelope modifyPosition(ModelMap modelMap, Position position,
			String[] roleids ,HttpServletRequest request, HttpServletResponse response){
		ResponseEnvelope re = new ResponseEnvelope();
		try{
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("position", position);
			paramMap.put("roleids", StringUtils.join(roleids,","));
			ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
					positionService, "modifyPosition", paramMap);
			re.setMessage("修改成功!");
			re.setState(true);
		}catch(Exception e){
			e.printStackTrace();
			re.setMessage("修改失败!"+e.getMessage());
			re.setState(false);
		}
		return re;
	}
	/**
	 * 删除岗位
	 * @date 2014-6-17 下午02:57:53
	 * @author lff
	 * @param ids
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteList")
	public ResponseEnvelope deleteList(String[] ids, HttpServletRequest request){
		ResponseEnvelope re = new ResponseEnvelope();
		try{
			Map paramMap = new HashMap();
			paramMap.put("ids", ids);
			RequestDeployRoute.call(AppConstant.APP1,
					positionService, "deleteList", paramMap);
			
			re.setMessage("删除成功!");
			re.setState(true);
		}catch(Exception e){
			e.printStackTrace();
			re.setMessage("删除失败!"+e.getMessage());
			re.setState(false);
		}
		return re;
	}
}
