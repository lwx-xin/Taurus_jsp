package org.taurus.config.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private static SimpleDateFormat format = new SimpleDateFormat();
	
	/**
	 * 字符串转时间
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date strToDate(String dateStr, String pattern) {
		format.applyPattern(pattern);
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 时间转字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateToStr(Date date, String pattern) {
		format.applyPattern(pattern);
		String dateStr = null;
		dateStr = format.format(date);
		return dateStr;
	}
	
	/**
	 * 获取当前时间毫秒数
	 * @return
	 */
	public static long getNowDateLong() {
		return new Date().getTime();
	}

}
