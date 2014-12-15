package com.zjhcsoft.icrm.contacts.mapper;

import com.zjhcsoft.icrm.contacts.domain.ContactsPool;

public interface ContactsPoolMapper {
    int deleteByPrimaryKey(String rowId);

    int insert(ContactsPool record);

    int insertSelective(ContactsPool record);

    ContactsPool selectByPrimaryKey(String rowId);

    int updateByPrimaryKeySelective(ContactsPool record);

    int updateByPrimaryKey(ContactsPool record);
    
    int updateByContactsIdSelective(ContactsPool record);
    
    int deleteByContactsId(String contactsId);

}