package com.zjhcsoft.icrm.customer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.common.util.CommonUtil;
import com.zjhcsoft.common.util.ResponseEnvelope;
import com.zjhcsoft.common.util.UUIDGenerator;
import com.zjhcsoft.icrm.customer.domain.CustContactsRela;
import com.zjhcsoft.icrm.customer.domain.CustSubInfo;
import com.zjhcsoft.icrm.customer.domain.Customer;
import com.zjhcsoft.icrm.customer.mapper.CustomerMapper;
import com.zjhcsoft.icrm.customer.vo.CustomerVO;
import com.zjhcsoft.rbac.user.domain.User;
import com.zjhcsoft.rpc.context.impl.Request;

@Service
public class CustomerService {

	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private CustSubInfoService custSubInfoService; 
	@Autowired
	private CustContactsRelaService custContactsRelaService;
	
	public ResponseEnvelope deleteByPrimaryKey() {
		String id = (String) Request.getAttribute("id");
		ResponseEnvelope<Object> re = new ResponseEnvelope<Object>();

		try {
			int i = customerMapper.deleteByPrimaryKey(id);
			//TODO 删除客户联系人关系表
			re.setSuccess(true);
			re.setMessage("删除成功!");
		} catch (Exception e) {
			re.setSuccess(false);
			re.setMessage(re.getMessage());
		}
		return re;
	}

	public Customer selectByPrimaryKey() {
		Map<String, Object> map = new HashMap<String, Object>();
		return customerMapper.selectByPrimaryKey((String) Request.getAttribute("id"));
	}
	
	public List<Customer> getCustomerPageList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", Request.getAttribute("start"));
		map.put("limit", Request.getAttribute("limit"));
		Customer customer = (Customer) Request.getAttribute("domain");
		CommonUtil.filterToSelect(customer);
		map.put("domain", customer);
		return customerMapper.getPageList(map);
	}

	public long getCustomerTotalSize() {
		Map<String, Object> map = new HashMap<String, Object>();
		Customer customer = (Customer) Request.getAttribute("domain");
		CommonUtil.filterToSelect(customer);
		map.put("domain", customer);
		return customerMapper.getTotalSize(map);
	}

	public ResponseEnvelope insertOrUpdate() {
		CustomerVO customerVO = (CustomerVO) Request.getAttribute("customer");
		ResponseEnvelope<Object> re = new ResponseEnvelope<Object>();

		try {
//			Customer customer = new Customer();
//			BeanUtils.copyProperties(customer, customerVO);
			if (StringUtils.isEmpty(customerVO.getRowId())) {
				customerVO.setRowId(UUIDGenerator.getUUID());
				customerMapper.insert(customerVO);
			} else {
				customerMapper.updateByPrimaryKeySelective(customerVO);
			}
			
			//客户附属证件信息
			List<CustSubInfo> custSubInfoList = null;
			for(int i=0;custSubInfoList!=null&&i<custSubInfoList.size();i++){
				if(StringUtils.isEmpty(custSubInfoList.get(i).getCustId())){
					custSubInfoList.get(i).setCustId(customerVO.getRowId());
				}
				custSubInfoService.insertOrUpdate(custSubInfoList.get(i));
			}
			//客户联系人关联信息
			List<CustContactsRela> custContactsRelaList = customerVO.getCustContactsRela();
			for(int i=0;custContactsRelaList!=null&&i<custContactsRelaList.size();i++){
				if(StringUtils.isEmpty(custContactsRelaList.get(i).getCustId())){
					custContactsRelaList.get(i).setCustId(customerVO.getRowId());
				}
				custContactsRelaService.insertOrUpdate(custContactsRelaList.get(i));
			}
			re.setMessage("保存成功!");
			re.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			re.setMessage(e.getMessage());
			re.setSuccess(false);
		}
		return re;
	}

}
