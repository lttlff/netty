package com.zjhcsoft.app.cache;

import java.util.HashMap;
import java.util.Map;

public class SessionMapThreadLocal {
	private static final ThreadLocal<Map> SESSION_MAP = new ThreadLocal<Map>();

	public static void set(String attribute, Object value) {
		Map map = (Map) SESSION_MAP.get();
		if (map == null) {
			map = new HashMap();
			SESSION_MAP.set(map);
		}
		map.put(attribute, value);
	}

	public static Object get(String attribute) {
		Map map = (Map) SESSION_MAP.get();
		return map.get(attribute);
	}

}