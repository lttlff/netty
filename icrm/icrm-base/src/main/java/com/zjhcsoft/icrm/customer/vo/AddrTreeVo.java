/**
 * 
 */
package com.zjhcsoft.icrm.customer.vo;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.zjhcsoft.common.tree.CommonTreeObj;
import com.zjhcsoft.icrm.customer.domain.Addr;

/**
 * @Description: TODO
 * @Author zhunb
 * @Date 2014-5-23 下午2:35:48
 * 
 */
public class AddrTreeVo  extends CommonTreeObj implements Serializable{

	private static final long serialVersionUID = 7006359245893958933L;
//	private String parent;
	private String addrFull;
	private String parentAddrId;
	
	
	public String getParentAddrId() {
		return parentAddrId;
	}

	public void setParentAddrId(String parentAddrId) {
		this.parentAddrId = parentAddrId;
	}


//	public String getParent() {
//		return parent;
//	}
//
//	public void setParent(String parent) {
//		this.parent = parent;
//	}


	public String getAddrFull() {
		return addrFull;
	}

	public void setAddrFull(String addrFull) {
		this.addrFull = addrFull;
	}

}
