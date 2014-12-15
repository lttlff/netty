package com.zjhcsoft.rpc.handler;

import com.zjhcsoft.rpc.context.RequestContextThreadLocal;
import com.zjhcsoft.rpc.context.impl.Request;
import com.zjhcsoft.rpc.context.impl.RequestContext;
import com.zjhcsoft.rpc.exception.ServiceInvokeException;
import com.zjhcsoft.rpc.interceptor.filtercfg.ServiceFilter;
import com.zjhcsoft.rpc.interceptor.interceptorcfg.ServiceInterceptor;
import com.zjhcsoft.rpc.invocation.ActionInterceptorInvocation;
import com.zjhcsoft.rpc.invocation.ActionInvocation;
import com.zjhcsoft.rpc.servlet.SpringContextInit;
import org.springframework.beans.BeansException;

public class DefaultServerHandler implements ServerHandler {

	public Object accept(Object requestContext) {
		RequestContext request = (RequestContext) requestContext;
		// 把request放置到本地线程变量
		RequestContextThreadLocal.requestStart(request);
		// ApplicationContextPortal.requestContextListenerStart();
		
		if (Request.getClassName() == null
				|| "".equals(Request.getClassName().trim())) {
			throw new ServiceInvokeException("服务调用的类名不能为空");
		}

		if (Request.getMethodName() == null
				|| "".equals(Request.getMethodName().trim())) {
			throw new ServiceInvokeException("服务调用的方法名不能为空");
		}

		Object obj = null;

		try {
//			obj = RequestContextThreadLocal.getServiceBeanContext().getBean(
//					Request.getClassName());
			obj =SpringContextInit.getApplicationContext().getBean(Request.getClassName());
			obj.getClass().getDeclaredMethod(Request.getMethodName());
		} catch (BeansException e) {
			e.printStackTrace();
			throw new ServiceInvokeException("服务调用的类名2:"
					+ Request.getClassName() + "查找失败2!!");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new ServiceInvokeException("服务调用的方法名2:"
					+ Request.getMethodName() + "查找失败2!!");
		}

		try {
			ActionInvocation invocation = new ActionInterceptorInvocation(
					ServiceFilter.getBeforeFilterList(), ServiceFilter
							.getAfterFilterList(), ServiceInterceptor
							.getBeforeInterceptorList(), ServiceInterceptor
							.getAfterInterceptorList());
			return invocation.invoke();
		} catch (RuntimeException e) {
			throw new RuntimeException("运行期服务调用出错!");
		}
		// } finally {
		// // if (actionContext != null) {
		// // actionContext.clear();//清除掉一次动作调用的临时变量
		// // }
		// }
		// }
	}

}
