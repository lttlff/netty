/**
 * 
 */
package com.zjhcsoft.icrm.contacts.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.common.util.UUIDGenerator;
import com.zjhcsoft.icrm.contacts.domain.ContactsGroup;
import com.zjhcsoft.icrm.contacts.domain.ContactsGroupRela;
import com.zjhcsoft.icrm.contacts.mapper.ContactsGroupRelaMapper;

/**
 * @Description: 联系人和组关联表
 * @Author zhunb 
 * @Date 2014-5-14 下午8:01:27
 *
 */
@Service
public class ContactsGroupRelaService {

	@Autowired
	private ContactsGroupRelaMapper contactsGroupRelaMapper;
	
	/**
	 * 
	 * @param contactsGroup 
	 * @Description: 根据联系人id更新
	 * @Date 2014-5-14 下午8:03:09
	 * @Author zhunb
	 */
	public void updateByContactsId(String contactsId, ContactsGroup contactsGroup){
		//1.删除旧关系
		//2.新增关系
		contactsGroupRelaMapper.deleteByContactsId(contactsId);
		ContactsGroupRela record = new ContactsGroupRela();
		record.setContactsId(contactsId);
		record.setCreateDate(new Date());
		record.setLastDate(new Date());
		record.setGroupId(contactsGroup.getRowId());
		record.setRowId(UUIDGenerator.getUUID());
		contactsGroupRelaMapper.insert(record);
	}
	
	
	
}
