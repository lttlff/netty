/**
 * 
 */
package com.zjhcsoft.icrm.contacts.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.common.util.ResponseEnvelope;
import com.zjhcsoft.common.util.UUIDGenerator;
import com.zjhcsoft.icrm.contacts.domain.ContactWay;
import com.zjhcsoft.icrm.contacts.mapper.ContactWayMapper;
import com.zjhcsoft.icrm.customer.domain.CustContactsRela;
import com.zjhcsoft.rpc.context.impl.Request;

/**
 * @Description: 联系方式
 * @Author zhunb
 * @Date 2014-5-13 上午11:11:11
 * 
 */
@Service
public class ContactWayService {

	@Autowired
	private ContactWayMapper contactWayMapper;

	public List<ContactWay> getContactWayListByContactsId() {
		String contactsId = (String) Request.getAttribute("contactsId");
		return contactWayMapper.getContactWayListByContactsId(contactsId);
	}

	public ResponseEnvelope deleteByPrimaryKey() {
		String id = (String) Request.getAttribute("id");
		ResponseEnvelope<Object> re = new ResponseEnvelope<Object>();

		try {
			deleteByPrimaryKey(id);
			re.setSuccess(true);
			re.setMessage("删除成功!");
		} catch (Exception e) {
			re.setSuccess(false);
			re.setMessage(re.getMessage());
		}
		return re;
	}

	
	
	public int deleteByContactsId(String contactsId){
		return contactWayMapper.deleteByContactsId(contactsId);
	}
	
	int deleteByPrimaryKey(String rowId) {
		return contactWayMapper.deleteByPrimaryKey(rowId);
	}

	int insert(ContactWay record) {
		return contactWayMapper.insert(record);
	}

	int insertSelective(ContactWay record) {
		return contactWayMapper.insertSelective(record);
	}

	ContactWay selectByPrimaryKey(String rowId) {
		return contactWayMapper.selectByPrimaryKey(rowId);
	}

	int updateByPrimaryKeySelective(ContactWay record) {
		return contactWayMapper.updateByPrimaryKeySelective(record);
	}

	int updateByPrimaryKey(ContactWay record) {
		return contactWayMapper.updateByPrimaryKey(record);
	}

	/**
	 * @Description:
	 * @Date 2014-5-14 上午9:46:30
	 * @Author zhunb
	 */
	public void insertOrUpdate(ContactWay contactWay) {
		if (StringUtils.isEmpty(contactWay.getRowId())) {
			contactWay.setRowId(UUIDGenerator.getUUID());
			insert(contactWay);
		} else {
			updateByPrimaryKeySelective(contactWay);
		}
	}

}
