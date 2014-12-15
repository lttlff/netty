package com.zjhcsoft.icrm.contacts.mapper;

import java.util.List;
import java.util.Map;

import com.zjhcsoft.icrm.contacts.domain.CommRecord;
import com.zjhcsoft.icrm.contacts.vo.CommRecordVO;
import com.zjhcsoft.icrm.contacts.vo.ContactsVO;

public interface CommRecordMapper {
    int deleteByPrimaryKey(String rowId);

    int insert(CommRecord record);

    int insertSelective(CommRecord record);

    CommRecord selectByPrimaryKey(String rowId);

    int updateByPrimaryKeySelective(CommRecord record);

    int updateByPrimaryKey(CommRecord record);
    
    List<CommRecordVO> getPageList(Map map);
    
    long getTotalSize(Map map);

    CommRecordVO getCommRecordVOInfo(String rowId);
}