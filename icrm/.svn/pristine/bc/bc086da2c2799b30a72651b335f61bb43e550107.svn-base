package com.zjhcsoft.icrm.contacts.mapper;

import java.util.List;

import com.zjhcsoft.icrm.contacts.domain.ContactWay;

public interface ContactWayMapper {
    int deleteByPrimaryKey(String rowId);

    int insert(ContactWay record);

    int insertSelective(ContactWay record);

    ContactWay selectByPrimaryKey(String rowId);

    int updateByPrimaryKeySelective(ContactWay record);

    int updateByPrimaryKey(ContactWay record);
    
    List<ContactWay> getContactWayListByContactsId(String contactsId);
    
    int deleteByContactsId(String contactsId);
    
}