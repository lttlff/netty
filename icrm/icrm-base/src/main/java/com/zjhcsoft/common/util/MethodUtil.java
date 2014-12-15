package com.zjhcsoft.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class MethodUtil {

	/**
	 * 执行set方法
	 * 
	 * @param o执行对象
	 * @param fieldName属性
	 * @param value值
	 */
	public static void invokeSet(Object o, String fieldName, Object value) {
		Method method = getSetMethod(o.getClass(), fieldName);
		try {
			// System.out.println("target--------------"+method.getParameterTypes()[0].getName());
			// System.out.println("value--------------"+value.getClass().getName());
			if ("java.lang.String".equals(method.getParameterTypes()[0].getName())
					&& "java.lang.Long".equals(value.getClass().getName())) {
				value = value.toString();
			} else if ("java.lang.Long".equals(method.getParameterTypes()[0].getName())
					&& "java.lang.String".equals(value.getClass().getName())) {
				value = Long.valueOf(value.toString());
			}
			method.invoke(o, new Object[] { value });
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * java反射bean的get方法
	 * 
	 * @param objectClass
	 * @param fieldName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Method getGetMethod(Class objectClass, String fieldName) {
		StringBuffer sb = new StringBuffer();
		sb.append("get");
		sb.append(fieldName.substring(0, 1).toUpperCase());
		sb.append(fieldName.substring(1));
		try {
			return objectClass.getMethod(sb.toString());
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * java反射bean的set方法
	 * 
	 * @param objectClass
	 * @param fieldName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Method getSetMethod(Class objectClass, String fieldName) {
		try {
			Class[] parameterTypes = new Class[1];
			Field field = objectClass.getDeclaredField(fieldName);
			parameterTypes[0] = field.getType();
			StringBuffer sb = new StringBuffer();
			sb.append("set");
			sb.append(fieldName.substring(0, 1).toUpperCase());
			sb.append(fieldName.substring(1));
			Method method = objectClass.getMethod(sb.toString(), parameterTypes);
			return method;
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * 执行get方法
	 * 
	 * @param o执行对象
	 * @param fieldName属性
	 */
	public static Object invokeGet(Object o, String fieldName) {
		Method method = getGetMethod(o.getClass(), fieldName);
		try {
			if(method==null){
				return null;
			}else{
				return method.invoke(o, new Object[0]);
			}
		} catch (Exception e) {
//			 e.printStackTrace();
		}
		return null;
	}
}
