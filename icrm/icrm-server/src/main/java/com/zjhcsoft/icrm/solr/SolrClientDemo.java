package com.zjhcsoft.icrm.solr;

import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.TimeZone;

import net.sf.json.JSONObject;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;

public class SolrClientDemo {
	public static final String SOLR_URL = "http://localhost:8888/solr/solr_test";

	/**
	 * 创建索引或者更新索引，通过BEAN的方式。ID存在的话就是更新，不存在的话就是创建
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TimeZone timeZone = TimeZone.getTimeZone("UTC");
		Calendar cal = Calendar.getInstance();

		String str = "{\"id\":\"5\",\"prod_offer_name\":\"427\",\"eff_date\":\"1995-12-31T23:59:59Z\",\"brand_id\":\"杭州\",\"prod_offer_id\":\"\",\"manager_grade\":\"杭州\",\"bu_id\":\"234\",\"order_flg\":\"on\",\"prod_offer_desc\":\"阿斯顿发生大幅2\"}";

		// TODO Auto-generated method stub
		SolrServer solr = null;
		solr = new HttpSolrServer(SOLR_URL);
		// List<DemoModel> list = new ArrayList<DemoModel>();
		// DemoModel model = null;
		// model = new DemoModel();
		// model.id = "bean_111111";
		// model.name = "奥运总共204块金牌";
		// model.type = "bean";
		// model.createtime = "NOW/DAY-1DAY";// 当前日期减一天
		// list.add(model);
		// model = new DemoModel();
		// model.id = "bean_222222";
		// model.name = "中国又添一枚金牌,中国获得38块金牌";
		// model.type = "bean";
		// model.createtime = "NOW";// 当前时间，包括具体的时间
		// list.add(model);
		// model = new DemoModel();
		// model.id = "bean_333333";
		// model.name = "美国获得47块金牌";
		// model.type = "bean";
		// model.createtime = "NOW/DAY";// 当前日期,时间为00:00:00
		// list.add(model);
		// model = new DemoModel();
		// model.id = "bean_444444";
		// model.name = "法国获得18块金牌";
		// model.type = "bean";
		// model.createtime = "1995-12-31T23:59:59Z";// 直接写UTC格式字符串
		// list.add(model);
		try {
			// solr.addBeans(list);
			SolrInputDocument doc = new SolrInputDocument();
			JSONObject jsonObj = JSONObject.fromObject(str);
			Iterator it = jsonObj.keys();
			String key;
			while (it.hasNext()) {
				key = (String) it.next();
				doc.addField(key, jsonObj.get(key));
			}
			solr.add(doc);
			solr.commit();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SolrServerException e1) {
			e1.printStackTrace();
		}
		System.out.println("finish。。。。。。。。。。。");
	}
}
