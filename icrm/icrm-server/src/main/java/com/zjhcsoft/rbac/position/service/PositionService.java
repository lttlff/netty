package com.zjhcsoft.rbac.position.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.common.util.UUIDGenerator;
import com.zjhcsoft.rbac.position.domain.Position;
import com.zjhcsoft.rbac.position.domain.PositionNode;
import com.zjhcsoft.rbac.position.mapper.PositionMapper;
import com.zjhcsoft.rpc.context.impl.Request;

/** 
 * @date 2014-6-17 上午09:30:48
 * @Title: PositionService.java 
 * @author lff
 */
@Service
public class PositionService {
	@Autowired
	private PositionMapper positionMapper;
	
	public long getSubCountByPar(String parPosId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parPosId", parPosId);
		return positionMapper.getPageCount(map);
	}
	
	public List<PositionNode> getPositionTree() {
		Map<Object, Object> map = Request.getAttributes();

		List<Position> list = positionMapper.getPositionTree(map);
		List<PositionNode> nodeList = new ArrayList<PositionNode>();
		if (list != null && list.size() > 0) {
			for (Position domain : list) {
				PositionNode node = new PositionNode();
				node.setDomain(domain);
				node.setId(domain.getRowId());
				node.setText(domain.getPosName());
				// node.setParent(domain.getParOrgId());
				long count = this.getSubCountByPar(domain.getRowId());
				if (count<1) {
					node.setChildren(false);
					node.setType("file");
				}
				nodeList.add(node);
			}
		}
		return nodeList;
	}
	
	public List<Position> getPageList(){
		Position domain = (Position) Request.getAttribute("domain");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("domain", domain);
		map.put("start", Request.getAttribute("start"));
		map.put("limit", Request.getAttribute("limit"));
		return positionMapper.getPageList(map);
	}
	
	public long getPageCount() {
		Position domain = (Position) Request.getAttribute("domain");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("domain", domain);
		return positionMapper.getPageCount(map);
	}
	
	public Position getById(){
		String rowId = (String)Request.getAttribute("id");
		return  positionMapper.selectByPrimaryKey(rowId);
	}
	/**
	 * 删除
	 * @date 2014-6-17 下午03:27:27
	 * @author lff
	 */
	public void deleteList(){
		String idsStr = (String)Request.getAttribute("ids");
		if(StringUtils.isNotEmpty(idsStr)){
			String[] ids = idsStr.split(",");
			for(String id : ids){
				this.delete(id);
				//TODO 删除子节点
			}
		}
	}
	
	public void delete(String rowId){
		positionMapper.deleteByPrimaryKey(rowId);
	}
	/**
	 * 添加
	 * @date 2014-6-17 下午03:27:15
	 * @author lff
	 */
	public void addPosition(){
		Position position = (Position) Request.getAttribute("position");
		this.insertPosition(position);
		//角色
		String roleidsStr = (String)Request.getAttribute("roleids");
		String[] roleids = roleidsStr.split(",");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleids", roleids);
		map.put("position_id", position.getRowId());
		//先删除
		positionMapper.deletePositionRoles(map);
		//插入
		positionMapper.insertPositionRoles(map);
	}
	
	public void insertPosition(Position position){
		position.setRowId(UUIDGenerator.getUUID());
		position.setCreateDate(new Date());
		position.setCreatePerson("");
		position.setLastDate(new Date());
		position.setLastPerson("");
		positionMapper.insertSelective(position);
		
		//添加角色
	}
	/**
	 * 修改
	 * @date 2014-6-17 下午03:27:21
	 * @author lff
	 */
	public void modifyPosition(){
		Position position = (Position) Request.getAttribute("position");
		this.updatePosition(position);
		//角色
		String roleidsStr = (String)Request.getAttribute("roleids");
		String[] roleids = roleidsStr.split(",");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleids", roleids);
		map.put("position_id", position.getRowId());
		//先删除
		positionMapper.deletePositionRoles(map);
		//插入
		positionMapper.insertPositionRoles(map);
	}
	
	public void updatePosition(Position position){
		position.setLastDate(new Date());
		position.setLastPerson("");
		positionMapper.updateByPrimaryKeySelective(position);
	}
}
