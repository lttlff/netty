package com.zjhcsoft.rpc.interceptor.filtercfg;

import java.util.ArrayList;
import java.util.List;

import com.zjhcsoft.rpc.interceptor.Interceptor;

public class ServiceFilter {
private static final List<Interceptor> filterInterceptorBeforeList=new ArrayList<Interceptor>();
	
	private static final List<Interceptor> filterInterceptorAfterList=new ArrayList<Interceptor>();
//	private static final List<Interceptor> filterInterceptorAroundList=new ArrayList<Interceptor>();
	
	public static List<Interceptor> getFilterInterceptorBeforeList(){
		return filterInterceptorBeforeList;
	}
	
	public static List<Interceptor> getFilterInterceptorAfterList(){
		return filterInterceptorAfterList;
	}
	
	public static void addFilterInterceptorBeforeList(Interceptor interceptor){
		filterInterceptorBeforeList.add(interceptor);
	}
	
	public static void addFilterInterceptorAfterList(Interceptor interceptor){
		filterInterceptorAfterList.add(interceptor);
	}
	
	public static void addFilterInterceptorBeforeAllList(List<Interceptor> interceptorList){
		filterInterceptorBeforeList.addAll(interceptorList);
	}
	
	public static void addFilterInterceptorAfterAllList(List<Interceptor> interceptorList){
		filterInterceptorAfterList.addAll(interceptorList);
	}

	//拦截器：类和方法拦截器组
	public static void addFilterInterceptorAroundList(Interceptor interceptor){
		addFilterInterceptorBeforeList(interceptor);
		addFilterInterceptorAfterList(interceptor);
	}
	
	public static List<Interceptor> getBeforeFilterList(){
		return getFilterInterceptorBeforeList();
	}
	
	public static List<Interceptor> getAfterFilterList(){
		return getFilterInterceptorAfterList();
	}

}
