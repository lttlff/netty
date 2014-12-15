package com.zjhcsoft.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StringFilter {
	static String[] allFilter = { "select", "update", "delete", "from", "where", "union", "'", "<", ">" };
	static String[] sqlFilter = { "select", "update", "delete", "from", "where", "union", "'" };
	static String[] xxsFilter = { "<", ">" };
	static String[] starFilter = { "*" };

	public static void reflectAllReplace(Object obj) {
		reflectReplace(obj, 0);
	}

	public static void reflectSqlReplace(Object obj) {
		reflectReplace(obj, 1);
	}

	public static void reflectXxsReplace(Object obj) {
		reflectReplace(obj, 2);
	}

	public static void reflectStarReplace(Object obj) {
		reflectReplace(obj, 3);
	}

	public static void reflectAllCheck(Object obj) {
		reflectCheck(obj, 0);
	}

	public static void reflectSqlCheck(Object obj) {
		reflectCheck(obj, 1);
	}

	public static void reflectXxsCheck(Object obj) {
		reflectCheck(obj, 2);
	}

	public static void reflectStarCheck(Object obj) {
		reflectCheck(obj, 3);
	}

	/**
	 * @author: 吕振华
	 * @Date：2010-7-28
	 * @desciptin：判断字符串中是否包含filter中过滤的字符。
	 * @param：输入String类型字符串，输入String类型过滤类型，0表示全部过滤；1表示sql过滤；2表示xxs过滤。
	 * @return：true表示包含；false表示不包含。
	 */
	public static boolean contain(String s, int filterType) {
		// 对所有特殊字符进行过滤。
		if (filterType == 0) {
			for (int i = 0; i < allFilter.length; i++) {
				if (s != null && s.toLowerCase().indexOf(allFilter[i]) != -1)
					return true;
			}
		}
		// 对sql注入字符进行过滤。
		else if (filterType == 1) {
			for (int i = 0; i < sqlFilter.length; i++) {
				if (s != null && s.toLowerCase().indexOf(sqlFilter[i]) != -1)
					return true;
			}
		}
		// 对xss字符进行过滤。
		else if (filterType == 2) {
			for (int i = 0; i < xxsFilter.length; i++) {
				if (s != null && s.toLowerCase().indexOf(xxsFilter[i]) != -1)
					return true;
			}
		}
		// 对*字符进行过滤。
		else if (filterType == 3) {
			for (int i = 0; i < starFilter.length; i++) {
				if (s != null && s.toLowerCase().indexOf(starFilter[i]) != -1)
					return true;
			}
		}
		return false;
	}

	/**
	 * @author: 吕振华
	 * @Date：2010-7-28
	 * @desciptin：通过反射的方式，传入Object对象，只验证该对象的String类型的成员变量，是否包含filter中的过滤字符，返回true。
	 * @param：输入Object类型对象。
	 * @return：true表示包含；false表示不包含。
	 */
	public static boolean reflectCheck(Object obj, int filterType) {
		if (obj == null) {
			return false;
		}
		// 获取对象的所有方法。
		Method[] methods = obj.getClass().getMethods();
		// 获取对象的所有成员变量。
		Field[] fields = obj.getClass().getDeclaredFields();

		// 遍历对象的所有成员变量。
		for (int i = 0; i < fields.length; i++) {
			// 构造成员变量对应的getX方法名。
			String getMethod = "get" + fields[i].getName().substring(0, 1).toUpperCase()
					+ fields[i].getName().substring(1, fields[i].getName().length());
			for (int j = 0; j < methods.length; j++) {
				// 若该getX方法存在。
				if (getMethod.equals(methods[j].getName())) {
					try {
						// 若该getX方法的返回类型为String，且该值包含filter中需要过滤的字符。
						if (methods[j].getReturnType().getSimpleName().equals("String")
								&& contain((String) methods[j].invoke(obj, new Object[] {}), filterType)) {
							return true;
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return false;
	}

	/**
	 * @author: 吕振华
	 * @Date：2010-7-28
	 * @desciptin：通过反射的方式，传入Object对象，只验证该对象的String类型的成员变量，是否包含filter中的过滤字符，若存在将对应的变量值置为""。
	 * @param：输入Object类型对象。
	 * @return：void。
	 */
	public static void reflectReplace(Object obj, int filterType) {
		if (obj == null) {
			return;
		}
		// 获取对象的所有方法。
		Method[] methods = obj.getClass().getMethods();
		// 获取对象的所有成员变量。
		Field[] fields = obj.getClass().getDeclaredFields();

		// 遍历对象的所有成员变量。
		for (int i = 0; i < fields.length; i++) {
			// 构造成员变量对应的getX方法名。
			String getMethod = "get" + fields[i].getName().substring(0, 1).toUpperCase()
					+ fields[i].getName().substring(1, fields[i].getName().length());
			for (int j = 0; j < methods.length; j++) {
				// 若该getX方法存在。
				if (getMethod.equals(methods[j].getName())) {
					try {
						// 若该getX方法的返回类型为String，且该值包含filter中需要过滤的字符。
						if (methods[j].getReturnType().getSimpleName().equals("String")
								&& contain((String) methods[j].invoke(obj, new Object[] {}), filterType)) {
							// 构造成员变量对应的setX方法名。
							String setMethod = "set" + fields[i].getName().substring(0, 1).toUpperCase()
									+ fields[i].getName().substring(1, fields[i].getName().length());
							for (int k = 0; k < methods.length; k++) {
								// 若该setX方法存在，且该方法只有一个传入参数，参数的类型为String。
								if (setMethod.equals(methods[k].getName())
										&& methods[k].getParameterTypes().length == 1
										&& methods[k].getParameterTypes()[0].getSimpleName().equals("String")) {
									// 若过滤类型为全部或sql注入过滤，执行setX方法，并将值置为""。
									if (filterType == 0 || filterType == 1) {
										methods[k].invoke(obj, "");
									}
									// 若过滤类型为xxs类型，执行setX方法，并将包含的特殊字符进行转义。
									else if (filterType == 2) {
										methods[k].invoke(obj, xxsEncode((String) methods[j].invoke(obj,
												new Object[] {})));
									}
									// 若过滤类型为*类型，执行setX方法，并将*转换成%。
									else if (filterType == 3) {
										methods[k].invoke(obj, starEncode((String) methods[j].invoke(obj,
												new Object[] {})));
									}
								}
							}
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * @author: 吕振华
	 * @Date：2010-8-2
	 * @desciptin：将字符串中包含xxs类型的字符进行转换。
	 * @param：输入String类型字符串。
	 * @return：输出不包含特殊字符的字符串。
	 */
	public static String xxsEncode(String s) {
		StringBuffer stringbuffer = new StringBuffer();
		int j = s.length();
		for (int i = 0; i < j; i++) {
			char c = s.charAt(i);
			switch (c) {
			case 60: // <
				stringbuffer.append("&lt;");
				break;
			case 62: // >
				stringbuffer.append("&gt;");
				break;
			default:
				stringbuffer.append(c);
				break;
			}
		}
		return new String(stringbuffer.toString());
	}

	/**
	 * @author: 吕振华
	 * @Date：2010-8-2
	 * @desciptin：将字符串中包含*类型的字符进行转换。
	 * @param：输入String类型字符串。
	 * @return：输出将*转换成%的字符串。
	 */
	public static String starEncode(String s) {
		StringBuffer stringbuffer = new StringBuffer();
		int j = s.length();
		for (int i = 0; i < j; i++) {
			char c = s.charAt(i);
			switch (c) {
			case 42: // *
				stringbuffer.append("%");
				break;
			default:
				stringbuffer.append(c);
				break;
			}
		}
		return new String(stringbuffer.toString());
	}

}
