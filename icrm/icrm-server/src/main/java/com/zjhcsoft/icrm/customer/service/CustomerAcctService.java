package com.zjhcsoft.icrm.customer.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.common.icrm.Constant;
import com.zjhcsoft.common.util.CommonUtil;
import com.zjhcsoft.common.util.ResponseEnvelope;
import com.zjhcsoft.common.util.UUIDGenerator;
import com.zjhcsoft.icrm.customer.mapper.CustomerAcctMapper;
import com.zjhcsoft.icrm.customer.vo.CustomerAcctVO;
import com.zjhcsoft.rpc.context.impl.Request;

@Service
public class CustomerAcctService {
	@Autowired
	private CustomerAcctMapper customerAcctMapper;
	
	public List<CustomerAcctVO> getPageList(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", Request.getAttribute("start"));
		map.put("limit", Request.getAttribute("limit"));
		CustomerAcctVO record = (CustomerAcctVO) Request.getAttribute("domain");
		CommonUtil.filterToSelect(record);
		map.put("domain", record);
		return customerAcctMapper.getPageList(map);
	}
    
    public long getTotalSize(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", Request.getAttribute("start"));
		map.put("limit", Request.getAttribute("limit"));
		CustomerAcctVO record = (CustomerAcctVO) Request.getAttribute("domain");
		CommonUtil.filterToSelect(record);
		map.put("domain", record);
    	return customerAcctMapper.getTotalSize(map);
    }
	
    
    public ResponseEnvelope deleteByPrimaryKey(){
    	String rowId = (String) Request.getAttribute("id");
    	ResponseEnvelope re = new ResponseEnvelope();
    	try {
			customerAcctMapper.deleteByPrimaryKey(rowId);
			re.setSuccess(true);
			re.setMessage(Constant.DEL_SUCCESS);
		} catch (Exception e) {
			re.setSuccess(false);
			re.setMessage(Constant.DEL_FAILURE);
			re.setData(e.getMessage());
		}
    	return re;
    }
    
    public ResponseEnvelope insertOrUpdate(){
    	CustomerAcctVO record = (CustomerAcctVO) Request.getAttribute("domain");
    	ResponseEnvelope re = new ResponseEnvelope();
    	try {
    		record.setLastDate(new Date());
			if(StringUtils.isEmpty(record.getRowId())){
				record.setCreateDate(new Date());
				record.setRowId(UUIDGenerator.getUUID());
				record.setCreatePerson(record.getLastPerson());
				customerAcctMapper.insert(record);
			}else{
				customerAcctMapper.updateByPrimaryKeySelective(record);
			}
			re.setSuccess(true);
			re.setMessage(Constant.SUCCESS);
			re.setData(record.getRowId());
		} catch (Exception e) {
			e.printStackTrace();
			re.setSuccess(false);
			re.setMessage(Constant.FAILURE);
			re.setData(e.getMessage());
		}
    	return re;
    }

    
    public CustomerAcctVO getCustomerAcctVOInfo(){
		String rowId = (String) Request.getAttribute("id");
		try {
			return customerAcctMapper.getCustomerAcctVOInfo(rowId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    

    
    
	
}