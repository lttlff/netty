package com.zjhcsoft.icrm.sale.mapper;

import com.zjhcsoft.icrm.sale.domain.SaleMethodPeriod;

public interface SaleMethodPeriodMapper {
    int deleteByPrimaryKey(String rowId);

    int insert(SaleMethodPeriod record);

    int insertSelective(SaleMethodPeriod record);

    SaleMethodPeriod selectByPrimaryKey(String rowId);

    int updateByPrimaryKeySelective(SaleMethodPeriod record);

    int updateByPrimaryKey(SaleMethodPeriod record);
}