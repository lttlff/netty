package com.zjhcsoft.icrm.customer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.common.util.CommonUtil;
import com.zjhcsoft.common.util.ResponseEnvelope;
import com.zjhcsoft.common.util.UUIDGenerator;
import com.zjhcsoft.icrm.customer.domain.Addr;
import com.zjhcsoft.icrm.customer.mapper.AddrMapper;
import com.zjhcsoft.icrm.customer.vo.AddrComboVo;
import com.zjhcsoft.icrm.customer.vo.AddrTreeVo;
import com.zjhcsoft.icrm.customer.vo.AddrVo;
import com.zjhcsoft.rpc.context.impl.Request;

@Service
public class AddrService {
	@Autowired
	private AddrMapper addrMapper;

	
	public List<AddrTreeVo> getAddrTree(){
		AddrTreeVo addrTreeVo = (AddrTreeVo) Request.getAttribute("addrTreeVo");
		CommonUtil.filterToSelect(addrTreeVo);
		Map map = new HashMap();
		map.put("domain", addrTreeVo);
		return addrMapper.getTreeList(map);
	}
	
	public long getTotalSize(){
		Map<String, Object> map = new HashMap<String, Object>();
		AddrVo addrVo = (AddrVo) Request.getAttribute("domain");
		CommonUtil.filterToSelect(addrVo);
		map.put("domain", addrVo);
		return addrMapper.getTotalSize(map);
	}
	
	public List<AddrVo> getPageList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", Request.getAttribute("start"));
		map.put("limit", Request.getAttribute("limit"));
		AddrVo addrVo = (AddrVo) Request.getAttribute("domain");
		CommonUtil.filterToSelect(addrVo);
		map.put("domain", addrVo);
		return addrMapper.getPageList(map);
	}
	
	public Addr getAddrById(){
		String id = (String) Request.getAttribute("id");
		return selectByPrimaryKey(id);
	}
	
	public ResponseEnvelope insertOrUpdate() {
		Addr addr = (Addr) Request.getAttribute("domain");
		ResponseEnvelope<Object> re = new ResponseEnvelope<Object>();
		try {
			Addr parAddr = selectByPrimaryKey(addr.getParentAddrId());
			addr.setAddrFull(parAddr.getAddrFull()+addr.getAddress());
			
			if (StringUtils.isEmpty(addr.getRowId())) {
				addr.setRowId(UUIDGenerator.getUUID());
				insert(addr);
			} else {
				updateByPrimaryKeySelective(addr);
			}
			re.setMessage("保存成功!");
			re.setSuccess(true);
			re.setData(addr.getRowId());
		} catch (Exception e) {
			e.printStackTrace();
			re.setMessage(e.getMessage());
			re.setSuccess(false);
		}
		return re;
	}
	
	
	
	public List<AddrComboVo> getAddrComboList(){
		Addr addr = (Addr) Request.getAttribute("domain");
		Map map = new HashMap();
		map.put("domain", addr);
		return addrMapper.getAddrComboList(map);
	}
	
	
	public List getCityByProv(){
		
		return null;
	}
	
	public List getTownByCity(){
		
		return null;
	}
	
	
	
	
	private int deleteByPrimaryKey(String rowId) {
		return addrMapper.deleteByPrimaryKey(rowId);
	}

	private int insert(Addr record) {
		return addrMapper.insert(record);
	}

	private int insertSelective(Addr record) {
		return addrMapper.insertSelective(record);
	}

	private Addr selectByPrimaryKey(String rowId) {
		return addrMapper.selectByPrimaryKey(rowId);
	}

	private int updateByPrimaryKeySelective(Addr record) {
		return addrMapper.updateByPrimaryKeySelective(record);
	}

	private int updateByPrimaryKey(Addr record) {
		return addrMapper.updateByPrimaryKey(record);
	}
}