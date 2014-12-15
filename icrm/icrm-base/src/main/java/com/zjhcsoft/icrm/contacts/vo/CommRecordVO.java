/**
 * 
 */
package com.zjhcsoft.icrm.contacts.vo;

import java.io.Serializable;

import com.zjhcsoft.icrm.contacts.domain.CommRecord;

/**
 * @Description: 沟通记录
 * @Author zhunb 
 * @Date 2014-5-15 下午7:22:04
 *
 */
public class CommRecordVO extends CommRecord implements Serializable{

	private static final long serialVersionUID = 8055679722835729620L;

	private String contactsName;//联系人名称

	public String getContactsName() {
		return contactsName;
	}

	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}
	
	
}
