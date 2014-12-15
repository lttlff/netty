package com.zjhcsoft.icrm.contacts.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjhcsoft.common.util.CommonUtil;
import com.zjhcsoft.common.util.ResponseEnvelope;
import com.zjhcsoft.common.util.UUIDGenerator;
import com.zjhcsoft.icrm.contacts.domain.ContactWay;
import com.zjhcsoft.icrm.contacts.domain.Contacts;
import com.zjhcsoft.icrm.contacts.mapper.ContactsMapper;
import com.zjhcsoft.icrm.contacts.vo.ContactsVO;
import com.zjhcsoft.icrm.customer.domain.Customer;
import com.zjhcsoft.rpc.context.impl.Request;

@Service
public class ContactsService {
	
	@Autowired
	private ContactsMapper contactsMapper;

	@Autowired
	private ContactWayService  contactWayService;
	@Autowired
	private ContactsGroupRelaService contactsGroupRelaService;
	
	public List<ContactsVO> getContactsPageList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", Request.getAttribute("start"));
		map.put("limit", Request.getAttribute("limit"));
		Contacts contacts = (Contacts) Request.getAttribute("domain");
		CommonUtil.filterToSelect(contacts);
		map.put("domain", contacts);
		return contactsMapper.getPageList(map);
	}

	public long getContactsTotalCount() {
		Map<String, Object> map = new HashMap<String, Object>();
		Contacts contacts = (Contacts) Request.getAttribute("domain");
		CommonUtil.filterToSelect(contacts);
		map.put("domain", contacts);
		return contactsMapper.getTotalSize(map);
	}
	
	public ContactsVO getContactsVOInfo(){
		String rowId = (String) Request.getAttribute("id");
		ContactsVO result;
		try {
			result = contactsMapper.getContactsVOInfo(rowId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Contacts selectByPrimaryKey() {
		String rowId = (String) Request.getAttribute("id");
		return selectByPrimaryKey(rowId);
	}
	
	
	public ResponseEnvelope insertOrUpdate() {
		ContactsVO contactsVO = (ContactsVO) Request.getAttribute("contactsVO");
		ResponseEnvelope<Object> re = new ResponseEnvelope<Object>();

		if(contactsVO.getUser()==null||StringUtils.isEmpty(contactsVO.getUser().getRowId())){
			contactsVO.setAssignSts(1);
			contactsVO.setAssignDate(null);
		}else{
			contactsVO.setAssignSts(2);
			contactsVO.setAssignDate(new Date());
		}
		try {
			
			if (StringUtils.isEmpty(contactsVO.getRowId())) {
				contactsVO.setRowId(UUIDGenerator.getUUID());
				insert(contactsVO);
			} else {
				updateByPrimaryKeySelective(contactsVO);
			}
			
			//联系方式信息
			List<ContactWay> contactWayList =  contactsVO.getContactWayList();
			if(contactWayList!=null){
				for(int i=0;i<contactWayList.size();i++){
					if(StringUtils.isEmpty(contactWayList.get(i).getContactsId())){
						contactWayList.get(i).setContactsId(contactsVO.getRowId());
					}
					contactWayService.insertOrUpdate(contactWayList.get(i));
				}
			}
			//联系人分组更新
			contactsGroupRelaService.updateByContactsId(contactsVO.getRowId(),contactsVO.getContactsGroup());
			//联系人池(联系人分配)
			re.setData(contactsVO.getRowId());
			re.setMessage("保存成功!");
			re.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			re.setMessage(e.getMessage());
			re.setSuccess(false);
		}
		return re;
	}
	
	public ResponseEnvelope deleteByPrimaryKey(){
		String id = (String) Request.getAttribute("id");
		ResponseEnvelope<Object> re = new ResponseEnvelope<Object>();

		try {
			// 删除对应联系方式
			contactWayService.deleteByContactsId(id);
			deleteByPrimaryKey(id);
			re.setSuccess(true);
			re.setMessage("删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			re.setSuccess(false);
			re.setMessage(e.getMessage());
		}
		return re;
	}
	
	
	public void updateAssignStsByIds(String[] ids,Integer assignSts){
		Contacts record = new Contacts();
		for(int i=0;i<ids.length;i++){
			record.setRowId(ids[i]);
			record.setAssignSts(assignSts);
			record.setAssignDate(new Date());
			contactsMapper.updateByPrimaryKeySelective(record);
		}
	}
	
	
	private int deleteByPrimaryKey(String rowId) {
		return contactsMapper.deleteByPrimaryKey(rowId);
	}

	private int insert(Contacts record) {
		return contactsMapper.insert(record);
	}

	private int insertSelective(Contacts record) {
		return contactsMapper.insertSelective(record);
	}

	private Contacts selectByPrimaryKey(String rowId) {
		return contactsMapper.selectByPrimaryKey(rowId);
	}

	private int updateByPrimaryKeySelective(Contacts record) {
		return contactsMapper.updateByPrimaryKeySelective(record);
	}

	private int updateByPrimaryKey(Contacts record) {
		return contactsMapper.updateByPrimaryKey(record);
	}
}