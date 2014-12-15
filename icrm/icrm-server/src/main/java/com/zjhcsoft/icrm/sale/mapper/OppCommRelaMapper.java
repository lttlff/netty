package com.zjhcsoft.icrm.sale.mapper;

import com.zjhcsoft.icrm.sale.domain.OppCommRela;

public interface OppCommRelaMapper {
    int deleteByPrimaryKey(String rowId);

    int insert(OppCommRela record);

    int insertSelective(OppCommRela record);

    OppCommRela selectByPrimaryKey(String rowId);

    int updateByPrimaryKeySelective(OppCommRela record);

    int updateByPrimaryKey(OppCommRela record);
}