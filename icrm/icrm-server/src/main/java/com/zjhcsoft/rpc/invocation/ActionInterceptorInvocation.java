package com.zjhcsoft.rpc.invocation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.zjhcsoft.rpc.context.RequestContextThreadLocal;
import com.zjhcsoft.rpc.interceptor.Interceptor;
import com.zjhcsoft.rpc.server.service.impl.DefaultService;

public class ActionInterceptorInvocation implements ActionInvocation {

	private static Log LOG = LogFactory.getLog(ActionInterceptorInvocation.class);

	private List<Interceptor> filterInterceptorBeforeList;
	private List<Interceptor> hasFilterInterceptorBeforeList;
	
	private List<Interceptor> filterInterceptorAfterList;
	private List<Interceptor> hasFilterInterceptorAfterList;
	
	private List<Interceptor> allInterceptorMethodOrObjBefore;
	private List<Interceptor> hasAllInterceptorMethodOrObjBefore;
	
	private List<Interceptor> allInterceptorMethodOrObjAfter;
	private List<Interceptor> hasAllInterceptorMethodOrObjAfter;

	private boolean executed = false;

	public ActionInterceptorInvocation(
			List<Interceptor> filterInterceptorBeforeList,
			List<Interceptor> filterInterceptorAfterList,
			List<Interceptor> allInterceptorMethodOrObjBefore,
			List<Interceptor> allInterceptorMethodOrObjAfter) {

		this.filterInterceptorBeforeList = filterInterceptorBeforeList;
		if (this.filterInterceptorBeforeList == null) {
			this.filterInterceptorBeforeList = new ArrayList<Interceptor>();
		}
		hasFilterInterceptorBeforeList = new ArrayList<Interceptor>();

		this.filterInterceptorAfterList = filterInterceptorAfterList;
		if (this.filterInterceptorAfterList == null) {
			this.filterInterceptorAfterList = new ArrayList<Interceptor>();
		}
		hasFilterInterceptorAfterList = new ArrayList<Interceptor>();

		this.allInterceptorMethodOrObjBefore = allInterceptorMethodOrObjBefore;
		if (this.allInterceptorMethodOrObjBefore == null) {
			this.allInterceptorMethodOrObjBefore = new ArrayList<Interceptor>();
		}
		hasAllInterceptorMethodOrObjBefore = new ArrayList<Interceptor>();

		this.allInterceptorMethodOrObjAfter = allInterceptorMethodOrObjAfter;
		if (this.allInterceptorMethodOrObjAfter == null) {
			this.allInterceptorMethodOrObjAfter = new ArrayList<Interceptor>();
		}
		hasAllInterceptorMethodOrObjAfter = new ArrayList<Interceptor>();
	}
	
	private Interceptor getNextInterceptor(List<Interceptor> interceptorList,
			List<Interceptor> hasInterceptorList) {
		if (hasInterceptorList.size() < interceptorList.size()) {
			Interceptor interceptor = (Interceptor) (interceptorList
					.get(hasInterceptorList.size()));
			hasInterceptorList.add(interceptor);
			return interceptor;
		} else {
			return null;
		}
	}

	public Object invoke() {
		
		Interceptor filterBefer = getNextInterceptor(filterInterceptorBeforeList,hasFilterInterceptorBeforeList);
		if (filterBefer != null) {
			filterBefer.invoke(this);
		}
		
		Interceptor interceptorBefore = getNextInterceptor(allInterceptorMethodOrObjBefore,hasAllInterceptorMethodOrObjBefore);
		if (interceptorBefore != null) {
			interceptorBefore.invoke(this);
		}
		
		if (!hasExecuted()) {
			setHasExecuted();
			//结果已在后面接口写到本地线程的response中,结果可以从本地线程变量的response中获取
			DefaultService.execute();
		}
		
		Interceptor interceptorAfter = getNextInterceptor(allInterceptorMethodOrObjAfter,hasAllInterceptorMethodOrObjAfter);
		if (interceptorAfter != null) {
			interceptorAfter.invoke(this);
		}

		Interceptor filterAfter = getNextInterceptor(filterInterceptorAfterList,hasFilterInterceptorAfterList);
		if (filterAfter != null) {
			filterAfter.invoke(this);
		}
		Object object =RequestContextThreadLocal.getResponseContext().getResult();
		
		return object;
	}

	public boolean hasExecuted() {
		return executed;
	}

	public void setHasExecuted() {
		this.executed = true;
	}
}
