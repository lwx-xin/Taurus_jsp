package org.taurus.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.taurus.config.util.ClassUtil;
import org.taurus.config.util.CodeKeyValue;
import org.taurus.config.util.ListUtil;
import org.taurus.config.util.StrUtil;
import org.taurus.entity.sys.TAuthUrlEntity;
import org.taurus.entity.sys.TAuthUserEntity;
import org.taurus.entity.sys.TMCodeEntity;
import org.taurus.entity.sys.TMFormatEntity;
import org.taurus.entity.sys.TSAuthEntity;
import org.taurus.entity.sys.TSFolderEntity;
import org.taurus.entity.sys.TSUrlEntity;
import org.taurus.entity.sys.TSUserEntity;
import org.taurus.service.sys.TAuthUrlService;
import org.taurus.service.sys.TAuthUserService;
import org.taurus.service.sys.TMCodeService;
import org.taurus.service.sys.TMFormatService;
import org.taurus.service.sys.TSAuthService;
import org.taurus.service.sys.TSFolderService;
import org.taurus.service.sys.TSUrlService;
import org.taurus.service.sys.TSUserService;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * 初始化表数据
 * 
 * @author 祈
 *
 */
@Order(3) // value越小越先加载
@Configuration
public class InitDBData {

	@Resource
	TaurusConfig config;

	@Resource
	TSUserService userService;

	@Resource
	TSUrlService urlService;

	@Resource
	TSAuthService authService;

	@Resource
	TAuthUserService authUserService;

	@Resource
	TAuthUrlService authUrlService;

	@Resource
	TSFolderService folderService;

	@Resource
	TMCodeService codeService;

	@Resource
	TMFormatService formatService;
	
	@Resource
    JdbcTemplate jdbcTemplate;

	@Bean
	public void initData() {

		// 生成系统管理员账号
		createAdminUser();

		// 生成系统管理员权限
		createAdminAuth();

		// 生成请求表数据(.**)
		createAdminUrl();

		// 生成[权限-用户]数据
		createAuthUser();

		// 生成[权限-请求]数据
		createAuthUrl();

		// 成系统日志文件夹
		createSystemLogFolder();

		// 根据枚举类添加code
		createCodeData();

		// 生成用户账号生成规则
		createFormatUserNumber();
		
		//生成全部的请求
		createAllUrl();
	}

	/**
	 * 生成系统管理员账号
	 */
	private void createAdminUser() {
		// 系统管理员id
		String adminUserId = config.getAdminUserId();
		String adminUserName = config.getAdminUserName();
		String adminUserNumber = config.getAdminUserNumber();
		String adminUserPwd = config.getAdminUserPwd();

		String MD5_pwd = StrUtil.getMD5Pwd(adminUserPwd, adminUserNumber);
		// 判断是否有系统管理员
		if (userService.getById(adminUserId) == null) {
			Date now = new Date();
			TSUserEntity userEntity = new TSUserEntity();
			userEntity.setUserId(adminUserId);
			userEntity.setUserName(adminUserName);
			userEntity.setUserNumber(adminUserNumber);
			userEntity.setUserPwd(MD5_pwd);
			userEntity.setUserHeadPortrait("default.jpg");
			userEntity.setUserDelFlg(CodeKeyValue.DEL_FLG_NO.value());
			userEntity.setUserCreateTime(now);
			userEntity.setUserCreateUserId(adminUserId);
			userEntity.setUserModifyTime(now);
			userEntity.setUserModifyUserId(adminUserId);
			boolean save = userService.save(userEntity);
			
			if (save) {
				TSFolderEntity entity = new TSFolderEntity();
				entity.setFolderId(StrUtil.getUUID());
				entity.setFolderName(adminUserNumber);
				entity.setParentFolderId(null);
				entity.setFolderOwner(adminUserId);
				entity.setFolderDelFlg(CodeKeyValue.DEL_FLG_NO.value());
				entity.setFolderCreateTime(now);
				entity.setFolderCreateUser(adminUserId);
				entity.setFolderModifyTime(now);
				entity.setFolderModifyUser(adminUserId);
				folderService.save(entity);
			}
		}
	}

	/**
	 * 生成系统管理员权限
	 */
	private void createAdminAuth() {
		// 系统管理员id
		String adminUserId = config.getAdminUserId();
		// 管理员权限id
		String adminAuthId = config.getAdminAuthId();
		String adminAuthName = config.getAdminAuthName();
		String adminAuthLevel = config.getAdminAuthLevel();
		// 判断是否有管理员权限
		if (authService.getById(adminAuthId) == null) {
			Date now = new Date();
			TSAuthEntity authEntity = new TSAuthEntity();
			authEntity.setAuthId(adminAuthId);
			authEntity.setAuthName(adminAuthName);
			authEntity.setAuthLevel(adminAuthLevel);
			authEntity.setAuthDelFlg(CodeKeyValue.DEL_FLG_NO.value());
			authEntity.setAuthCreateTime(now);
			authEntity.setAuthCreateUserId(adminUserId);
			authEntity.setAuthModifyTime(now);
			authEntity.setAuthModifyUserId(adminUserId);
			authService.save(authEntity);
		}
	}

