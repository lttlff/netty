package com.zjhcsoft.icrm.sale.mapper;

import com.zjhcsoft.icrm.sale.domain.SaleArea;

public interface SaleAreaMapper {
    int deleteByPrimaryKey(String rowId);

    int insert(SaleArea record);

    int insertSelective(SaleArea record);

    SaleArea selectByPrimaryKey(String rowId);

    int updateByPrimaryKeySelective(SaleArea record);

    int updateByPrimaryKey(SaleArea record);
}