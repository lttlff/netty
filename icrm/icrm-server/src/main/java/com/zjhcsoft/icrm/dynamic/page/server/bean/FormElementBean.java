package com.zjhcsoft.icrm.dynamic.page.server.bean;

/**
 * 
 * @author zhunb
 * @date 2014-3-6
 * 
 */
public class FormElementBean extends BaseBean {

	private String ab;// 是否允许为空
	private String h;// 隐藏标识
	private String rw;// 只读标识
	private String script;// 脚本

	public FormElementBean() {
		super();
	}

	/**
	 * 
	 * @param ab
	 *            是否允许为空
	 * @param h
	 *            隐藏标识
	 * @param rw
	 *            只读标识
	 * @param script
	 *            脚本
	 */
	public FormElementBean(String ab, String h, String rw, String script) {
		super();
		this.ab = ab;
		this.h = h;
		this.rw = rw;
		this.script = script;
	}

	public String getAb() {
		return ab;
	}

	/**
	 * 
	 * @param ab
	 *            方法功能说明:允许为空
	 * @author zhunb 2014-3-6
	 */
	public void setAb(String ab) {
		this.ab = ab;
	}

	public String getH() {
		return h;
	}

	/**
	 * 
	 * @param h
	 *            方法功能说明:是否隐藏
	 * @author zhunb 2014-3-6
	 */
	public void setH(String h) {
		this.h = h;
	}

	public String getRw() {
		return rw;
	}

	/**
	 * 
	 * @param rw
	 *            方法功能说明:只读
	 * @author zhunb 2014-3-6
	 */
	public void setRw(String rw) {
		this.rw = rw;
	}

	public String getScript() {
		return script;
	}

	/**
	 * 
	 * @param script
	 *            方法功能说明:动态脚本
	 * @author zhunb 2014-3-6
	 */
	public void setScript(String script) {
		this.script = script;
	}

}
