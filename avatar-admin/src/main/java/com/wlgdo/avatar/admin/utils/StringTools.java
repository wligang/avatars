package com.wlgdo.avatar.admin.util;

/**
 * @author: Ligang.Wang[wlgchun@163.com]
 * @date: 2019/04/26
 */
public class StringTools {

	public static boolean isNullOrEmpty(String str) {
		return null == str || "".equals(str) || "null".equals(str);
	}

	public static boolean isNullOrEmpty(Object obj) {
		return null == obj || "".equals(obj);
	}
}
