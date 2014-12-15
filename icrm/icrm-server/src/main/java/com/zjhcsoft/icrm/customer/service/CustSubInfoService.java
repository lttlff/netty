package com.zjhcsoft.icrm.customer.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.common.util.ResponseEnvelope;
import com.zjhcsoft.common.util.UUIDGenerator;
import com.zjhcsoft.icrm.customer.domain.CustSubInfo;
import com.zjhcsoft.icrm.customer.domain.Customer;
import com.zjhcsoft.icrm.customer.mapper.CustSubInfoMapper;
import com.zjhcsoft.rpc.context.impl.Request;

@Service
public class CustSubInfoService {

	@Autowired
	private CustSubInfoMapper custSubInfoMapper;
	
	/**
	 * 通过客户ID查询关联附属证书信息
	 * @return
	 */
	public List<CustSubInfo> getCustSubInfoListByCustId(){
		String custId = (String) Request.getAttribute("custId");
		return custSubInfoMapper.getCustSubInfoListByCustId(custId);
	}
	
	public ResponseEnvelope deleteByPrimaryKey(){
		String id = (String) Request.getAttribute("id");
		ResponseEnvelope<Object> re = new ResponseEnvelope<Object>();
		try {
			int i = custSubInfoMapper.deleteByPrimaryKey(id);
			re.setSuccess(true);
			re.setMessage("删除成功!");
		} catch (Exception e) {
			re.setSuccess(false);
			re.setMessage(re.getMessage());
		}
		return re;
	}
	
	
	public void insertOrUpdate(CustSubInfo custSubInfo){
		if (StringUtils.isEmpty(custSubInfo.getRowId())) {
			custSubInfo.setRowId(UUIDGenerator.getUUID());
			custSubInfoMapper.insert(custSubInfo);
		} else {
			custSubInfoMapper.updateByPrimaryKeySelective(custSubInfo);
		}
	}
	
	public ResponseEnvelope insertOrUpdate() {
		CustSubInfo custSubInfo = (CustSubInfo) Request.getAttribute("custSubInfo");
		ResponseEnvelope<Object> re = new ResponseEnvelope<Object>();
		try {
			if (StringUtils.isEmpty(custSubInfo.getRowId())) {
				custSubInfo.setRowId(UUIDGenerator.getUUID());
				custSubInfoMapper.insert(custSubInfo);
			} else {
				custSubInfoMapper.updateByPrimaryKeySelective(custSubInfo);
			}
			re.setMessage("保存成功!");
			re.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			re.setMessage(e.getMessage());
			re.setSuccess(false);
		}
		return re;
	}
	
	
	
	
}
