package com.zjhcsoft.rbac.user.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.common.icrm.Constant;
import com.zjhcsoft.common.mapper.PageMapper;
import com.zjhcsoft.common.page.Page;
import com.zjhcsoft.common.util.CommonUtil;
import com.zjhcsoft.common.util.JsonUtil;
import com.zjhcsoft.common.util.ResponseEnvelope;
import com.zjhcsoft.common.util.UUIDGenerator;
import com.zjhcsoft.rbac.role.service.RoleService;
import com.zjhcsoft.rbac.user.domain.User;
import com.zjhcsoft.rbac.user.mapper.UserMapper;
import com.zjhcsoft.rpc.context.impl.Request;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PageMapper pageMapper;

	@Autowired
	private JsonUtil jsonUtil;
	@Autowired
	private RoleService roleService;
	/**
	 * 分页查询，获得列表
	 * @date 2014-6-10 上午09:13:40
	 * @author lff
	 * @return
	 */
	public List<User> getUserPageList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", Request.getAttribute("start"));
		map.put("limit", Request.getAttribute("limit"));
		User user = (User) Request.getAttribute("domain");
		CommonUtil.filterToSelect(user);
		map.put("domain", user);
		List<User> list = userMapper.getList(map);
		return list;
	}
	/**
	 * 获得用户总量
	 * @date 2014-6-10 上午09:13:21
	 * @author lff
	 * @return
	 */
	public long getUserTotalCount() {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) Request.getAttribute("domain");
		CommonUtil.filterToSelect(user);
		map.put("domain", user);
		return userMapper.getTotalSize(map);
	}
	
	public ResponseEnvelope<User> getUserListByPage() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", Request.getAttribute("start"));
		map.put("limit", Request.getAttribute("limit"));
		ResponseEnvelope<User> re = new ResponseEnvelope<User>();
		re.setList(userMapper.getList(map));
		re.setTotalSize(userMapper.getTotalSize(map));
		return re;
	}

	/**
	 * 逻辑删除
	 * 异常要抛出来
	 * @date 2014-6-9 下午03:22:15
	 * @author lff 
	 * @return
	 */
	public ResponseEnvelope deleteLogic() {
		String id = (String) Request.getAttribute("id");
		ResponseEnvelope<Object> re = new ResponseEnvelope<Object>();

		try {
			User user = new User();
			user.setRowId(id);
			user.setUserState(1);
			int i = userMapper.updateByPrimaryKeySelective(user);
			re.setSuccess(true);
			re.setMessage("删除成功!");
		} catch (Exception e) {
			re.setSuccess(false);
			re.setMessage(re.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		return re;
	}

	public User selectByUserNameAndPsw() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", (String) Request.getAttribute("userName"));
		map.put("userPsw", (String) Request.getAttribute("userPsw"));
		return userMapper.selectByUserNameAndPsw(map);
	}

	/**
	 * 插入或者删除
	 * 
	 * @date 2014-6-10 上午08:38:56
	 * @author lff
	 * @return
	 */
	public ResponseEnvelope insertOrUpdate() {
		User user = (User) Request.getAttribute("user");
		User userSession = (User) Request.getAttribute("userSession");
		ResponseEnvelope<Object> re = new ResponseEnvelope<Object>();

		try {
			if (StringUtils.isEmpty(user.getRowId())) {
				//账号唯一性判断
				
				user.setRowId(UUIDGenerator.getUUID());
				user.setUserPsw(Constant.DEFAULT_PASSWORD);// 默认密码
				user.setUserState(0);
				user.setCreateDate(new Date());
				user.setCreatePerson(userSession.getRowId());
				user.setLastPerson(userSession.getRowId());
				user.setLastDate(new Date());
				userMapper.insert(user);
			} else {
				user.setLastPerson(userSession.getRowId());
				user.setLastDate(new Date());
				userMapper.updateByPrimaryKeySelective(user);
				
				//删除原有用户角色关联关系
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("user_id", user.getRowId());
				userMapper.deleteUserRoles(map);
			}
			// 添加角色
			String roleids = (String) Request.getAttribute("roleids");
			if (StringUtils.isNotEmpty(roleids)) {
				this.insertUserRoles(user.getRowId(), roleids.split(","));
			}
			re.setMessage("保存成功!");
			re.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			re.setMessage(e.getMessage());
			re.setSuccess(false);
			throw new RuntimeException(e.getMessage());
		}
		return re;
	}

	/**
	 * 根据id获得用户
	 * 
	 * @date 2014-6-10 上午08:39:07
	 * @author lff
	 * @return
	 */
	public User selectByPrimaryKey() {
		Map<String, Object> map = new HashMap<String, Object>();
		return userMapper.selectByPrimaryKey((String) Request
				.getAttribute("id"));
	}

	/**
	 * 批量插入用户角色关联
	 * 
	 * @date 2014-6-10 上午09:12:02
	 * @author lff
	 * @param user_id
	 * @param roleids
	 */
	public void insertUserRoles(String user_id, String[] roleids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("roleids", roleids);
		userMapper.insertUserRoles(map);
	}
}
