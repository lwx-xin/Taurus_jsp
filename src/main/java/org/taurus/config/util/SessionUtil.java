package org.taurus.config.util;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.taurus.entity.sys.TSUserEntity;

public class SessionUtil {
	
	private static final String USER_INFO= "userInfo";
	
	/**
	 * 清除全部session
	 * @param session
	 */
	public static void clearSession(HttpSession session) {
		Enumeration<String> em = session.getAttributeNames();
        while (em.hasMoreElements()) {
        	session.removeAttribute(em.nextElement().toString());
        }
	}
	
	/**
	 * 获取用户信息
	 * @param session
	 * @return
	 */
	public static TSUserEntity getUserInfo(HttpSession session) {
		Object userInfoObj = session.getAttribute(USER_INFO);
		if (userInfoObj!=null) {
			return (TSUserEntity)userInfoObj;
		}
		return null;
	}
	
	/**
	 * 获取用户id
	 * @param session
	 * @return
	 */
	public static String getUserId(HttpSession session) {
		TSUserEntity userInfo = getUserInfo(session);
		if (userInfo==null) {
			return "";
		} else {
			return userInfo.getUserId();
		}
	}
	
	/**
	 * 保存用户信息
	 * @param session
	 * @param userDetail
	 */
	public static void setUserInfo(HttpSession session, TSUserEntity userExtendEntity) {
		session.setAttribute(USER_INFO, userExtendEntity);
	}

}
