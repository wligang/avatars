package com.wlgdo.avatar.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * 日期帮助类,提供常用的日期操作方法
 * 
 * @author: dk
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static List<String> patterns = new ArrayList<>();

	static {
		patterns.add("yyyy-MM-dd HH:mm:ss");
		patterns.add("yyyy-MM-dd HH:mm");
		patterns.add("yyyy-MM-dd");
		patterns.add("yyyy-MM");
		patterns.add("yyyy/MM/dd HH:mm:ss");
		patterns.add("yyyy/MM/dd HH:mm");
		patterns.add("yyyy/MM/dd");
		patterns.add("yyyy/MM");
		patterns.add("yyyyMMdd HH:mm:ss");
		patterns.add("yyyyMMdd HH:mm");
		patterns.add("yyyyMMddHHmmss");
		patterns.add("yyyyMMddHHmm");
		patterns.add("yyyyMMdd");
		patterns.add("yyyyMM");
		patterns.add("yyyy");
	}

	/** 下面两行代码要放在静态代码块下面 否则拿不到patterns */
	protected static Date minDate = toDate("1971");
	protected static Date maxDate = toDate("3000");

	/**
	 * 从String 类型转换成 Date类型
	 */
	public static Date toDate(String time) {
		SimpleDateFormat df = new SimpleDateFormat();
		Iterator<String> it = patterns.iterator();
		while (it.hasNext()) {
			try {
				df.applyPattern(it.next());
				return df.parse(time);
			} catch (ParseException pattern) {
			}
		}
		return null;
	}

	/**
	 * 从Date 类型 根据模式 转换成 String 类型
	 */
	public static String toString(Date time, String format) {
		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern(format);
		return null == time ? "" : df.format(time);
	}

	public static Date parseDate(String date, String format) {
		try {
			return org.apache.commons.lang3.time.DateUtils.parseDate(date, new String[] { format });
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 设定模式 返回当前日期字符串,部分模式如下: yyyy-MM-dd yyyy/MM/dd HH:mm:ss
	 */
	public static String getDateTime(String format) {
		return toString(new Date(), format);
	}

	/**
	 * 返回当前日期后的N天
	 * 
	 * @param date
	 * @param n
	 * @param format
	 * @return
	 */
	public static String afterNDay(Date date, int n, String format) {
		Calendar c = Calendar.getInstance();
		if (isBlank(format)) {
			format = "yyyy-MM-dd";
		}
		DateFormat df = new SimpleDateFormat(format);
		c.setTime(date);
		c.add(Calendar.DATE, n);
		Date d2 = c.getTime();
		String s = df.format(d2);
		return s;
	}

	/**
	 * 返回当前日期N月后的日期
	 * 
	 * @param date
	 * @param n
	 * @param format
	 * @return
	 */
	public static String afterNMonth(Date date, int n, String format) {
		Calendar c = Calendar.getInstance();
		if (isBlank(format)) {
			format = "yyyy-MM-dd";
		}
		DateFormat df = new SimpleDateFormat(format);
		c.setTime(date);
		c.add(Calendar.MONTH, n);
		Date d2 = c.getTime();
		String s = df.format(d2);
		return s;
	}

	/**
	 * 返回当前日期N年后的日期
	 * 
	 * @param date
	 * @param n
	 * @param format
	 * @return
	 */
	public static String afterNYear(Date date, int n, String format) {
		Calendar c = Calendar.getInstance();
		if (isBlank(format)) {
			format = "yyyy-MM-dd";
		}
		DateFormat df = new SimpleDateFormat(format);
		c.setTime(date);
		c.add(Calendar.YEAR, n);
		Date d2 = c.getTime();
		String s = df.format(d2);
		return s;
	}

	/**
	 * 获取一天的开始时刻
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateStartTimeOfDay(Date date) {
		return toDate(toString(date, "yyyyMMdd") + "000000");
	}

	public static void main(String[] args) {
		System.out.println(afterNYear(new Date(), 2, null));
	}
}