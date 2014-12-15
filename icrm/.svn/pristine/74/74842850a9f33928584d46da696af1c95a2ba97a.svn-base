package com.zjhcsoft.rbac.role.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.common.mapper.SeqGeneratorMapper;
import com.zjhcsoft.common.util.UUIDGenerator;
import com.zjhcsoft.rbac.role.domain.Role;
import com.zjhcsoft.rbac.role.mapper.RoleMapper;
import com.zjhcsoft.rbac.user.domain.User;
import com.zjhcsoft.rpc.context.impl.Request;

/**
 * @date 2014-6-6 下午02:14:19
 * @Title: RoleService.java
 * @author lff
 */
@Service
public class RoleService {
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private SeqGeneratorMapper seqGeneratorMapper;
	/**
	 * 分页查询
	 * 
	 * @date 2014-6-6 下午02:25:04
	 * @author lff
	 * @return
	 */
	public List<Role> getPageList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", Request.getAttribute("start"));
		map.put("limit", Request.getAttribute("limit"));
		map.put("domain", Request.getAttribute("domain"));
		return roleMapper.getPageList(map);
	}

	/**
	 * 分页得到总条数
	 * 
	 * @date 2014-6-6 下午02:30:53
	 * @author lff
	 * @return
	 */
	public long getTotalCount() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("domain", Request.getAttribute("domain"));
		return roleMapper.getTotalSize(map);
	}

	/**
	 * 删除
	 */
	public void deleteLogic() {
		String rowId = (String) Request.getAttribute("id");
		Role role = new Role();
		role.setRowId(rowId);
		role.setRoleState(1);
		role.setLastDate(new Date());
		roleMapper.updateByPrimaryKeySelective(role);
	}

	public Role getById() {
		String rowId = (String) Request.getAttribute("id");
		return roleMapper.selectByPrimaryKey(rowId);
	}

	/**
	 * 新增
	 * 
	 * @date 2014-6-9 上午09:03:42
	 * @author lff
	 * @throws RuntimeException 异常要抛出来不然无法回滚
	 */
	public void insertRole() throws RuntimeException {
		Role role = (Role) Request.getAttribute("role");
		User userSession = (User) Request.getAttribute("userSession");
		role.setCreateDate(new Date());
		role.setCreatePerson(userSession.getRowId());
		role.setLastDate(new Date());
		role.setLastPerson(userSession.getRowId());
		role.setRoleCode(seqGeneratorMapper.getValueBySeq("ROLE_CODE_SEQ"));
		role.setRowId(UUIDGenerator.getUUID());
		role.setRoleState(0);
		roleMapper.insertSelective(role);
		
		//添加权限
		String peridsStr = (String)Request.getAttribute("perids");
		if(!StringUtils.isEmpty(peridsStr)){
			String[] perids = peridsStr.split(",");
			this.insertRolePermissions(role.getRowId(), perids);
		}
	}
	/**
	 * 修改
	 * @date 2014-6-9 上午09:46:32
	 * @author lff
	 * @throws RuntimeException
	 */
	public void updateRole() throws RuntimeException {
		Role role = (Role) Request.getAttribute("role");
		User userSession = (User) Request.getAttribute("userSession");
		role.setLastDate(new Date());
		role.setLastPerson(userSession.getRowId());
		roleMapper.updateByPrimaryKeySelective(role);
		
		//删除原来权限
		this.deleteRolePermission(role.getRowId());
		//添加权限
		String peridsStr = (String)Request.getAttribute("perids");
		if(!StringUtils.isEmpty(peridsStr)){
			String[] perids = peridsStr.split(",");
			this.insertRolePermissions(role.getRowId(), perids);
		}
	}
	/**
	 * 已拥有角色列表
	 * @date 2014-6-9 下午04:08:51
	 * @author lff
	 * @return
	 */
	public List<Role> getRolesByUser(){
		String user_id = (String)Request.getAttribute("user_id");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		return roleMapper.getRolesByUser(map);
	}
	/**
	 * 未拥有角色列表
	 * @date 2014-6-9 下午04:09:06
	 * @author lff
	 * @return
	 */
	public List<Role> getSelectRolesByUser(){
		String user_id = (String)Request.getAttribute("user_id");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		return roleMapper.getSelectRolesByUser(map);
	}
	/**
	 * 添加角色权限关系
	 * @date 2014-6-12 下午02:43:18
	 * @author lff
	 * @param role_id
	 * @param perids
	 */
	public void insertRolePermissions(String role_id,String[] perids){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("role_id", role_id);
		map.put("perids", perids);
		roleMapper.insertRolePermissionBatch(map);
	}
	/**
	 * 根据角色id删除下挂权限
	 * @date 2014-6-12 下午02:46:40
	 * @author lff
	 * @param role_id
	 */
	public void deleteRolePermission(String role_id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("role_id", role_id);
		roleMapper.deleteRolePermissions(map);
	}
	/**
	 * 已拥有角色列表
	 * @date 2014-6-17 下午04:34:13
	 * @author lff
	 * @return
	 */
	public List<Role> getRolesByPosition(){
		String position_id = (String)Request.getAttribute("position_id");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("position_id", position_id);
		return roleMapper.getRolesByPosition(map);
	}
	/**
	 * 未拥有角色列表
	 * @date 2014-6-9 下午04:09:06
	 * @author lff
	 * @return
	 */
	public List<Role> getUnRolesByPosition(){
		String position_id = (String)Request.getAttribute("position_id");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("position_id", position_id);
		return roleMapper.getUnRolesByPosition(map);
	}
}
