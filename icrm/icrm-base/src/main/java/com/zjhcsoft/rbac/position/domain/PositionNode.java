package com.zjhcsoft.rbac.position.domain;

import java.io.Serializable;
import com.zjhcsoft.common.tree.CommonTreeObj;

/**
 * @date 2014-6-17 上午09:28:30
 * @Title: PositionNode.java
 * @author lff
 */
public class PositionNode extends CommonTreeObj implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1956717495868609469L;
	Position domain;

	public Position getDomain() {
		return domain;
	}

	public void setDomain(Position domain) {
		this.domain = domain;
	}
	
}
