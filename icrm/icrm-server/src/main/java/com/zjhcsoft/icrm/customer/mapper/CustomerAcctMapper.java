package com.zjhcsoft.icrm.customer.mapper;

import java.util.List;
import java.util.Map;

import com.zjhcsoft.icrm.contacts.vo.CommRecordVO;
import com.zjhcsoft.icrm.customer.domain.CustomerAcct;
import com.zjhcsoft.icrm.customer.vo.CustomerAcctVO;

public interface CustomerAcctMapper {
    int deleteByPrimaryKey(String rowId);

    int insert(CustomerAcct record);

    int insertSelective(CustomerAcct record);

    CustomerAcct selectByPrimaryKey(String rowId);

    int updateByPrimaryKeySelective(CustomerAcct record);

    int updateByPrimaryKey(CustomerAcct record);

	List<CustomerAcctVO> getPageList(Map<String, Object> map);

	long getTotalSize(Map<String, Object> map);

	CustomerAcctVO getCustomerAcctVOInfo(String rowId);

}