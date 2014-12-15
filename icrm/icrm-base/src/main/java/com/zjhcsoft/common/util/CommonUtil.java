package com.zjhcsoft.common.util;


import java.io.IOException ;
import java.io.InputStreamReader ;
import java.io.LineNumberReader ;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field ;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method ;
import java.math.BigDecimal;
import java.security.MessageDigest ;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest ;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 公共 有关的
 * 
 * @author ye
 * @date 2011/5/3
 * @version 2.0
 */

public class CommonUtil {

	public static List listMapToLowerCase(List list) {
		if (list == null) {
			return null;
		}
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			if (HashMap.class.equals(obj.getClass())) {// 若对象的类型不是hashmap则不进行转化
				HashMap map = (HashMap) list.get(i);
				list.set(i, mapToLowerCase(map));
			} else if (LinkedHashMap.class.equals(obj.getClass())) {
				LinkedHashMap map = (LinkedHashMap) list.get(i);
				list.set(i, linkedHashMapToLowerCase(map));
			}

		}
		return list;
	}

	public static HashMap mapToLowerCase(HashMap inMap) {
		if (inMap == null) {
			return null;
		}
		HashMap resultMap = new HashMap();
		for (Iterator it = inMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry e = (Map.Entry) it.next();
			String key = e.getKey().toString().toLowerCase();
			Object valueObj = e.getValue();
			resultMap.put(key, valueObj);
		}
		return resultMap;
	}

	public static LinkedHashMap linkedHashMapToLowerCase(LinkedHashMap inMap) {
		if (inMap == null) {
			return null;
		}
		LinkedHashMap resultMap = new LinkedHashMap();
		for (Iterator it = inMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry e = (Map.Entry) it.next();
			String key = e.getKey().toString().toLowerCase();
			Object valueObj = e.getValue();
			resultMap.put(key, valueObj);
		}
		return resultMap;
	}

	public static String changeEncode(String str, String fromEncode,
			String toEncode) throws UnsupportedEncodingException {
		String returnStr;
		returnStr = new String(str.getBytes(fromEncode), toEncode);
		return returnStr;
	}

	public static String changeEncodeToUtf8(String str)
			throws UnsupportedEncodingException {
		return changeEncode(str, "ISO-8859-1", "UTF-8");
	}

	public static String formatDate(Date date, String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(date);
	}

	public static String formatDate(Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}

	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date toDate(String date_string, String format) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat(format);
			return sf.parse(date_string);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date toDate(String date_string) {
		String format = "yyyy-MM-dd";
		if (date_string.indexOf(":") >= 0) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		return toDate(date_string, format);
	}

	public static boolean isDateFormat(String date_string, String format){
		boolean falg = true;
		try {
			SimpleDateFormat sf = new SimpleDateFormat(format);
			sf.parse(date_string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			falg = false;
		}
		return falg;
	}
	
	public static List<String> changeToList(String str) {
		if (isEmpty(str)) {
			return null;
		}
		List<String> list = new ArrayList();
		int fromIndex = 0;
		int toIndex = 0;
		while ((toIndex = str.indexOf("},{", fromIndex)) > -1) {
			String s = str.substring(fromIndex, toIndex + 1);
			fromIndex = toIndex + 2;
			list.add(s);
		}
		String s = str.substring(fromIndex);
		if (!isEmpty(s)) {
			list.add(s);
		}
		return list;
	}

	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEmpty(Object str) {
		if (str == null) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEmpty(Map map) {
		if (map == null || map.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEmpty(Long str) {
		if (str == null) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEmpty(List list) {
		if (list == null || list.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEmpty(String[] arr) {
		if (arr == null || arr.length == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 把jsonobject对象转化为HashMap对象
	 * 
	 * @param jsonObject
	 * @return
	 */
	public static HashMap jsonToMap(JSONObject jsonObject) {
		HashMap map = new HashMap();
		Iterator keys = jsonObject.keys();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			Object value_obj = jsonObject.get(key);
			String class_name = value_obj.getClass().toString();
			if ("class net.sf.json.JSONArray".equals(class_name)) {
				JSONArray arr = (JSONArray) value_obj;
				map.put(key, arr.toList(arr));
			} else {
				map.put(key, value_obj);
			}

		}
		return map;
	}

	public static HashMap extGridOut(List root, int totalProperty) {
		HashMap resultMap = new HashMap();
		resultMap.put("root", root);
		resultMap.put("totalProperty", totalProperty);
		return resultMap;
	}

	/**
	 * 从map中获取String
	 * 
	 * @date 2011-7-19
	 * @time 下午05:18:31
	 * @param map
	 * @param key
	 * @return
	 */
	public static String getMapString(HashMap map, String key) {
		if (map == null) {
			return null;
		}
		Object value_obj = map.get(key);
		if (value_obj == null) {
			return null;
		} else {
			return value_obj.toString().trim();
		}
	}

	/**
	 * 从map中获取long
	 * 
	 * @date 2011-7-19
	 * @time 下午05:18:20
	 * @param map
	 * @param key
	 * @return
	 */
	public static Long getMapLong(HashMap map, String key) {
		String value = getMapString(map, key);
		if (isEmpty(value)) {
			return null;
		} else {
			return Long.parseLong(value);
		}
	}

	public static Double getMapDouble(HashMap map, String key) {
		String value = getMapString(map, key);
		if (isEmpty(value)) {
			return null;
		} else {
			return Double.parseDouble(value);
		}
	}

	/**
	 * 从map中获取Int
	 * 
	 * @date 2011-7-19
	 * @time 下午05:18:20
	 * @param map
	 * @param key
	 * @return
	 */
	public static Integer getMapInt(HashMap map, String key) {
		String value = getMapString(map, key);
		if (isEmpty(value)) {
			return null;
		} else {
			return Integer.parseInt(value);
		}
	}

	/**
	 * 从map中获取string数组
	 * 
	 * @date 2011-7-19
	 * @time 下午05:18:03
	 * @param map
	 * @param key
	 * @return
	 */
	public static String[] getMapStringArray(HashMap map, String key) {
		Object value_obj = map.get(key);
		Class value_class = value_obj.getClass();
		String class_simple_name = value_class.getSimpleName();
		if ("String[]".equals(class_simple_name)) {
			return (String[]) value_obj;
		} else if ("String".equals(class_simple_name)) {
			return new String[] { value_obj.toString() };
		} else {
			return null;
		}
	}

	public static HashMap stringToMap(String str) {
		HashMap map = null;
		try {
			JSONObject json_obj = new JSONObject();
			json_obj = json_obj.fromObject(str);
			map = jsonToMap(json_obj);
		} catch (Exception e) {// 出错不影响全局
		}
		return map;
	}

	/**
	 * 用在特定的环境中，作用：将map中的数组转变为字符串
	 * 
	 * @date 2011-6-20
	 * @time 下午10:03:40
	 * @param inMap
	 * @return
	 */
	public static HashMap changeMap(HashMap inMap) {
		HashMap map = new HashMap();
		if (inMap != null) {
			for (Iterator it = inMap.entrySet().iterator(); it.hasNext();) {
				Map.Entry e = (Map.Entry) it.next();
				String key = e.getKey().toString();
				Object valueObj = e.getValue();
				if (String[].class.equals(valueObj.getClass())) {
					valueObj = ((String[]) valueObj)[0];
				}
				map.put(key, valueObj.toString());
			}
		}
		return map;
	}

	/**
	 * 在位数不到total的情况下前面补0,默认长度过长则裁剪
	 * 
	 * @date 2011-7-21
	 * @time 上午11:36:16
	 * @param number
	 * @param total
	 * @param cut_if_long
	 * @return
	 */
	public static String addZeroToNumber(Long number, int total) {
		if (number == null) {
			return null;
		}
		String number_string = number.toString();
		return addZeroToNumber(number_string, total, true);
	}

	/**
	 * 在位数不到total的情况下前面补0
	 * 
	 * @date 2011-7-21
	 * @time 上午11:36:16
	 * @param number
	 * @param total
	 * @param cut_if_long
	 * @return
	 */
	public static String addZeroToNumber(String number, int total,
			boolean cut_if_long) {
		if (number == null) {
			return null;
		}
		int length = number.length();
		if (length < total) {
			for (int i = length; i < total; i++) {
				number = "0" + number;
			}
		} else if (cut_if_long && length > total) {
			number = number.substring(length - total, length);
		}
		return number;
	}

	public static String format(String value, String type) {
		if ("".equals(value)) {
			value = "''";
		} else {
			if ("date".equals(type)) {
				value = "to_date('" + value + "','YYYY/MM/DD HH24:MI:SS')";
			} else if ("int".equals(type) || "string".equals(type)) {
				value = "'" + value + "'";
			}
		}

		return value;
	}

	public static String getNowTimeString() {
		return getNowTimeString("yyyyMMddHHmmssSSS");
	}

	public static String getNowTimeString(String format) {
		return formatDate(new Date(), format);
	}

	public static String arrayToString(String[] arr) {
		if (arr == null) {
			return null;
		} else {
			String re = "";
			for (int i = 0; i < arr.length; i++) {
				re += arr[i] + ",";
			}
			if (!"".equals(re)) {
				re = re.substring(0, re.length() - 1);
			}
			return re;
		}
	}

	public static String listToString(List<String> list) {
		return listToString(list, ",");
	}

	public static String listToString(List<String> list, String split) {
		if (list == null) {
			return null;
		} else {
			String re = "";
			for (String s : list) {
				re += s + split;
			}
			if (!"".equals(re)) {
				re = re.substring(0, re.length() - 1);
			}
			return re;
		}
	}

	/**
	 * 取得两个数组中的公共部分
	 * 
	 * @author ye
	 * @date 2011-11-6
	 * @time 上午12:19:06
	 * @updater
	 * @update-time
	 * @update-info
	 * @param new_phone_numbers
	 * @param old_phone_numbers
	 * @return
	 */
	public static List getExistNumbers(List<String> new_phone_numbers,
			List<String> old_phone_numbers) {
		List<String> result_list = new ArrayList(Arrays
				.asList(new String[old_phone_numbers.size()]));
		Collections.copy(result_list, old_phone_numbers);
		result_list.retainAll(new_phone_numbers);
		return result_list;
	}

	/**
	 * 取得第一个数组相对第二个数组新增的
	 * 
	 * @author ye
	 * @date 2011-11-6
	 * @time 上午12:19:25
	 * @updater
	 * @update-time
	 * @update-info
	 * @param new_phone_numbers
	 * @param old_phone_numbers
	 * @return
	 */
	public static List getNewNumbers(List<String> new_phone_numbers,
			List<String> old_phone_numbers) {
		List<String> result_list = new ArrayList(Arrays
				.asList(new String[new_phone_numbers.size()]));
		Collections.copy(result_list, new_phone_numbers);
		result_list.removeAll(old_phone_numbers);
		return result_list;
	}

	/**
	 * 取得第一个数组相对第二个数组缺少的
	 * 
	 * @author ye
	 * @date 2011-11-6
	 * @time 上午12:19:44
	 * @updater
	 * @update-time
	 * @update-info
	 * @param new_phone_numbers
	 * @param old_phone_numbers
	 * @return
	 */
	public static List getRemoveNumbers(List<String> new_phone_numbers,
			List<String> old_phone_numbers) {
		List<String> result_list = new ArrayList(Arrays
				.asList(new String[old_phone_numbers.size()]));
		Collections.copy(result_list, old_phone_numbers);
		result_list.removeAll(new_phone_numbers);
		return result_list;
	}

	public static String urlEncode(String word) {
		return java.net.URLEncoder.encode(word);
	}

	public static String urlEncode(String word, String code)
			throws UnsupportedEncodingException {
		return java.net.URLEncoder.encode(word, code);
	}

	public static String urlDecode(String word) {
		return java.net.URLDecoder.decode(word);
	}

	public static String urlDecode(String word, String code)
			throws UnsupportedEncodingException {
		return java.net.URLDecoder.decode(word, code);
	}

	public static Date addMonth(Date from_date, int month) {
		Date d = (Date) from_date.clone();
		int new_month = d.getMonth();
		new_month += month;
		int add_year = new_month / 12;
		new_month %= 12;
		if (add_year > 0) {
			d.setYear(d.getYear() + add_year);
		}
		d.setMonth(new_month);
		return d;
	}

	public static boolean isBeforeNow(Date from_date) {
		Date now_date = new Date();
		if (from_date.before(now_date)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isBeforeOrEqualNow(Date from_date) {
		Date now_date = new Date();
		if (now_date.before(from_date)) {
			return false;
		} else {
			return true;
		}
	}

	public static int getDaysBetweenDate(Date from_date, Date to_date) {
		int days;
		days = (int) ((to_date.getTime() - from_date.getTime()) / (24 * 60 * 60 * 1000));
		return days;
	}

	public static double getDoubleLeft(double f, int place) {
		BigDecimal b = new BigDecimal(f);
		double f1 = b.setScale(place, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	public static void main(String[] args) {
		System.out.println(CommonUtil.getNowTimeString());
	}

	public static void main4(String[] args) {
		Date d1 = new Date("2011/12/8");
		boolean d2 = CommonUtil.isBeforeNow(d1);
		System.out.println(d2);
	}

	public static void main2(String[] args) {
		Date d1 = new Date("2000/02/01");
		d1 = CommonUtil.addMonth(d1, 36);
		System.out.println(CommonUtil.formatDate(d1));
	}

	public static void main3(String[] args) {
		Date d1 = new Date("2000/02/01");
		Date d2 = new Date("2000/03/01");
		int b1 = CommonUtil.getDaysBetweenDate(d1, d2);
		Date d3 = new Date("2011/02/01");
		Date d4 = new Date("2011/03/01");
		int b2 = CommonUtil.getDaysBetweenDate(d3, d4);
		System.out.println(b1);
		System.out.println(b2);
	}

	/**
	 * 若转换异常则返回0
	 * 
	 * @param perscent
	 * @return
	 */
	public static float perscentToFloat(String perscent) {
		float result = 0f;
		if (!CommonUtil.isEmpty(perscent)) {
			int indexOfp = perscent.indexOf("%");
			if (indexOfp != -1) {
				perscent = perscent.replaceAll("%", "");
			}
			try {
				result = Float.parseFloat(perscent);
			} catch (Exception e) {
			}
		}
		result = result / 100;
		return result;

	}
	
	


	public static String formatNullToString(Object b) {
		if (b == null) {
			return "";
		} else {
			return b.toString();
		}
	}

	/**
	 * checkbox Y/N  与Ext匹配转换
	 * @param value
	 * @return
	 */
	public static String checkBoxValChange(String value) {
		if (value != null) {
			//后台至前台
			if ("Y".equals(value)) {
				return "1";
			}
			if ("N".equals(value)) {
				return "0";
			}

			//前台至后台
			if ("on".equals(value) || "true".equals(value)) {
				return "Y";
			} else {
				return "N";
			}
		} else {
			return value;
		}
	}

	/**
	 * 格式化异常信息提示
	 * 
	 * @param ex
	 * @param retStr
	 * @return
	 */
	public static String formatExceptionInfoFor(String ex, String retStr) {
		if (retStr == null) {
			retStr = "";
		}
		retStr = ex.replace("\n", "\\n");

		return retStr;
	}

	/**
	 * 处理搜索语句
	 * 
	 * @param obj
	 */
	public static void filterToSelect(Object obj) {
		StringFilter.reflectStarReplace(obj);// 将字段值中"*"转换成"%"。
	}

	/**
	 * 将* 替换成 %
	 * @param cc
	 * @return
	 * @author Zhunb	Jul 13, 2012
	 */
	public static String replaceAsterisk(String cc){
		return StringFilter.starEncode(cc);
	}
	
	/*
	 * 当输入条件多个且非对应domain中属性时使用该方法,规则如下 输入值"key1:value1;key2:value2...."
	 * 
	 */
	public static Map splitCondition(String condition) {
		Map map = new HashMap();
		if (condition != null && !condition.equals("")) {
			String conditionnew = tansformCondition(condition);

			// 先拆分分号
			String[] kv = conditionnew.split(";");
			for (int i = 0; i < kv.length; i++) {
				String[] kv_ = kv[i].split(":");
				if (kv_.length >= 2) {
					for (int j = 2; j < kv_.length; j++) {
						if (kv_[j] != null && !kv_[j].equals("") && !kv_[j].equals("undefined")) {
							kv_[1] = kv_[1] + kv_[j];
						}
					}
					if (kv_[1] != null && !kv_[1].equals("") && !kv_[1].equals("undefined")) {
						map.put(kv_[0], kv_[1]);
					}

				}

			}
		}
		return map;

	}

	public static int getPageNoByStart(int start, int pageSize) {
		int pageNo = 1;
		try {
			if (pageSize == 0) {
				pageSize = 10;
			}
			if (start == 0) {
				pageNo = 1;
			} else {
				pageNo = (start / pageSize) + 1;
			}
		} catch (Exception e) {
		}
		return pageNo;
	}

	/*
	 * @param 将*等标示符转换为符合sql的语句 就是
	 * 
	 */
	public static String tansformCondition(String condition) {

		return condition.replace("*", "%");

	}

	public static Long getPageNumber(Long pageSize, Long total) {
		if (total == null || total == 0) {
			return 0l;
		}
		if (pageSize == 0) {
			pageSize = 10l;
		}
		if ((total % pageSize) == 0) {
			return (total / pageSize);
		} else {
			return (total / pageSize) + 1;
		}

	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */

	public static double doubleAdd(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */

	public static double doubleSub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 把字符串true false 转换成 N Y fore_flag=Util.formateToYN(fore_flag);//转换 true
	 * false
	 * 
	 * @param instr
	 * @return
	 */
	public static String formateToYN(String instr) {
		if (instr == null)
			instr = "";
		if (instr.trim().equalsIgnoreCase("true"))
			instr = "Y";
		else if (instr.trim().equalsIgnoreCase("false"))
			instr = "N";
		return instr;
	}

	/**
	 * 把字符串true false 转换成 N Y fore_flag=Util.formateToYN(fore_flag);//转换 true
	 * false
	 * 
	 * @param instr
	 * @return
	 */
	public static String formateNullONToYN(String instr) {
		if (instr == null)
			instr = "N";
		if (instr.trim().equalsIgnoreCase("true"))
			instr = "Y";
		else if (instr.trim().equalsIgnoreCase("false"))
			instr = "N";
		else if (instr.trim().equalsIgnoreCase("on"))
			instr = "Y";
		return instr;
	}

	/**
	 * 把字符串 N Y 转换成 true false fore_flag=Util.formateToYN(fore_flag);//转换 true
	 * false
	 * 
	 * @param instr
	 * @return
	 */
	public static String formateToTrueFalse(String instr) {
		if (instr == null)
			instr = "false";
		if (instr.trim().equalsIgnoreCase("Y"))
			instr = "true";
		else if (instr.trim().equalsIgnoreCase("N"))
			instr = "false";
		return instr;
	}

	/**
	 * MD5 加密
	 * 
	 * @param s
	 *            待加密码字符串
	 * @return 字符串
	 */
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 时间转换成固定的字符串
	 * 
	 * @param date
	 * @param _format
	 * @return
	 */
	public static String formateDateToString(Date date, String _format) {
		String dateString = "";
		if (_format == null || _format.trim().equals("")) {
			_format = "yyyy-MM-dd";
		}
		if (date != null) {
			// SimpleDateFormat simpleDateFormat = new
			// SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(_format);
			dateString = simpleDateFormat.format(date);
		}

		return dateString;
	}

	/**
	 * 获取domain中属性为string的字段组成的条件map，其中属性值中的"*"替换为"%"
	 * 
	 * @author-LIH
	 * @param domain实例
	 * @return map
	 */
	public static Map getObj2Map(Map map, Object obj) {
		Method[] methods = obj.getClass().getMethods();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String getMethod = "get" + fields[i].getName().substring(0, 1).toUpperCase()
					+ fields[i].getName().substring(1, fields[i].getName().length());
			for (int j = 0; j < methods.length; j++) {
				if (getMethod.equals(methods[j].getName())) {
					try {
						Object value = methods[j].invoke(obj, new Object[] {});
						if (value != null) {
							map.put(fields[i].getName(), value.toString());
							if (methods[j].getReturnType().getSimpleName().equals("String")) {
								map.put(fields[i].getName(), value.toString().replace("*", "%"));
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
		return map;
	}

	/**
	 * 得到真实IP
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		//		String MAC = getMACAddress(ip);
		return ip;
	}

	/**
	 * 获取客户端MAC地址
	 * @param ip
	 * @return
	 */
	public static String getMACAddress(String ip) {
		String str = "";
		String macAddress = "";
		try {
			Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC Address") > 1) {
						macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length());
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return macAddress;
	}

	public static Object invokeGet(Object target, String name) {
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		String getMethodName = "get" + name;

		Object res = null;
		try {
			Method method = target.getClass().getMethod(getMethodName);
			res = method.invoke(target);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static void invokeSet(Object o, String fieldName, Object value) throws Exception {
		fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		String setMethodName = "set" + fieldName;
		Method method = o.getClass().getMethod(setMethodName,new Class[]{value.getClass()});
		method.invoke(o, new Object[] { value });
	}
	
	// 初始化空字符2000
	public static String initStrArr() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 4000; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}

	//
	public static boolean isNotEmpty(String s) {
		return s != null && !s.trim().equalsIgnoreCase("");
	}

	// 去除NULL 转化成空格
	public static Object getNullOut(Object obj) {
		Field[] list = obj.getClass().getDeclaredFields();
		for (int i = 0; i < list.length; i++) {
			String name = list[i].getName();
			Class<?> type = list[i].getType();
			// ////System.out.println(type);
			if (type.toString().equals("".getClass().toString())) {
				if (CommonUtil.invokeGet(obj, name) == null || "NULL".equals(CommonUtil.invokeGet(obj, name).toString().trim())) {
					// 获取所以属性的set方法
					try {
						Method method = obj.getClass().getMethod(
								"set" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length()),
								String.class);
						method.invoke(obj, "");
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
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
		return obj;
	}
	/**
	 * 获得domain被修改的字段
	 * @param fieldMap  String或Date类型
	 * @param newDomain
	 * @param oldDomain
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static Map getModifyFields(Map fieldMap,Object newDomain,Object oldDomain) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Map modifyFields = new HashMap();
		if(fieldMap==null){
			fieldMap = PropertyUtils.describe(newDomain);
		}
		Iterator it = fieldMap.keySet().iterator();
		while(it.hasNext()){
			String key = (String)it.next();
			Object new_value = PropertyUtils.getProperty(newDomain, key);//有可能为null
			Object old_value = PropertyUtils.getProperty(oldDomain, key);//有可能为null
			if(new_value!=null){ //new_value!=null
				if(new_value.getClass().equals(String.class)){
					String new_valueString = (String)new_value;
					String old_valueString = (String)old_value;
					if(StringUtils.isBlank(new_valueString) && StringUtils.isBlank(old_valueString)){
						continue;
					} else if(!new_value.equals(old_value)){
						modifyFields.put(key, fieldMap.get(key));
					}
				} else {
					if(!new_value.equals(old_value)){
						modifyFields.put(key, fieldMap.get(key));
					}
				}
			} else {//new_value==null
				if(old_value == null){//old_value == null
					continue;
				} else {//old_value != null
					if(old_value.getClass().equals(String.class)){
						String new_valueString = (String)new_value;
						String old_valueString = (String)old_value;
						if(StringUtils.isBlank(new_valueString) && StringUtils.isBlank(old_valueString)){
							continue;
						} else if(!old_value.equals(new_value)){
							modifyFields.put(key, fieldMap.get(key));
						}
					} else {
						if(!old_value.equals(new_value)){
							modifyFields.put(key, fieldMap.get(key));
						}
					}
				}
			}
			
		}
		return modifyFields;
	}
}