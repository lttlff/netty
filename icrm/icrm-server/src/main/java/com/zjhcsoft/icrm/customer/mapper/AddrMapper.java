package com.zjhcsoft.icrm.customer.mapper;

import java.util.List;
import java.util.Map;

import com.zjhcsoft.icrm.customer.domain.Addr;
import com.zjhcsoft.icrm.customer.vo.AddrComboVo;
import com.zjhcsoft.icrm.customer.vo.AddrTreeVo;
import com.zjhcsoft.icrm.customer.vo.AddrVo;

public interface AddrMapper {
    int deleteByPrimaryKey(String rowId);

    int insert(Addr record);

    int insertSelective(Addr record);

    Addr selectByPrimaryKey(String rowId);

    int updateByPrimaryKeySelective(Addr record);

    int updateByPrimaryKey(Addr record);
    
    List<AddrTreeVo> getTreeList(Map map);

	/**
	 * @Description: TODO
	 * @Date 2014-6-12 上午9:49:25
	 * @Author zhunb 
	 */
	long getTotalSize(Map<String, Object> map);

	/**
	 * @Description: TODO
	 * @Date 2014-6-12 上午9:50:25
	 * @Author zhunb 
	 */
	List<AddrVo> getPageList(Map<String, Object> map);
	
	
	List<AddrComboVo> getAddrComboList(Map map);
	
    
}