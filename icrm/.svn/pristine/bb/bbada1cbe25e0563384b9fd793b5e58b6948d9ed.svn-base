package com.zjhcsoft.icrm.contacts.mapper;

import java.util.List;
import java.util.Map;

import com.zjhcsoft.icrm.contacts.domain.ContactsGroup;

public interface ContactsGroupMapper {
    int deleteByPrimaryKey(String rowId);

    int insert(ContactsGroup record);

    int insertSelective(ContactsGroup record);

    ContactsGroup selectByPrimaryKey(String rowId);

    int updateByPrimaryKeySelective(ContactsGroup record);

    int updateByPrimaryKey(ContactsGroup record);

	List<ContactsGroup> getPageList(Map<String, Object> map);

	long getTotalSize(Map<String, Object> map);
}