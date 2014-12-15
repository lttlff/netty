package com.zjhcsoft.icrm.sale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.icrm.sale.domain.BusinessOpp;
import com.zjhcsoft.icrm.sale.mapper.BusinessOppMapper;

/**
 * @Description: 商机管理
 * @Author zhunb 
 * @Date 2014-5-26 下午5:02:31
 *
 */
@Service
public class BusinessOppService {

	@Autowired
	private BusinessOppMapper businessOppMapper;
	
	public void insert(){
		
		
		
	}
	

	
	
	
	
	
	
    private int insert(BusinessOpp record){
    	return businessOppMapper.insert(record);
    }

}
