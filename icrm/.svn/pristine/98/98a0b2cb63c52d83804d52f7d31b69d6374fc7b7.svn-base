/**
 * 
 */
package com.zjhcsoft.icrm.contacts.vo;

import java.io.Serializable;
import java.util.List;

import com.zjhcsoft.icrm.contacts.domain.ContactWay;
import com.zjhcsoft.icrm.contacts.domain.Contacts;
import com.zjhcsoft.icrm.contacts.domain.ContactsGroup;
import com.zjhcsoft.icrm.customer.domain.Customer;
import com.zjhcsoft.rbac.user.domain.User;

/**
 * @Description: 联系人信息页面模型
 * @Author zhunb
 * @Date 2014-5-14 上午9:38:11
 * 
 */
public class ContactsVO extends Contacts implements Serializable{

	private static final long serialVersionUID = -6936157627651764598L;
	private List<ContactWay> contactWayList;//联系方式

	private ContactsGroup contactsGroup;//联系人分组
	
	private User user;//销售人员
	
	private String addrFull;//全路径地址名称
	
	private List<Customer> customerList;
	
	
	public List<ContactWay> getContactWayList() {
		return contactWayList;
	}

	public void setContactWayList(List<ContactWay> contactWayList) {
		this.contactWayList = contactWayList;
	}

	public ContactsGroup getContactsGroup() {
		return contactsGroup;
	}

	public void setContactsGroup(ContactsGroup contactsGroup) {
		this.contactsGroup = contactsGroup;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAddrFull() {
		return addrFull;
	}

	public void setAddrFull(String addrFull) {
		this.addrFull = addrFull;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}




}
