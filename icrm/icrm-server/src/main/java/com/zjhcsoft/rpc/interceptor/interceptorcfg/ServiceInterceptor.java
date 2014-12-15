package com.zjhcsoft.rpc.interceptor.interceptorcfg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zjhcsoft.rpc.interceptor.Interceptor;
import com.zjhcsoft.rpc.interceptor.route.InterceptorPatternRoute;

public class ServiceInterceptor {
	private static final Map<String,List<Interceptor>> interceptorBeforeList=new HashMap<String,List<Interceptor>>();
	private static final List<String> interceptorBeforeListOrder=new ArrayList<String>();//维护多组拦截器的顺序
	
	private static final Map<String,List<Interceptor>> interceptorAfterList=new HashMap<String,List<Interceptor>>();
	private static final List<String> interceptorAfterListOrder=new ArrayList<String>();//维护多组拦截器的顺序
	
	private static final Map<String,List<Interceptor>> interceptorAroundAfterTempList=new HashMap<String,List<Interceptor>>();
	private static final List<String> interceptorAroundAfterTempListOrder=new ArrayList<String>();//维护多组拦截器的顺序
	
	private static final Map<String,List<Interceptor>> interceptorAfterTempList=new HashMap<String,List<Interceptor>>();
	private static final List<String> interceptorAfterTempListOrder=new ArrayList<String>();//维护多组拦截器的顺序
	
//	private static final Map<String,List> interceptorAroundList=new HashMap<String,List>();
	public static final Map<String,List<Interceptor>> interceptorExceptionList=new HashMap<String,List<Interceptor>>();
	
	//拦截器：类和方法拦截器组
	public static void addInterceptorAroundAfterTempList(String pattern,List<Interceptor> interceptorList){
		interceptorAroundAfterTempListOrder.add(pattern);
		interceptorAroundAfterTempList.put(pattern, interceptorList);
	}
	
	public static Map<String, List<Interceptor>> getInterceptorAfterTempList() {
		return interceptorAfterTempList;
	}

	public static List<String> getInterceptorAfterTempListOrder() {
		return interceptorAfterTempListOrder;
	}

	//拦截器：类和方法拦截器组
	public static void addInterceptorAfterTempList(String pattern,List<Interceptor> interceptorList){
		interceptorAfterTempListOrder.add(pattern);
		interceptorAfterTempList.put(pattern, interceptorList);
	}
	
	public static Map<String, List<Interceptor>> getInterceptorAroundAfterTempList() {
		return interceptorAroundAfterTempList;
	}

	public static List<String> getInterceptorAroundAfterTempListOrder() {
		return interceptorAroundAfterTempListOrder;
	}

	//拦截器：类和方法拦截器组
	public static void addInterceptorBeforeList(String pattern,List<Interceptor> interceptorList){
		interceptorBeforeListOrder.add(pattern);
		interceptorBeforeList.put(pattern, interceptorList);
	}
	//如果拦截器组从这里进入的话顺序将没法维护
	public static Map<String,List<Interceptor>> getInterceptorBeforeList(){
		return interceptorBeforeList;//这里应该做一个复制，不能让引用流入出去
	}
	
	public static List<String> getInterceptorBeforeListOrder(){
		return interceptorBeforeListOrder;
	}
	
	//拦截器：类和方法拦截器组,围绕的话，是不是应该让前面第一个的，在后面最后一个返回,从xml读取的话,应该容易控制
	//到时这个接口需要重新打散
//	public static void addInterceptorAroundList(String pattern,List<Interceptor> interceptorList){
//		interceptorBeforeListOrder.add(pattern);
//		interceptorBeforeList.put(pattern, interceptorList);
//		
//		interceptorAfterListOrder.add(pattern);
//		interceptorAfterList.put(pattern, interceptorList);
//	}
	
	
	
	//拦截器：类和方法拦截器组
	public static void addInterceptorAfterList(String pattern,List<Interceptor> interceptorList){
		interceptorAfterListOrder.add(pattern);
		interceptorAfterList.put(pattern, interceptorList);
	}
	//如果拦截器组从这里进入的话顺序将没法维护
	public static Map<String,List<Interceptor>> getInterceptorAfterList(){
		return interceptorAfterList;//这里应该做一个复制，不能让引用流入出去
	}
	
	public static List<String> getInterceptorAfterListOrder(){
		return interceptorAfterListOrder;
	}
	
	//前置拦截器获取
	public static List<Interceptor> getBeforeInterceptorList(){
		Map<String,List<Interceptor>> interceptorBeforeMap=getInterceptorBeforeList();
		List<String> interceptorBeforeOrderList=getInterceptorBeforeListOrder();
		List<Interceptor> interceptorBeforeList=getInterceptorsFromPatternList(interceptorBeforeMap,interceptorBeforeOrderList);
		return interceptorBeforeList;
	}
	
	//后置拦截器获取
	public static List<Interceptor> getAfterInterceptorList(){
		Map<String,List<Interceptor>> interceptorAfterMap=getInterceptorAfterList();
		List<String> interceptorAfterOrderList=getInterceptorAfterListOrder();
		List<Interceptor> interceptorAfterList=getInterceptorsFromPatternList(interceptorAfterMap,interceptorAfterOrderList);
		return interceptorAfterList;
	}
	
	private static List<Interceptor> getInterceptorsFromPatternList(Map<String,List<Interceptor>> interceptorPatternMap,List<String> interceptorPatternListOrderParam){
		List<Interceptor> classAllInterceptors=new ArrayList<Interceptor>();
		
		//拦截类和方法的集合
		for(String patternInInterceptorMap:InterceptorPatternRoute.getInterceptorPatternList(interceptorPatternListOrderParam)){
			List<Interceptor> interceptorInOneGroup=interceptorPatternMap.get(patternInInterceptorMap);
			for(Interceptor interceptor:interceptorInOneGroup){
				//统一对该类有效的所有类拦截器和方法拦截器
				classAllInterceptors.add(interceptor);
			}
		}
		return classAllInterceptors;
	}

}
