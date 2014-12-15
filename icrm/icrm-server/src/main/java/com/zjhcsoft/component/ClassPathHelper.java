package com.zjhcsoft.component;

import java.util.Date;

public class ClassPathHelper {
	// 获取包路径
	private static String getPackageName(Object obj) {
		return obj.getClass().getPackage().getName();
	}

	// 获取类名
	private static String getClassName(Object obj) {
		return obj.getClass().getSimpleName();
	}

	// 获取类路径
	public static String getClass(Object obj) {
		return obj.getClass().getName();
	}

	public static void main(String[] args) {
		Date date = new Date();
		System.out.println("Package Name = " + getPackageName(date));
		System.out.println("Class Name =" + getClassName(date));
		System.out.println("Class = " + getClass(date));
	}
}
