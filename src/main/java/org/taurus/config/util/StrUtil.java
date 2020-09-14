package org.taurus.config.util;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;

public class StrUtil {
	
	/**
	 * 对象为空
	 * @param data
	 * @return
	 */
	public static boolean isEmpty(Object data) {
		if (data == null 
				|| data.equals("")
				|| data.toString().trim().equals("")) {
			return true;
		}
		return false;
	}	
	
	/**
	 * 对象不为空
	 * @param data
	 * @return
	 */
	public static boolean isNotEmpty(Object data) {
		return !isEmpty(data);
	}
    
	/**
	 * 两个对象相等（equals）
	 * @param str1
	 * @param str2
	 * @return
	 */
    public static boolean strIsEquals(String str1, String str2) {
        System.err.println("2");
    	return StringUtils.equals(str1, str2);
    }
    public static boolean strIsEquals(Object str1, Object str2) {
        System.err.println("1");
        if (isEmpty(str1) && isEmpty(str2)) {
            return true;
        } else if (isEmpty(str1) || isEmpty(str2)) {
            return false;
        } else if (str1.equals(str2)) {
            return true;
        }
        return false;
    }
    
	/**
	 * 两个对象不相等（equals）
	 * @param str1
	 * @param str2
	 * @return
	 */
    public static boolean strIsNotEquals(Object str1, Object str2) {
        return !strIsEquals(str1, str2);
    }
	
    /**
     * object转string
     * @param data
     * @return
     */
	public static String formatNull(Object data) {
		if (isEmpty(data)) {
			return "";
		} else {
			return String.valueOf(data);
		}
	}
	
	/**
	 * 获取uuid 有‘-’
	 * @return
	 */
    public static String getUUID(){
        //注意replaceAll前面的是正则表达式  
    	String uuid = UUID.randomUUID().toString().replaceAll("-","");
        return uuid;
    }
    
    /**
     * 获取急卖后的密码
     * @param userPwd 加密前的密码
     * @param userNumber账号
     * @return
     */
    public static String getMD5Pwd(String userPwd, String userNumber) {
    	return MD5Util_Spring.getMD5(userPwd, userNumber);
    }
}
