package org.taurus.config.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	
	//设置cookie有效路径
	private static final String contextPath = "/";

	//设置有效时间,单位秒
	private static final Integer maxAge = -1;
	
	/**
	 * 往cookie中添加数据
	 * @param cookieName
	 * @param cookieValue
	 * @param response
	 */
	public static void createCookie(String cookieName, String cookieValue, HttpServletResponse response) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(maxAge);
		cookie.setPath(contextPath);
		cookie.setDomain("localhost");
		response.addCookie(cookie);
	}
	
	/**
	 * 移除cookie中的数据
	 * @param cookieName
	 * @param response
	 */
	public static void removeCookie(String cookieName, HttpServletResponse response) {
		Cookie cookie = new Cookie(cookieName,null);//cookie名字要相同
		cookie.setMaxAge(0);
		cookie.setPath(contextPath);
		cookie.setDomain("localhost");
		response.addCookie(cookie);
	}
	
	/**
	 * 获取cookie中的数据
	 * @param cookieName 
	 * @param request
	 * @return
	 */
	public static String getCookie(String cookieName, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (ListUtil.isNotEmpty(cookies)) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					return cookie.getValue();
				}
			}
		}
		return "";
	}

}
