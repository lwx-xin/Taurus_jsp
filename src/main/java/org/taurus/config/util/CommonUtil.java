package org.taurus.config.util;

import javax.servlet.http.HttpServletRequest;

public class CommonUtil {

	/**
	 * 判断请求是PC 还是手机端
	 * 
	 * @param requestHeader
	 * @return true-->移动端<br/>
	 *         false-->pc
	 */
	public static boolean isMobileDevice(HttpServletRequest request) {
		String requestHeader = request.getHeader("user-agent");
		String[] deviceArray = new String[] { "android", "mac os", "windows phone" };
		if (requestHeader == null) {
			return false;
		}
		requestHeader = requestHeader.toLowerCase();
		for (String device : deviceArray) {
			if (requestHeader.indexOf(device) != -1) {
				return true;
			}
		}
		return false;
	}

}
