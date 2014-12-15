package com.zjhcsoft.icrm.customer.mapper;

import java.util.List;
import java.util.Map;

import com.zjhcsoft.icrm.customer.domain.Customer;

public interface CustomerMapper {
    int deleteByPrimaryKey(String rowId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(String rowId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
    
    List<Customer> getPageList(Map map);
    long getTotalSize(Map map);
    
}