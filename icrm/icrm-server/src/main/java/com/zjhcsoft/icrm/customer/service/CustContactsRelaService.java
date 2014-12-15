package com.zjhcsoft.icrm.customer.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.common.util.ResponseEnvelope;
import com.zjhcsoft.common.util.UUIDGenerator;
import com.zjhcsoft.icrm.customer.domain.CustContactsRela;
import com.zjhcsoft.icrm.customer.mapper.CustContactsRelaMapper;
import com.zjhcsoft.icrm.customer.vo.CustContactsRelaVO;
import com.zjhcsoft.rpc.context.impl.Request;

@Service
public class CustContactsRelaService {

	@Autowired
	private CustContactsRelaMapper custContactsRelaMapper;
	
	/**
	 * 根据客户ID获取关联联系人列表
	 * @return
	 */
	public List<CustContactsRelaVO> getContactsRelaByCustId(){
		String custId = (String) Request.getAttribute("custId");

		return custContactsRelaMapper.getContactsRelaByCustId(custId);
	}

	public void insertOrUpdate(CustContactsRela custContactsRela) {
		if (StringUtils.isEmpty(custContactsRela.getRowId())) {
			custContactsRela.setRowId(UUIDGenerator.getUUID());
			custContactsRelaMapper.insert(custContactsRela);
		} else {
			custContactsRelaMapper.updateByPrimaryKeySelective(custContactsRela);
		}
	}
	
	public ResponseEnvelope deleteByPrimaryKey(){
		String id = (String) Request.getAttribute("id");
		ResponseEnvelope<Object> re = new ResponseEnvelope<Object>();
		try {
			int i = custContactsRelaMapper.deleteByPrimaryKey(id);
			re.setSuccess(true);
			re.setMessage("删除成功!");
		} catch (Exception e) {
			re.setSuccess(false);
			re.setMessage(re.getMessage());
		}
		return re;
	}
	
	
}
