package org.taurus.controller.err;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.taurus.config.util.StrUtil;

@Controller
public class ErrController {


	/**
	 * 无权限返回页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/authErr", method = RequestMethod.GET)
	public String authErr() {
		return "err/authErr";
	}
	@RequestMapping(value = "/authErr", method = RequestMethod.POST)
	public void authErr(HttpServletRequest request, HttpServletResponse response) {
		String sysErrMessage = StrUtil.formatNull(request.getAttribute("sysErrMessage"));
		try {
			sysErrMessage = URLEncoder.encode(sysErrMessage, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setHeader("sysErrMessage", sysErrMessage);
		response.setHeader("redirect-url", "/authErr");
	}

	/**
	 * 未知的请求返回页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pathNotFound", method = RequestMethod.GET)
	public String pathNotFound() {
		return "err/404";
	}
	@RequestMapping(value = "/pathNotFound", method = RequestMethod.POST)
	public void pathNotFound(HttpServletRequest request, HttpServletResponse response) {
		String sysErrMessage = StrUtil.formatNull(request.getAttribute("sysErrMessage"));
		try {
			sysErrMessage = URLEncoder.encode(sysErrMessage, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setHeader("sysErrMessage", sysErrMessage);
		response.setHeader("redirect-url", "/pathNotFound");
	}

	/**
	 * 未登录返回的页面
	 * @return
	 */
	@RequestMapping(value = "/nologin", method = RequestMethod.GET)
	public String nologin() {
		return "sys/login";
	}
	@RequestMapping(value = "/nologin", method = RequestMethod.POST)
	public void nologin(HttpServletRequest request, HttpServletResponse response) {
		String sysErrMessage = StrUtil.formatNull(request.getAttribute("sysErrMessage"));
		try {
			sysErrMessage = URLEncoder.encode(sysErrMessage, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setHeader("sysErrMessage", sysErrMessage);
		response.setHeader("redirect-url", "/nologin");
	}
}
