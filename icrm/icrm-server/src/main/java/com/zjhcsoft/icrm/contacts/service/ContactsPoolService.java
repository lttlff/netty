/**
 * 
 */
package com.zjhcsoft.icrm.contacts.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.common.icrm.Constant;
import com.zjhcsoft.common.util.ResponseEnvelope;
import com.zjhcsoft.common.util.UUIDGenerator;
import com.zjhcsoft.icrm.contacts.domain.ContactsPool;
import com.zjhcsoft.icrm.contacts.mapper.ContactsPoolMapper;
import com.zjhcsoft.rpc.context.impl.Request;

/**
 * @Description: TODO
 * @Author zhunb 
 * @Date 2014-5-15 下午3:02:28
 *
 */
@Service
public class ContactsPoolService {

	@Autowired
	private ContactsPoolMapper contactsPoolMapper;
	@Autowired
	private ContactsService contactsService;
	
	/**
	 * 
	 * @Description: 分配联系人TO销售人员
	 * @Date 2014-5-15 下午5:32:44
	 * @Author zhunb
	 */
	public ResponseEnvelope insertByContactsIds(){
		String userId = (String) Request.getAttribute("userId");
		String[] contactsIdArr = (String[]) Request.getAttribute("contactsIdArr");
		ResponseEnvelope re = new ResponseEnvelope();
		try {
			insertByContactsIds(userId, contactsIdArr);
			contactsService.updateAssignStsByIds(contactsIdArr,2);//更新为“已分配”
			re.setMessage(Constant.SUCCESS);
			re.setSuccess(true);
		} catch (Exception e) {
			re.setSuccess(false);
			re.setMessage(Constant.FAILURE);
			re.setData(e.getMessage());
		}
		return re;
	}
	

	/**
	 * 
	 * @Description: 重新分配联系人对应销售人员
	 * @Date 2014-5-15 下午5:33:08
	 * @Author zhunb
	 */
	public ResponseEnvelope reAllotByContactsIds(){
		String userId = (String) Request.getAttribute("userId");
		String[] contactsIdArr = (String[]) Request.getAttribute("contactsIdArr");
		ResponseEnvelope re = new ResponseEnvelope();
		try {
			updateByContactsIds(userId, contactsIdArr);
			re.setMessage(Constant.SUCCESS);
			re.setSuccess(true);
		} catch (Exception e) {
			re.setSuccess(false);
			re.setMessage(Constant.FAILURE);
			re.setData(e.getMessage());
		}
		return re;
	}
	
	/**
	 * 
	 * @Description: 回收联系人对应销售人员
	 * @Date 2014-5-15 下午5:33:42
	 * @Author zhunb
	 */
	public ResponseEnvelope recoverAllotByContactsIds(){
		String[] contactsIdArr = (String[]) Request.getAttribute("contactsIdArr");
		ResponseEnvelope re = new ResponseEnvelope();
		try {
			deleteByContactsIds( contactsIdArr);
			contactsService.updateAssignStsByIds(contactsIdArr,1);//更新为“未分配”
			re.setMessage(Constant.SUCCESS);
			re.setSuccess(true);
		} catch (Exception e) {
			re.setSuccess(false);
			re.setMessage(Constant.FAILURE);
			re.setData(e.getMessage());
		}
		return re;
	}
	
	
	
	void insertByContactsIds(String userId,String[] contactsIdArr) throws Exception{
		ContactsPool record = new ContactsPool();
		record.setCreateDate(new Date());
		record.setLastDate(new Date());
		record.setUserId(userId);
		ResponseEnvelope re = new ResponseEnvelope();
		try {
			for (int i = 0; i < contactsIdArr.length; i++) {
				record.setContactsId(contactsIdArr[i]);
				record.setRowId(UUIDGenerator.getUUID());
				contactsPoolMapper.insert(record);
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	void updateByContactsIds(String userId,String[] contactsIdArr) throws Exception{
		ContactsPool record = new ContactsPool();
		record.setLastDate(new Date());
		record.setUserId(userId);
		ResponseEnvelope re = new ResponseEnvelope();
		try {
			for (int i = 0; i < contactsIdArr.length; i++) {
				record.setContactsId(contactsIdArr[i]);
				contactsPoolMapper.updateByContactsIdSelective(record);
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	void deleteByContactsIds(String[] contactsIdArr) throws Exception{
		try {
			for (int i = 0; i < contactsIdArr.length; i++) {
				contactsPoolMapper.deleteByContactsId(contactsIdArr[i]);
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
