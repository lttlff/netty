package com.zjhcsoft.rpc.listener.context;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.zjhcsoft.rpc.context.RequestContextThreadLocal;
import com.zjhcsoft.rpc.listener.ApplicationContextAttributeEvent;
import com.zjhcsoft.rpc.listener.ApplicationContextAttributeListener;
import com.zjhcsoft.rpc.listener.ApplicationContextEvent;
import com.zjhcsoft.rpc.listener.ApplicationContextListener;
import com.zjhcsoft.rpc.listener.RequestContextAttributeEvent;
import com.zjhcsoft.rpc.listener.RequestContextAttributeListener;
import com.zjhcsoft.rpc.listener.RequestContextEvent;
import com.zjhcsoft.rpc.listener.RequestContextListener;

public class ListenerContext {
	private static final Log LOG = LogFactory.getLog(ListenerContext.class);
	private static boolean isStart = false;
	private static Object LockedObject = new Object();
	public static final List<RequestContextListener> requestContextListenerList=new ArrayList<RequestContextListener>();
	public static final List<RequestContextAttributeListener> requestContextAttributeListenerList=new ArrayList<RequestContextAttributeListener>();
	
	public static final List<ApplicationContextListener> applicationContextListenerList=new ArrayList<ApplicationContextListener>();
	public static final List<ApplicationContextAttributeListener> applicationContextAttributeListenerList=new ArrayList<ApplicationContextAttributeListener>();
	
	
	public static void init(){
		if (!isStart) {//静态变量只会切换一次,后面的值都是false
			synchronized (LockedObject) {
				if (!isStart) {
					isStart = true;
					applicationContextListenerStart();////初始化容器的全局上下文监听器，从缓存获取或者从数据库获取，并都设置到全局的上下文对象中
				}
			}
		}
	}
	
	//每个请求进入都需要调用setRequestContext(new RequestContextImpl());将初始化request数据空间，然后把参数都设置入这个RequestContextImpl
	private static void requestContextListenerStart() {
		LOG.debug("RequestContext start");
//		setRequestContext(new RequestContextImpl());
		RequestContextListener listener=null;
		for (int i = 0; i < requestContextListenerList.size(); i++) {
			listener =  requestContextListenerList.get(i);
			listener.contextInitialized(new RequestContextEvent());//执行request创建时的监听器,监听器不需要在request中，只需要循环遍历调用就可以
		}
	}
	
	private static void requestContextListenerEnd() {
		try {
			LOG.debug("RequestContext end");
			RequestContextListener listener=null;
			for (int i = 0; i < requestContextListenerList.size(); i++) {
				listener = requestContextListenerList.get(i);
				listener.contextDestroyed(new RequestContextEvent());
			}
		} finally {
//			if (getRequestContext() != null) {
//				getRequestContext().clear();//直接可以remove
//			}
////			setRequestContext(null);
//			removeContextThreadLocal();
		}
	}
	
	private static void requestContextAttributeAddedListener(){
		RequestContextAttributeListener listener=null;
		for (int i = 0; i < requestContextAttributeListenerList.size(); i++) {
			listener = requestContextAttributeListenerList.get(i);
			listener.attributeAdded(new RequestContextAttributeEvent());
		}
	}
	
	private static void requestContextAttributeRemovedListener(){
		RequestContextAttributeListener listener=null;
		for (int i = 0; i < requestContextAttributeListenerList.size(); i++) {
			listener = requestContextAttributeListenerList.get(i);
			listener.attributeRemoved(new RequestContextAttributeEvent());
		}
	}
	
	private static void requestContextAttributeReplacedListener(){
		RequestContextAttributeListener listener=null;
		for (int i = 0; i < requestContextAttributeListenerList.size(); i++) {
			listener = requestContextAttributeListenerList.get(i);
			listener.attributeReplaced(new RequestContextAttributeEvent());
		}
	}
	
	
	

	
	public static void applicationContextListenerStart() {
		if (!isStart) {
			LOG.debug("GlobalContext start");
//			List<ApplicationContextListener> applicationContextListenerList = ApplicationContextPortal.applicationContextListenerList;
			for (int i = 0; i < applicationContextListenerList.size(); i++) {
				ApplicationContextListener listener = applicationContextListenerList.get(i);
				listener.contextInitialized(new ApplicationContextEvent());//将每个监听器加入ApplicationContext
			}
		}
	}
	
	//在全局上下文变量中销毁从缓存获取的或者从数据库获取的每个监听器
	public static void applicationContextListenerStop() {
		try {
			LOG.debug("GlobalContext stop");
//			List<ApplicationContextListener> applicationContextListenerList = ApplicationContextPortal.applicationContextListenerList;
			for (int i = 0; i < applicationContextListenerList.size(); i++) {
				ApplicationContextListener listener =  applicationContextListenerList.get(i);
				listener.contextDestroyed(new ApplicationContextEvent());
			}
		} finally {
//			getApplicationContext().clear();
//			setApplicationContext(null);
		}
	}
	
	private static void applicationContextAttributeAddedListener(){
//		List<ApplicationContextAttributeListener> applicationContextAttributeListenerList = ApplicationContextPortal.applicationContextAttributeListenerList;//通过配置对象从缓存或者数据库获取request上下文监听器
		ApplicationContextAttributeListener listener=null;
		for (int i = 0; i < applicationContextAttributeListenerList.size(); i++) {
			listener = applicationContextAttributeListenerList.get(i);
			//先获取本地线程变量，实现ContextListener接口的具体request监听器实现类没找到(从RequestContextImpl接口看出监听器没实现)，应该是将其设置入request本地线程变量中
			listener.attributeAdded(new ApplicationContextAttributeEvent());
		}
	}
	
	private static void applicationContextAttributeReplacedListener(){
//		List<ApplicationContextAttributeListener> applicationContextAttributeListenerList = ApplicationContextPortal.applicationContextAttributeListenerList;//通过配置对象从缓存或者数据库获取request上下文监听器
		ApplicationContextAttributeListener listener=null;
		for (int i = 0; i < applicationContextAttributeListenerList.size(); i++) {
			listener = applicationContextAttributeListenerList.get(i);
			//先获取本地线程变量，实现ContextListener接口的具体request监听器实现类没找到(从RequestContextImpl接口看出监听器没实现)，应该是将其设置入request本地线程变量中
			listener.attributeReplaced(new ApplicationContextAttributeEvent());
		}
	}
	
	private static void applicationContextAttributeRemovedListener(){
//		List<ApplicationContextAttributeListener> applicationContextAttributeListenerList = ApplicationContextPortal.applicationContextAttributeListenerList;//通过配置对象从缓存或者数据库获取request上下文监听器
		ApplicationContextAttributeListener listener=null;
		for (int i = 0; i < applicationContextAttributeListenerList.size(); i++) {
			listener = applicationContextAttributeListenerList.get(i);
			//先获取本地线程变量，实现ContextListener接口的具体request监听器实现类没找到(从RequestContextImpl接口看出监听器没实现)，应该是将其设置入request本地线程变量中
			listener.attributeRemoved(new ApplicationContextAttributeEvent());
		}
	}
}
