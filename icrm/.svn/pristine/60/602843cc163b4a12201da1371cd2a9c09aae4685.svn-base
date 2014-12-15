/**
 * 
 */
package com.zjhcsoft.icrm.contacts.controller;

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
import com.zjhcsoft.rbac.user.domain.User;
import com.zjhcsoft.rbac.user.domain.UserSession;
import com.zjhcsoft.rpc.RequestDeployRoute;
import com.zjhcsoft.rpc.context.impl.ResponseContext;

/**
 * @Description: 沟通记录
 * @Author zhunb 
 * @Date 2014-5-15 下午7:45:30
 *
 */
@Controller
@RequestMapping("/commRecordController/")
@SessionAttributes(UserSession.USER_SESSION_ID)
public class CommRecordController extends BaseController{

	private final String commRecordService = "commRecordService"; 
	
	@Autowired
	private JsonUtil jsonUtil;
	
	
	@RequestMapping("index")
	public String list(CommRecordVO domain,HttpServletRequest request,HttpServletResponse response){

		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("domain", domain);
		ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
				commRecordService, "getTotalSize", paramMap);
		Page page = null;
		if (result != null) {
			page = super.executePage(request, (Long) result.getResult());
		}
		paramMap.put("start", (page.getCurrentPage()-1)*page.getEveryPage());
		paramMap.put("limit", page.getEveryPage());
		result = RequestDeployRoute.call(AppConstant.APP1,
				commRecordService, "getPageList", paramMap);
		if (result != null) {
			List<CommRecordVO> list = (List<CommRecordVO>) result.getResult();
			String[] fieldCodes = {"crm_comm_record-contact_way"};
			super.setDicTextInList(list, fieldCodes);
			page.setDataList(list);
		}
		request.setAttribute("page", page);
		request.setAttribute("domain", domain);
		loadCommonData(request);
		return "/icrm/contacts/commrecord";
	}
	
	@RequestMapping("{id}/doEdit")
	public String doEdit(@PathVariable String id,HttpServletRequest request,HttpServletResponse response){
		if(StringUtils.isNotEmpty(id)&&!"-1".equals(id)){
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("id", id);
			ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
					commRecordService, "getCommRecordVOInfo", paramMap);
			if (result != null) {
				CommRecordVO record = (CommRecordVO)result.getResult();
				request.setAttribute("domain",record);
				request.setAttribute("type","m");
				
			}
		}else{
			request.setAttribute("type", "a");
		}
		loadCommonData(request);
		return "/icrm/contacts/commrecord.form";
	}
	
	@RequestMapping("delete/{id}")
	public void deleteById(@PathVariable String id,HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object> paramMap = new HashMap<Object,Object>();
		paramMap.put("id", id);
		ResponseContext result =RequestDeployRoute.call(AppConstant.APP1, commRecordService, "deleteByPrimaryKey", paramMap);
		ResponseEnvelope<Object> re = null;
		if (result != null) {
			re = (ResponseEnvelope) result.getResult();
		}
		jsonUtil.outJson(re, response);
	}
	
	/**
	 * 
	 * @Description: 沟通记录编辑
	 * @Date 2014-5-16 上午10:07:53
	 * @Author zhunb
	 */
	@RequestMapping("submitEdit")
	public void submitEdit(CommRecordVO commRecordVO,ModelMap modelMap, HttpServletRequest request,HttpServletResponse response){
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
		    User user = (User)modelMap.get("USER_SESSION_ID");
		    paramMap.put("domain", commRecordVO);
		    paramMap.put("operatorId", user.getRowId());
			ResponseContext result = RequestDeployRoute.call(AppConstant.APP1,
					commRecordService, "insertOrUpdate", paramMap);
			
			ResponseEnvelope<Object> re = null;
			if (result != null) {
				re = (ResponseEnvelope) result.getResult();
			}else{
				re = new ResponseEnvelope("提交失败",false);
			}
			jsonUtil.outJson(re, response);
	}
	
	
	private void loadCommonData(HttpServletRequest request) {
		String prefix = "crm_comm_record";
		String[] field_codes = {"contact_way"};
		super.addDicList(request,prefix,field_codes);
	}
	
}