	/**
	 * 生成请求表数据(.**)
	 */
	private void createAdminUrl() {
		// 系统管理员id
		String adminUserId = config.getAdminUserId();
		// url_id
		String adminUrlId = config.getAdminUrlId();
		String adminUrlPath = config.getAdminUrlPath();
		String adminUrlMethod = config.getAdminUrlMethod();
		String adminUrlRemarks = config.getAdminUrlRemarks();
		if (urlService.getById(adminUrlId) == null) {
			Date now = new Date();

			TSUrlEntity urlEntity = new TSUrlEntity();
			urlEntity.setUrlId(adminUrlId);
			urlEntity.setUrlPath(adminUrlPath);
			urlEntity.setUrlMethod(adminUrlMethod);
			urlEntity.setUrlRemarks(adminUrlRemarks);
			urlEntity.setUrlDelFlg(CodeKeyValue.DEL_FLG_NO.value());
			urlEntity.setUrlCreateTime(now);
			urlEntity.setUrlCreateUserId(adminUserId);
			urlEntity.setUrlModifyTime(now);
			urlEntity.setUrlModifyUserId(adminUserId);
			urlService.save(urlEntity);
		}
	}

	/**
	 * 生成[权限-用户]数据
	 */
	private void createAuthUser() {
		// 系统管理员id
		String adminUserId = config.getAdminUserId();
		// 系统管理员权限id
		String adminAuthId = config.getAdminAuthId();

		TAuthUserEntity queryEntity = new TAuthUserEntity();
		queryEntity.setAuthId(adminAuthId);
		queryEntity.setUserId(adminUserId);
		Wrapper<TAuthUserEntity> queryWrapper = new QueryWrapper<TAuthUserEntity>(queryEntity);
		if (ListUtil.isEmpty(authUserService.list(queryWrapper))) {
			Date now = new Date();

			TAuthUserEntity entity = new TAuthUserEntity();
			entity.setAuthUserId(StrUtil.getUUID());
			entity.setAuthId(adminAuthId);
			entity.setUserId(adminUserId);
			entity.setAuthUserDelFlg(CodeKeyValue.DEL_FLG_NO.value());
			entity.setAuthUserCreateTime(now);
			entity.setAuthUserCreateUserId(adminUserId);
			entity.setAuthUserModifyTime(now);
			entity.setAuthUserModifyUserId(adminUserId);
			authUserService.save(entity);
		}
	}

	/**
	 * 生成[权限-请求]数据
	 */
	private void createAuthUrl() {
		// 系统管理员id
		String adminUserId = config.getAdminUserId();
		// 系统管理员请求id
		String adminUrlId = config.getAdminUrlId();
		// 系统管理员权限id
		String adminAuthId = config.getAdminAuthId();

		TAuthUrlEntity queryEntity = new TAuthUrlEntity();
		queryEntity.setUrlId(adminUrlId);
		queryEntity.setAuthId(adminAuthId);
		Wrapper<TAuthUrlEntity> queryWrapper = new QueryWrapper<TAuthUrlEntity>(queryEntity);
		if (ListUtil.isEmpty(authUrlService.list(queryWrapper))) {
			Date now = new Date();

			TAuthUrlEntity entity = new TAuthUrlEntity();
			entity.setAuthUrlId(StrUtil.getUUID());
			entity.setUrlId(adminUrlId);
			entity.setAuthId(adminAuthId);
			entity.setAuthUrlDelFlg(CodeKeyValue.DEL_FLG_NO.value());
			entity.setAuthUrlCreateTime(now);
			entity.setAuthUrlCreateUserId(adminUserId);
			entity.setAuthUrlModifyTime(now);
			entity.setAuthUrlModifyUserId(adminUserId);
			authUrlService.save(entity);
		}
	}

