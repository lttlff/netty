package com.zjhcsoft.icrm.customer.mapper;

import java.util.List;

import com.zjhcsoft.icrm.customer.domain.CustContactsRela;
import com.zjhcsoft.icrm.customer.vo.CustContactsRelaVO;

public interface CustContactsRelaMapper {
    int deleteByPrimaryKey(String rowId);

    int insert(CustContactsRela record);

    int insertSelective(CustContactsRela record);

    CustContactsRela selectByPrimaryKey(String rowId);

    int updateByPrimaryKeySelective(CustContactsRela record);

    int updateByPrimaryKey(CustContactsRela record);

	List<CustContactsRelaVO> getContactsRelaByCustId(String custId);
}