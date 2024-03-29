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
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taurus.config.util.JsonUtil;
import org.taurus.config.util.ListUtil;
import org.taurus.config.util.SessionUtil;
import org.taurus.config.util.StrUtil;
import org.taurus.dao.sys.TSUrlDao;
import org.taurus.entity.sys.TSFileEntity;
import org.taurus.entity.sys.TSFolderEntity;
import org.taurus.entity.sys.TSUrlExtendEntity;
import org.taurus.entity.sys.TSUserEntity;
import org.taurus.service.sys.TSFileService;
import org.taurus.service.sys.TSFolderService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

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
	
	/**
	 * 未登录
	 */
	private final String nologin = "/nologin";
	
	@Resource
	private TSUrlDao urlDao;

	@Resource
	private TSFileService fileService;
	
	@Resource
	private TSFolderService folderService;

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
					|| url.startsWith("/openStart")) {
				return new AuthResult(true, "", "");
			}
		}
		
		/* 1.判断用户是否登录 */
		TSUserEntity userInfo = SessionUtil.getUserInfo(request.getSession());
		if (userInfo==null) {
			HttpSession session = request.getSession();
			Object session_msg = session.getAttribute("sysErrMessage");
			if (session_msg!=null) {
				session.removeAttribute("sysErrMessage");
				return new AuthResult(false, StrUtil.formatNull(session_msg), nologin);
			}
			return new AuthResult(false, "请先登录后再访问", nologin);
		}
		
		//文件请求需要判断--只有文件所属者才可以操作
		if (url.startsWith("/file/")) {
			return checkFileOwner(url, userInfo);
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
				if (ListUtil.isEmpty(methodList) || !methodList.contains(method.toUpperCase())) {
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
	
	/**
	 * 判断文件的所属者
	 * @param url
	 * @param userEntity
	 * @return
	 */
	private AuthResult checkFileOwner(String url, TSUserEntity userEntity) {
		
		//获取文件名(修改后)
		String fileName = url.substring(url.lastIndexOf("/")+1);
		
		TSFileEntity entity = new TSFileEntity();
		entity.setFileName(fileName);
		QueryWrapper<TSFileEntity> queryWrapper = new QueryWrapper<TSFileEntity>(entity);
		List<TSFileEntity> fileList = fileService.list(queryWrapper);
		
		if (ListUtil.isNotEmpty(fileList)) {
			TSFileEntity file = fileList.get(0);
			String fileFolderId = file.getFileFolderId();
			if (StrUtil.isNotEmpty(fileFolderId)) {
				TSFolderEntity folder = folderService.getById(fileFolderId);
				String folderOwner = folder.getFolderOwner();
				if (StrUtil.strIsEquals(folderOwner, userEntity.getUserId())) {
					return new AuthResult(true, "", "");
				}
			}
		}
		
		return new AuthResult(false, "您没有权限操作此文件", noAuth);
	}

}
