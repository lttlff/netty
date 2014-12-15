package com.zjhcsoft.rpc.interceptor.route;

import java.util.ArrayList;
import java.util.List;

import com.zjhcsoft.component.ClassPathHelper;
import com.zjhcsoft.rpc.context.RequestContextThreadLocal;
import com.zjhcsoft.rpc.context.impl.Request;
import com.zjhcsoft.rpc.servlet.SpringContextInit;

public class InterceptorPatternRoute {
	
	public static List<String> getInterceptorPatternList(List<String> interceptorPatternListOrderParam){
		//
//		String classPath=ClassPathHelper.getClass(RequestContextThreadLocal.getServiceBeanContext().getBean(Request.getClassName()));
		
		String classPath=ClassPathHelper.getClass(SpringContextInit.getApplicationContext().getBean(Request.getClassName()));
		List<String> realInterceptorPatternList=new ArrayList<String>();
		
		String interceptorPattern=null;
		
		List<String> interceptorPatternListOrder= interceptorPatternListOrderParam;
		if(interceptorPatternListOrder.size()!=0){
			for(String interceptorBeforeListOrderTemp:interceptorPatternListOrder){
	            interceptorPattern=interceptorPattern.copyValueOf(interceptorBeforeListOrderTemp.toCharArray());
//	            System.out.println("!!!!!!!!!!!!!!!=="+interceptorPattern);
	            boolean methodFlag=false;
	            boolean methodFlagFinal=false;
	            String methodTakeTemp=null;
	            
	            //检测是否是拦截方法
	            if(interceptorPattern!=null&&interceptorPattern.lastIndexOf('(')!=-1){
	            	methodTakeTemp=interceptorPattern;
	            	//如果拦截方法先截取拦截类的匹配模式
	            	interceptorPattern=interceptorPattern.substring(0, interceptorPattern.lastIndexOf('('));
	            	//标识拦截的是方法，但未对方法检测成功
	            	methodFlag=true;
	            	//方法名截取
	            	methodTakeTemp=methodTakeTemp.substring(methodTakeTemp.lastIndexOf('(')+1, methodTakeTemp.lastIndexOf(')'));
	            }
			String[] interceptorPackageSplits=interceptorPattern.split("\\.");
			String[] serviceClassPackageSplits=classPath.split("\\.");
			boolean flag=true;
			
				//这里配置的包的路径可能不全
				for(int i=0;i<interceptorPackageSplits.length;i++){
					String interceptorPackageSplit=interceptorPackageSplits[i];
//					System.out.println("interceptorPackageSplits["+i+"]:"+interceptorPackageSplits[i]+" pk serviceClassPackageSplits["+i+"]:"+serviceClassPackageSplits[i]);
					if("*".equalsIgnoreCase(interceptorPackageSplit.trim())){
						continue;
						}
					if(interceptorPackageSplit!=null&&(!interceptorPackageSplit.equalsIgnoreCase(serviceClassPackageSplits[i]))){
						flag=false;
						break;
					}
				}
				
				//检测是否拦截该方法
				if (methodFlag) {
					if (methodTakeTemp != null
							&& methodTakeTemp.equals(Request.getMethodName())) {
						// 对方法拦截检测成功
						methodFlagFinal = true;
					}
				}
				
				//如果是拦截方法
				if(methodFlagFinal&&(interceptorPattern!=null)){
					realInterceptorPatternList.add(interceptorBeforeListOrderTemp);
				}
				//如果是拦截类
				if(!methodFlag&&flag&&(interceptorPattern!=null)){
					realInterceptorPatternList.add(interceptorBeforeListOrderTemp);
				}
			}		
		}
		return realInterceptorPatternList;
	}

}
