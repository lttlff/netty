package com.zjhcsoft.common.util;

import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class UUIDGenerator {

	private static Queue<String> uuidQueue = new LinkedList<String>();

	public UUIDGenerator() {
	}

	/**
	 * 获得一个UUID
	 * @return
	 */
	public static String getUUID() {
		if (uuidQueue.size() < 10) {
			for (int i = 0; i < 200; i++) {
				uuidQueue.add(createUUID());
			}
		}
		return uuidQueue.poll();
	}

	private static String createUUID() {
		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
				+ s.substring(19, 23) + s.substring(24);
	}

	/**
	 * 获得指定数目的UUID
	 * 
	 * @param number
	 *            int 需要获得的UUID数量
	 * @return String[] UUID数组
	 */
	public static String[] getUUID(int number) {
		if (number < 1) {
			return null;
		}
		String[] ss = new String[number];
		for (int i = 0; i < number; i++) {
			ss[i] = getUUID();
		}
		return ss;
	}

	public static void main(String[] args) {
		String[] ss = getUUID(100);
		for (int i = 0; i < ss.length; i++) {
			System.out.println(ss[i]);
		}
	}
}