	/**
	 * 生成系统日志文件夹
	 */
	private void createSystemLogFolder() {
		// 系统管理员id
		String adminUserId = config.getAdminUserId();
		// 系统日志文件夹id
		String systemLogFolderId = config.getSystemLogFolderId();
		// 系统日志文件夹名称
		String systemLogFolderName = config.getSystemLogFolderName();

		if (folderService.getById(systemLogFolderId) == null) {
			Date now = new Date();

			TSFolderEntity entity = new TSFolderEntity();
			entity.setFolderId(systemLogFolderId);
			entity.setFolderName(systemLogFolderName);
			entity.setParentFolderId(null);
			entity.setFolderOwner(adminUserId);
			entity.setFolderDelFlg(CodeKeyValue.DEL_FLG_NO.value());
			entity.setFolderCreateTime(now);
			entity.setFolderCreateUser(adminUserId);
			entity.setFolderModifyTime(now);
			entity.setFolderModifyUser(adminUserId);
			folderService.save(entity);
		}
	}

	/**
	 * 根据枚举类添加code
	 */
	private void createCodeData() {
		// 系统管理员id
		String adminUserId = config.getAdminUserId();
		
		//删除全部的code，重新添加
		TMCodeEntity queryEntity = new TMCodeEntity();
		queryEntity.setCodeCreateUserId(adminUserId);
		Wrapper<TMCodeEntity> queryWrapper = new QueryWrapper<TMCodeEntity>(queryEntity);
		codeService.remove(queryWrapper);
		
		List<TMCodeEntity> codeList = new ArrayList<TMCodeEntity>();
		EnumSet<CodeKeyValue> codeKeyValues = EnumSet.allOf(CodeKeyValue.class);

		Integer codeOrder = -1;
		String group = "";
		for (CodeKeyValue code : codeKeyValues) {
			group = code.group();
			String value = code.value();
			String names = code.names();
			Date now = new Date();

			if (group.equals(group)) {
				codeOrder++;
			} else {
				codeOrder = 0;
			}

			TMCodeEntity entity = new TMCodeEntity();
			entity.setCodeGroup(group);
			entity.setCodeName(names);
			entity.setCodeValue(value);
			entity.setCodeOrder(codeOrder.toString());
			entity.setCodeDelFlg(CodeKeyValue.DEL_FLG_NO.value());
			entity.setCodeCreateTime(now);
			entity.setCodeCreateUserId(adminUserId);
			entity.setCodeModifyTime(now);
			entity.setCodeModifyUserId(adminUserId);
			codeList.add(entity);
		}
		codeService.saveBatch(codeList);
	}

	/**
	 * 生成用户账号生成规则
	 */
	private void createFormatUserNumber() {
		// 系统管理员id
		String adminUserId = config.getAdminUserId();

		String formatUserNumberGroup = config.getFormatUserNumberGroup();
		String formatUserNumberRegx = config.getFormatUserNumberRegx();
		String formatUserNumberRegxValue = config.getFormatUserNumberRegxValue();

		if (formatService.getById(formatUserNumberGroup) == null) {
			Date now = new Date();

			TMFormatEntity entity = new TMFormatEntity();
			entity.setFormatGroup(formatUserNumberGroup);
			entity.setFormatRegex(formatUserNumberRegx);
			entity.setFormatRegexValue(formatUserNumberRegxValue);
			entity.setFormatDelFlg(CodeKeyValue.DEL_FLG_NO.value());
			entity.setFormatCreateTime(now);
			entity.setFormatCreateUserId(adminUserId);
			entity.setFormatModifyTime(now);
			entity.setFormatModifyUserId(adminUserId);
			formatService.save(entity);
		}
	}
	
	private void createAllUrl() {
		// 系统管理员id
		String adminUserId = config.getAdminUserId();
		
		List<TSUrlEntity> allRequestUrl = ClassUtil.getAllRequestUrl("org.taurus.controller");
		for (TSUrlEntity urlEntity : allRequestUrl) {
			String urlPath = urlEntity.getUrlPath();
			String urlMethod = urlEntity.getUrlMethod();
			
			if (!urlPath.startsWith("/")) {
				urlPath = "/"+urlPath;
			}
			Date now = new Date();
			
			urlEntity.setUrlId(StrUtil.getUUID());
			urlEntity.setUrlPath(urlPath);
			urlEntity.setUrlMethod(urlMethod);
			urlEntity.setUrlRemarks("");
			urlEntity.setUrlDelFlg(CodeKeyValue.DEL_FLG_NO.value());
			urlEntity.setUrlCreateTime(now);
			urlEntity.setUrlCreateUserId(adminUserId);
			urlEntity.setUrlModifyTime(now);
			urlEntity.setUrlModifyUserId(adminUserId);

			TSUrlEntity queryEntity = new TSUrlEntity();
			queryEntity.setUrlPath(urlPath);
			Wrapper<TSUrlEntity> queryWrapper = new QueryWrapper<TSUrlEntity>(queryEntity);
			int count = urlService.count(queryWrapper);
			if (count==0) {
				urlService.save(urlEntity);
			}
		}
	}
	
}
