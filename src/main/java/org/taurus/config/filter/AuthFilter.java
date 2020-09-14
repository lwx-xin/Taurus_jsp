package org.taurus.config.filter;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taurus.config.util.JsonUtil;
import org.taurus.config.util.ListUtil;
import org.taurus.config.util.SessionUtil;
import org.taurus.config.util.StrUtil;
import org.taurus.dao.sys.TSUrlDao;
import org.taurus.entity.sys.TSUrlExtendEntity;
import org.taurus.entity.sys.TSUserEntity;

public class AuthFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(AuthFilter.class);
	
	private static class AuthResult{
		private boolean status;
		private String sysErrMessage;
		private String redirectUrl;
		
		public boolean getStatus() {
			return status;
		}

		public String getSysErrMessage() {
			return sysErrMessage;
		}

		public String getRedirectUrl() {
			return redirectUrl;
		}

		/**
		 * 
		 * @param status 验证状态
		 * @param sysErrMessage 提示消息
		 * @param redirectUrl 要重定向的请求
		 */
		public AuthResult(boolean status, String sysErrMessage, String redirectUrl) {
			super();
			this.status = status;
			this.sysErrMessage = sysErrMessage;
			this.redirectUrl = redirectUrl;
		}
	}
	
	/**
	 * 权限不足
	 */
	private final String noAuth = "/authErr";
	
	@Resource
	private TSUrlDao urlDao;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("------------------------------------初始化AuthFilter权限过滤器------------------------------------");
		Filter.super.init(filterConfig);
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		// 参数集合
		// Map<String, String[]> parameterMap = request.getParameterMap();

		// 请求路径
		String url = request.getServletPath();
		// 请求方法-post，get
		String method = request.getMethod();
		// 项目名称
//		String contextPath = request.getContextPath();

		AuthResult checkAuth = checkAuth(url, method, request);
		boolean status = checkAuth.getStatus();
		String message = checkAuth.getSysErrMessage();
		String redirectUrl = checkAuth.getRedirectUrl();

		if (status) {
			//验证通过
			filterChain.doFilter(request, response);
		} else {
			// throw new CustomException("权限不足", "001");
//			String redirectUrl = StrUtil.formatNull(checkUrlMap.get("redirectUrl"));
//			String backUrl = StrUtil.formatNull(checkUrlMap.get("backUrl"));
//			request.setAttribute("backUrl", backUrl);
//			response.sendRedirect(contextPath + redirectUrl);
			request.setAttribute("sysErrMessage", message);

			request.getRequestDispatcher(redirectUrl).forward(request, response);
		}
	}

	@Override
	public void destroy() {
		logger.info("------------------------------------销毁AuthFilter权限过滤器------------------------------------");
		Filter.super.destroy();
	}
	
	/**
	 * 验证用户是否有权限访问该请求
	 * @param url
	 * @param method
	 * @return
	 */
	private AuthResult checkAuth(String url, String method, HttpServletRequest request) {
		if (StrUtil.isEmpty(url)) {
			return new AuthResult(false, "", noAuth);
		} else {
			// 公共资源不需要验证
			if (url.startsWith("/js_css/") || url.startsWith("/default") || url.startsWith(noAuth)
					|| url.startsWith("/webLogin") || url.startsWith("/webLogout") 
					|| url.startsWith("/openStart") || url.startsWith("/file")) {
				return new AuthResult(true, "", "");
			}
		}
		
		/* 1.判断用户是否登录 */
		TSUserEntity userInfo = SessionUtil.getUserInfo(request.getSession());
		if (userInfo==null) {
			return new AuthResult(false, "请先登录后再访问", noAuth);
		}

		/* 2.当前用户是否设置权限，如果有权限就把权限对应的请求保存起来 */
		String userId = userInfo.getUserId();
		//当前用户能访问的请求
		List<TSUrlExtendEntity> urlByUser = urlDao.getUrlByUser(userId);
		if (ListUtil.isEmpty(urlByUser)) {
			return new AuthResult(false, "当前用户未设置权限", noAuth);
		}
		
		/* 3.判断请求方式 */
		Integer flg = 0;
		for (TSUrlExtendEntity tsUrlExtendEntity : urlByUser) {
			//请求方法post,get(json list)
			String urlMethod = tsUrlExtendEntity.getUrlMethod();
			//请求路径表达式
			String urlPattern = tsUrlExtendEntity.getUrlPath();
			if (Pattern.matches(urlPattern, url)) {
				if (flg==0) flg=1;
				//请求方式
				List<String> methodList = JsonUtil.jsonToList(urlMethod, String.class);
				if (ListUtil.isEmpty(methodList) || !methodList.contains(method.toLowerCase())) {
					flg=-1;
					break;
				}
			}
		}
		if (flg==-1) {
			return new AuthResult(false, "请求方式错误", noAuth);
		} else if (flg==0) {
			return new AuthResult(false, "暂无权限", noAuth);
		} else if (flg==1) {
			return new AuthResult(true, "", "");
		}
		
		return new AuthResult(true, "", "");
	}

}
