package com.zjhcsoft.rpc.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.zjhcsoft.rpc.cfg.RpcXmlConfigure_server;
import com.zjhcsoft.rpc.cfg.domain.ParamDomain;
import com.zjhcsoft.rpc.cfg.domain.ServerDomain;
import com.zjhcsoft.rpc.handler.ServerHandler;
import com.zjhcsoft.rpc.interceptor.route.InterceptorChainBuild;
import com.zjhcsoft.rpc.tcpserver.TcpServer;
import org.sevenstar.util.BeanHelper;

public class RpcServerStartListener implements ServletContextListener {

	private static Log LOG = LogFactory.getLog(RpcServerStartListener.class);

	public void contextDestroyed(ServletContextEvent event) {

	}

	public void contextInitialized(ServletContextEvent event) {
//		ServiceFilter.addFilterInterceptorBeforeList(new ConcurrencyInterceptor());
//		ServiceFilter.addFilterInterceptorAroundList(new ConcurrencyInterceptor2());
//		ServiceFilter.addFilterInterceptorAroundList(new ConcurrencyInterceptor3());
//		
//		ServiceFilter.addFilterInterceptorAroundList(new ConcurrencyInterceptor10());
//		
//		ServiceFilter.addFilterInterceptorAfterList(new ConcurrencyInterceptor8());
//		ServiceFilter.addFilterInterceptorAfterList(new ConcurrencyInterceptor9());
//		

//		

//		List<Interceptor> list2=new ArrayList<Interceptor>();
//		list2.add(new ConcurrencyInterceptor3());
//		list2.add(new ConcurrencyInterceptor4());
//		//org.dacell.test.mybatis
//		List<Interceptor> list=new ArrayList<Interceptor>();
//		list.add(new ConcurrencyInterceptor());
//		list.add(new ConcurrencyInterceptor2());
//		ServiceInterceptor.addInterceptorBeforeList("org.*.test", list2);//前面对象拦截
		
//		List<Interceptor> list4=new ArrayList<Interceptor>();
//		list4.add(new ConcurrencyInterceptor7());
//		ServiceInterceptor.addInterceptorAroundList("*.*.*.mybatis", list4);//前面对象拦截
//		ServiceInterceptor.addInterceptorAroundList("org.dacell.*(getName2)", list);//前面方法拦截
//		
//		
//		
//		List<Interceptor> list3=new ArrayList<Interceptor>();
//		list3.add(new ConcurrencyInterceptor5());
//		list3.add(new ConcurrencyInterceptor6());
//		ServiceInterceptor.addInterceptorAfterList("org.*.*.mybatis", list3);//();
		
		InterceptorChainBuild.initFilterInterceptor();
		
		
		List serverDomainList = RpcXmlConfigure_server.getServerCfgDomain()// 这里还可以获取协议和拦截器
				.getServerList();// 从配置文件获取各rpc服务的配置
		for (int i = 0; i < serverDomainList.size(); i++) {
			ServerDomain serverDomain = (ServerDomain) serverDomainList.get(i);
			Map paramMap = new HashMap();
			for (int j = 0; j < serverDomain.getParamList().size(); j++) {
				ParamDomain paramDomain = (ParamDomain) serverDomain
						.getParamList().get(j);
				paramMap.put(paramDomain.getName(), paramDomain.getValue());
			}
			LOG.error("server[" + serverDomain.getName() + "] className["
					+ serverDomain.getClassName() + "] param["
					+ paramMap.toString() + "] prepare start....");
			TcpServer server = (TcpServer) BeanHelper.newInstance(serverDomain
					.getClassName());// 实例化rpc服务端,以下服务器属性配置
			server.setPort(Integer.parseInt((String) paramMap.get("port")));
			server.setHandler((ServerHandler) BeanHelper
					.newInstance((String) paramMap.get("serverHandler")));// 设置服务启动时候的处理handler，是服务路由的入口
			server.setMinIdleNum(Integer.parseInt((String) paramMap
					.get("minIdle")));
			server.setMaxActiveNum(Integer.parseInt((String) paramMap
					.get("maxActive")));
			server.setSoTimeout(Integer.MAX_VALUE);
			server.start();
//			System.out.println("监听器启动完成.......");
			// LOG.error("server["+serverDomain.getName()+"]
			// className["+serverDomain.getClassName()+"]
			// param["+paramMap.toString()+"] prepare start success");
		}
	}

}
