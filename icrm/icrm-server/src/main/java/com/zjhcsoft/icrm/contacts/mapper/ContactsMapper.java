package com.zjhcsoft.icrm.contacts.mapper;

import java.util.List;
import java.util.Map;

import com.zjhcsoft.icrm.contacts.domain.Contacts;
import com.zjhcsoft.icrm.contacts.vo.ContactsVO;
import com.zjhcsoft.icrm.customer.domain.Customer;

public interface ContactsMapper {
    int deleteByPrimaryKey(String rowId);

    int insert(Contacts record);

    int insertSelective(Contacts record);

    Contacts selectByPrimaryKey(String rowId);

    int updateByPrimaryKeySelective(Contacts record);

    int updateByPrimaryKey(Contacts record);

	List<ContactsVO> getPageList(Map<String, Object> map);

	long getTotalSize(Map<String, Object> map);
	
	ContactsVO getContactsVOInfo(String rowId);
}