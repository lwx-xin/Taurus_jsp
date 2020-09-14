package org.taurus.config.util;

import java.math.BigDecimal;

public class NumberUtil {
	
	/**
	 * 零
	 */
	public static final BigDecimal zero = BigDecimal.ZERO;
	
	/**
	 * String 转 Integer
	 * @param data
	 * @return
	 */
	public static Integer formatInteger(String data) {
		if (StrUtil.isEmpty(data)) {
			return 0;
		}
		return Integer.parseInt(data);
	}
	
	/**
	 * Integer 转 Long
	 * @param data
	 * @return
	 */
	public static Long formatLong(Integer data) {
		if (data == null) {
			return 0L;
		}
		return Long.parseLong(data.toString());
	}
	
	/**
	 * String 转 Long
	 * @param data
	 * @return
	 */
	public static Long formatLong(String data) {
		if (data == null) {
			return 0L;
		}
		return Long.parseLong(data.toString());
	}
	
	/**
	 * 转换成BigDecimal
	 * @param number
	 * @return
	 */
	public static BigDecimal formatNumber(Object number) {
		if (StrUtil.isEmpty(number)) {
			return zero;
		}
		return new BigDecimal(String.valueOf(number));
	}
	
	/**
	 * BigDecimal 加算
	 * @param numbers
	 * @return
	 */
	public static BigDecimal addNumber(Object...numbers) {
		if (ListUtil.isEmpty(numbers)) {
			return zero;
		}
		BigDecimal result = zero;
		for (Object number : numbers) {
			result = result.add(formatNumber(number));
		}
		return result;
	}
	
	/**
	 * BigDecimal 减算
	 * @param number1
	 * @param number2
	 * @return number1-number2
	 */
	public static BigDecimal subNumber(Object number1, Object number2) {
		return formatNumber(number1).subtract(formatNumber(number2));
	}
	
	/**
	 * BigDecimal 乘法
	 * @param numbers
	 * @return
	 */
	public static BigDecimal multiplyNumber(Object...numbers) {
		if (ListUtil.isEmpty(numbers)) {
			return zero;
		}
		BigDecimal result = zero;
		for (Object number : numbers) {
			result = result.multiply(formatNumber(number));
		}
		return result;
	}
	
	/**
	 * BigDecimal 除法
	 * @param number1
	 * @param number2
	 * @return number1 ÷ number2
	 */
	public static BigDecimal divideNumber(Object number1, Object number2) {
		return formatNumber(number1).divide(formatNumber(number2));
	}

	/**
	 * 获取随机数(包含最小值和最大值)[min,max]
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static Integer getRandom(Integer min, Integer max) {
		int ran = (int) (Math.random() * (max - min + 1) + min);
		return ran;
	}
}
