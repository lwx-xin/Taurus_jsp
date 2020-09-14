package org.taurus.controller.err;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	/**
	 * 未知的请求返回页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pathNotFound", method = RequestMethod.GET)
	public String pathNotFound() {
		return "err/404";
	}
}
