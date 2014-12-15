package com.zjhcsoft.rpc.server.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.zjhcsoft.rpc.context.RequestContextThreadLocal;
import com.zjhcsoft.rpc.context.impl.Request;
import com.zjhcsoft.rpc.context.impl.Response;
import com.zjhcsoft.rpc.exception.ServiceInvokeException;
import com.zjhcsoft.rpc.servlet.SpringContextInit;
import com.zjhcsoft.rpc.tcpserver.protocol.TcpProtocolFactory;
import org.sevenstar.util.BeanHelper;
import org.springframework.beans.BeansException;

public class DefaultService {

	@SuppressWarnings("unchecked")
	public static Object execute() {
		Method method=null;
		try {//SpringInit.getApplicationContext().getBean(Request.getClassName());
//			method = BeanHelper.getMethod((RequestContextThreadLocal.getServiceBeanContext().getBean(Request
//					.getClassName())).getClass(), Request.getMethodName());
			
			method = BeanHelper.getMethod((SpringContextInit.getApplicationContext().getBean(Request
					.getClassName())).getClass(), Request.getMethodName());

//			Object result=method.invoke((RequestContextThreadLocal.getServiceBeanContext().getBean(Request
//					.getClassName())));
			
			Object result=method.invoke((SpringContextInit.getApplicationContext().getBean(Request
					.getClassName())));
			//write result in response of threadLocal
			Response.setResult(result);
			
			Long resultObjSize = new Long(0);
			resultObjSize = getObjectSize(Request.getDataProtocol(), RequestContextThreadLocal.getResponseContext());
//			Response.setResponseLength(resultObjSize);
			//size of response
			RequestContextThreadLocal.getResponseContext().setResponseLength(resultObjSize);
			
			return result;
		}catch (BeansException e) {
			e.printStackTrace();
			throw new ServiceInvokeException("服务调用的类名:"+Request.getClassName()+"查找失败!!");
			//需要捕获参数异常
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} 
		catch (InvocationTargetException e) {
			e.printStackTrace();
			Response.setSuccess(false);
			Response.setResult(e);
			Response.setMessage("调用服务:"+Request.getClassName()+"方法:"+Request.getMethodName()+"失败!"+e.getMessage());
//			throw new ServiceInvokeException("调用服务:"+Request.getClassName()+"方法:"+Request.getMethodName()+"失败!"+e.getMessage());
		}catch (RuntimeException e) {
			//这里应该抛nosuchmethod异常，需要改进
			throw new ServiceInvokeException("服务调用的方法名:"+Request.getMethodName()+"查找失败!!");
		}
		
		return null;
	}
	
	private static Long getObjectSize(String dataProtocol,Object obj){
		if(obj == null){
			return new Long(0);
		}
		if(TcpProtocolFactory.getProtocol(dataProtocol) != null){
			return (TcpProtocolFactory.getProtocol(dataProtocol)).getObjectSize(obj);
		}else{
			throw new RuntimeException("wrong protocol["+dataProtocol+"]");
		}
	}

/*	public static boolean isMapEmpty(Map map) {
		Set<String> key = map.keySet();
		if (key.size() == 1) {
			for (Iterator it = key.iterator(); it.hasNext();) {
				String s = (String) it.next();
				if ("result".equals(s)) {
					return true;
				}
				return false;
			}
		}
		return false;
	}*/

}
