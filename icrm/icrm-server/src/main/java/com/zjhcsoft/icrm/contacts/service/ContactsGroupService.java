/**
 * 
 */
package com.zjhcsoft.icrm.contacts.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.common.util.CommonUtil;
import com.zjhcsoft.icrm.contacts.domain.Contacts;
import com.zjhcsoft.icrm.contacts.domain.ContactsGroup;
import com.zjhcsoft.icrm.contacts.mapper.ContactsGroupMapper;
import com.zjhcsoft.rpc.context.impl.Request;

/**
* @Description: TODO
* @Author zhunb 
* @Date 2014-5-14 下午3:59:36
*
*/ 
@Service
public class ContactsGroupService {
	@Autowired
	private ContactsGroupMapper contactsGroupMapper;

	public long getTotalCount(){
		Map<String, Object> map = new HashMap<String, Object>();
		ContactsGroup contactsGroup = (ContactsGroup) Request.getAttribute("domain");
		CommonUtil.filterToSelect(contactsGroup);
		map.put("domain", contactsGroup);
		return contactsGroupMapper.getTotalSize(map);
	}
	
	public List<ContactsGroup> getPageList(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", Request.getAttribute("start"));
		map.put("limit", Request.getAttribute("limit"));
		ContactsGroup contactsGroup = (ContactsGroup) Request.getAttribute("domain");
		CommonUtil.filterToSelect(contactsGroup);
		map.put("domain", contactsGroup);
		return contactsGroupMapper.getPageList(map);
	}
	
}

