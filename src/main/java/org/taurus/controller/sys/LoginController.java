package org.taurus.controller.sys;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.taurus.config.util.CodeKeyValue;
import org.taurus.config.util.ListUtil;
import org.taurus.config.util.SessionUtil;
import org.taurus.config.util.StrUtil;
import org.taurus.entity.sys.TSUserEntity;
import org.taurus.service.sys.TSUserService;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

@Controller
public class LoginController {
	
	@Resource
	private HttpServletRequest request;
	@Resource
	private HttpSession session;

	@Resource
	private TSUserService userService;

	@RequestMapping(value = "/webLogin", method = RequestMethod.GET)
	public String webLogin(HttpServletResponse response) {
		
		TSUserEntity userInfo = SessionUtil.getUserInfo(session);
		if (userInfo!=null) {
			return "sys/home";
		} else {
			SessionUtil.clearSession(session);
			session.setAttribute("LOGIN_TOKEN", StrUtil.getUUID());
			return "sys/login";
		}
	}

	@RequestMapping(value = "/webLogin", method = RequestMethod.POST)
	public String webLogin(TSUserEntity userEntity,String loginToken, HttpServletResponse response) {
		
		Object LOGIN_TOKEN = session.getAttribute("LOGIN_TOKEN");
		if (StrUtil.strIsNotEquals(LOGIN_TOKEN, loginToken)) {
			request.setAttribute("sysErrMessage", "请重新登录");
			return webLogin(response);
		}
		session.removeAttribute("LOGIN_TOKEN");

		String userNumber = userEntity.getUserNumber();
		String userPwd = userEntity.getUserPwd();

		String pwdMD5 = StrUtil.getMD5Pwd(userPwd, userNumber);
		userEntity.setUserPwd(null);
		
		Wrapper<TSUserEntity> queryWrapper = new QueryWrapper<TSUserEntity>(userEntity);
		List<TSUserEntity> userList = userService.list(queryWrapper);

		if (ListUtil.isEmpty(userList)) {
			request.setAttribute("sysErrMessage", "账号不存在！");
			return "sys/login";
		}

		userEntity = userList.get(0);
		
		if (StrUtil.strIsNotEquals(userEntity.getUserPwd(), pwdMD5)) {
			request.setAttribute("sysErrMessage", "密码错误！");
			return "sys/login";
		}
		
		if (CodeKeyValue.DEL_FLG_YES.value().equals(userEntity.getUserDelFlg())) {
			request.setAttribute("sysErrMessage", "账号被禁用！");
			return "sys/login";
		}
		
		// 将用户信息保存在session中
		SessionUtil.setUserInfo(session, userEntity);

		return "sys/home";
	}

	@RequestMapping(value = "/webLogout", method = RequestMethod.GET)
	public String webLogout(HttpServletResponse response,String sysErrMessage) {

		SessionUtil.clearSession(session);
		request.setAttribute("sysErrMessage", "请重新登录");

		return "sys/login";
	}

	@RequestMapping(value = "/openStart", method = RequestMethod.GET)
	public String openStart() {
		return "sys/start";
	}

}
