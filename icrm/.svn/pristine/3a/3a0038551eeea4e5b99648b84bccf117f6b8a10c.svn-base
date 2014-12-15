package com.zjhcsoft.common.util;

import java.util.Map;
import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;
/**
 * 模板引擎 解析工具类
 * @author Administrator
 *
 */
public class VelocityUtil {
	private static VelocityEngine velocityEngineFactoryBean;
	
	static {
		Properties p = new Properties();
		p.put("resource.loader", "class");
		p.put("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		try {
			velocityEngineFactoryBean = new VelocityEngine(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static VelocityEngine getVelocityEngineFactoryBean() {
		return velocityEngineFactoryBean;
	}

	/**
	 * 得到解析后的字符串
	 * @param head_xml
	 * @param params
	 * @return
	 */
	public static String getVelocityXmlStr(String head_xml,Map params){
		String xml = VelocityEngineUtils.mergeTemplateIntoString(
				velocityEngineFactoryBean, head_xml,"UTF-8", params);
		return xml;
	}
	
	/**
	 * 解析，加编码
	 * @param head_xml
	 * @param encoding
	 * @param params
	 * @return
	 */
	public static String getVelocityXmlStr(String head_xml,String encoding,Map params){
		String xml = VelocityEngineUtils.mergeTemplateIntoString(
				velocityEngineFactoryBean, head_xml,encoding, params);
		return xml;
	}
}
