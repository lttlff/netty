package com.zjhcsoft.rbac.permission.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.common.mapper.SeqGeneratorMapper;
import com.zjhcsoft.common.util.UUIDGenerator;
import com.zjhcsoft.rbac.permission.domain.Permission;
import com.zjhcsoft.rbac.permission.mapper.PermissionMapper;
import com.zjhcsoft.rpc.context.impl.Request;

/** 
 * @date 2014-6-12 上午09:33:10
 * @Title: PermissionService.java 
 * @author lff
 */
@Service
public class PermissionService {
	@Autowired
	private PermissionMapper permissionMapper;
	
	@Autowired
	private SeqGeneratorMapper seqGeneratorMapper;
	/**
	 * 分页
	 * @date 2014-6-12 下午04:25:35
	 * @author lff
	 * @return
	 */
	public List<Permission> getPageList(){
		Map<String, Object> map = new HashMap<String, Object>();
		Permission permission =  (Permission)Request.getAttribute("permission");
		map.put("domain", permission);
		map.put("start", Request.getAttribute("start"));
		map.put("limit", Request.getAttribute("limit"));
		return permissionMapper.getPageList(map);
	}
	/***
	 * 总数量
	 * @date 2014-6-12 下午04:25:42
	 * @author lff
	 */
	public long getPageCount(){
		Map<String, Object> map = new HashMap<String, Object>();
		Permission permission =  (Permission)Request.getAttribute("permission");
		map.put("domain", permission);
		return permissionMapper.getPageCount(map);
	}
	/**
	 * 逻辑删除
	 * @date 2014-6-13 上午09:53:49
	 * @author lff
	 */
	public void deleteLogicList(){
		String idsStr = (String)Request.getAttribute("ids");
		if(StringUtils.isNotEmpty(idsStr)){
			String[] ids = idsStr.split(",");
			for(String rowId : ids){
				this.deleteLogic(rowId);
				//练级删除关联关系
				
			}
		}
	}
	/**
	 * 逻辑删除
	 * @date 2014-6-13 上午10:12:59
	 * @author lff
	 * @param rowId
	 */
	public void deleteLogic(String rowId){
		Permission per = new Permission();
		per.setRowId(rowId);
		per.setLastDate(new Date());
		per.setLastPerson("");
		per.setPerSts(1);
		permissionMapper.updateByPrimaryKeySelective(per);
	}
	
	public Permission getById(){
		String rowId = (String)Request.getAttribute("id");
		return permissionMapper.selectByPrimaryKey(rowId);
	}
	/**
	 * 新增
	 * @date 2014-6-13 上午10:24:37
	 * @author lff
	 */
	public void insertPermission(){
			Permission per = (Permission)Request.getAttribute("permission");
			per.setCreateDate(new Date());
			per.setCreatePerson("");
			per.setLastDate(new Date());
			per.setLastPerson("");
			per.setPerSts(0);
			per.setRowId(UUIDGenerator.getUUID());
			per.setPerCode(seqGeneratorMapper.getValueBySeq("PERMISSION_CODE_SEQ"));
			permissionMapper.insertSelective(per);
	}
	/**
	 * 修改
	 * @date 2014-6-13 上午10:24:43
	 * @author lff
	 */
	public void updatePermission(){
		Permission per = (Permission)Request.getAttribute("permission");
		per.setLastDate(new Date());
		per.setLastPerson("");
		permissionMapper.updateByPrimaryKeySelective(per);
	}
	/**
	 * 获得角色下的权限列表
	 * @date 2014-6-12 上午11:07:08
	 * @author lff
	 * @return
	 */
	public List<Permission> getPermissionsByRole(){
		Map<String, Object> map = new HashMap<String, Object>();
		String role_id = (String)Request.getAttribute("role_id");
		map.put("role_id", role_id);
		return permissionMapper.getPermissionsByRole(map);
	}
	/**
	 * 获得用户未添加的权限列表
	 * @date 2014-6-12 上午11:09:21
	 * @author lff
	 * @return
	 */
	public List<Permission> getUnPermissionsByRole(){
		Map<String, Object> map = new HashMap<String, Object>();
		String role_id = (String)Request.getAttribute("role_id");
		map.put("role_id", role_id);
		return permissionMapper.getUnPermissionsByRole(map);
	}
	
	
}
