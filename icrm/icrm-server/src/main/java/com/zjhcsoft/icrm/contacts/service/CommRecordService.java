package com.zjhcsoft.icrm.contacts.service;

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
import com.zjhcsoft.icrm.contacts.domain.CommRecord;
import com.zjhcsoft.icrm.contacts.domain.Contacts;
import com.zjhcsoft.icrm.contacts.mapper.CommRecordMapper;
import com.zjhcsoft.icrm.contacts.vo.CommRecordVO;
import com.zjhcsoft.icrm.contacts.vo.ContactsVO;
import com.zjhcsoft.rpc.context.impl.Request;

@Service
public class CommRecordService {
	@Autowired
	private CommRecordMapper commRecordMapper;
	
	public List<CommRecordVO> getPageList(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", Request.getAttribute("start"));
		map.put("limit", Request.getAttribute("limit"));
		CommRecordVO commRecordVO = (CommRecordVO) Request.getAttribute("domain");
		CommonUtil.filterToSelect(commRecordVO);
		map.put("domain", commRecordVO);
		return commRecordMapper.getPageList(map);
	}
    
    public long getTotalSize(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", Request.getAttribute("start"));
		map.put("limit", Request.getAttribute("limit"));
		CommRecordVO commRecordVO = (CommRecordVO) Request.getAttribute("domain");
		CommonUtil.filterToSelect(commRecordVO);
		map.put("domain", commRecordVO);
    	return commRecordMapper.getTotalSize(map);
    }
	
    
    public ResponseEnvelope deleteByPrimaryKey(){
    	String rowId = (String) Request.getAttribute("id");
    	ResponseEnvelope re = new ResponseEnvelope();
    	try {
			commRecordMapper.deleteByPrimaryKey(rowId);
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
    	CommRecordVO record = (CommRecordVO) Request.getAttribute("domain");
    	String operatorId = (String) Request.getAttribute("operatorId");
    	ResponseEnvelope re = new ResponseEnvelope();
    	try {
    		record.setLastDate(new Date());
    		record.setLastPerson(operatorId);
			if(StringUtils.isEmpty(record.getRowId())){
				record.setCreateDate(new Date());
				record.setCreatePerson(operatorId);
				record.setRowId(UUIDGenerator.getUUID());
				record.setUserId(operatorId);
				commRecordMapper.insert(record);
			}else{
				commRecordMapper.updateByPrimaryKeySelective(record);
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

    
    public CommRecordVO getCommRecordVOInfo(){
		String rowId = (String) Request.getAttribute("id");
		try {
			return commRecordMapper.getCommRecordVOInfo(rowId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    

    
    
	
}