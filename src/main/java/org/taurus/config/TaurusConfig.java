package org.taurus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 读取taurus.properties配置文件
 * @author 祈
 *
 */
@Order(1)//value越小越先加载
@PropertySource(value = {"classpath:taurus.properties"}, encoding = "utf-8")
@Configuration
public class TaurusConfig implements WebMvcConfigurer {
	
	/**
	 * 文件路径
	 */
	@Value("${location.path}")
	private String locationPath;

	/**
	 * 文件路径对应的虚拟路径
	 */
	@Value("${virtual.path}")
	private String virtualPath;

	/**
	 * log文件夹id
	 */
	@Value("${system.log.folder.id}")
	private String systemLogFolderId;

	/**
	 * log文文件夹名称
	 */
	@Value("${system.log.folder.name}")
	private String systemLogFolderName;

	/**
	 * 系统管理员id
	 */
	@Value("${admin.user.id}")
	private String adminUserId;
	@Value("${admin.user.name}")
	private String adminUserName;
	@Value("${admin.user.number}")
	private String adminUserNumber;
	@Value("${admin.user.pwd}")
	private String adminUserPwd;

	/**
	 * 系统管理员权限id
	 */
	@Value("${admin.auth.id}")
	private String adminAuthId;
	@Value("${admin.auth.name}")
	private String adminAuthName;
	@Value("${admin.auth.level}")
	private String adminAuthLevel;

	/**
	 * 系统管理员请求
	 */
	@Value("${admin.url.id}")
	private String adminUrlId;
	@Value("${admin.url.path}")
	private String adminUrlPath;
	@Value("${admin.url.method}")
	private String adminUrlMethod;
	@Value("${admin.url.remarks}")
	private String adminUrlRemarks;

	/**
	 * user-number-format
	 */
	@Value("${format.userNumber.group}")
	private String formatUserNumberGroup;
	@Value("${format.userNumber.regex}")
	private String formatUserNumberRegx;
	@Value("${format.userNumber.regexValue}")
	private String formatUserNumberRegxValue;

	public String getLocationPath() {
		return locationPath;
	}

	public void setLocationPath(String locationPath) {
		this.locationPath = locationPath;
	}

	public String getVirtualPath() {
		return virtualPath;
	}

	public void setVirtualPath(String virtualPath) {
		this.virtualPath = virtualPath;
	}

	public String getSystemLogFolderId() {
		return systemLogFolderId;
	}

	public void setSystemLogFolderId(String systemLogFolderId) {
		this.systemLogFolderId = systemLogFolderId;
	}

	public String getSystemLogFolderName() {
		return systemLogFolderName;
	}

	public void setSystemLogFolderName(String systemLogFolderName) {
		this.systemLogFolderName = systemLogFolderName;
	}

	public String getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(String adminUserId) {
		this.adminUserId = adminUserId;
	}

	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public String getAdminUserNumber() {
		return adminUserNumber;
	}

	public void setAdminUserNumber(String adminUserNumber) {
		this.adminUserNumber = adminUserNumber;
	}

	public String getAdminUserPwd() {
		return adminUserPwd;
	}

	public void setAdminUserPwd(String adminUserPwd) {
		this.adminUserPwd = adminUserPwd;
	}

	public String getAdminAuthId() {
		return adminAuthId;
	}

	public void setAdminAuthId(String adminAuthId) {
		this.adminAuthId = adminAuthId;
	}

	public String getAdminAuthName() {
		return adminAuthName;
	}

	public void setAdminAuthName(String adminAuthName) {
		this.adminAuthName = adminAuthName;
	}

	public String getAdminAuthLevel() {
		return adminAuthLevel;
	}

	public void setAdminAuthLevel(String adminAuthLevel) {
		this.adminAuthLevel = adminAuthLevel;
	}

	public String getAdminUrlId() {
		return adminUrlId;
	}

	public void setAdminUrlId(String adminUrlId) {
		this.adminUrlId = adminUrlId;
	}

	public String getAdminUrlPath() {
		return adminUrlPath;
	}

	public void setAdminUrlPath(String adminUrlPath) {
		this.adminUrlPath = adminUrlPath;
	}

	public String getAdminUrlMethod() {
		return adminUrlMethod;
	}

	public void setAdminUrlMethod(String adminUrlMethod) {
		this.adminUrlMethod = adminUrlMethod;
	}

	public String getAdminUrlRemarks() {
		return adminUrlRemarks;
	}

	public void setAdminUrlRemarks(String adminUrlRemarks) {
		this.adminUrlRemarks = adminUrlRemarks;
	}

	public String getFormatUserNumberGroup() {
		return formatUserNumberGroup;
	}

	public void setFormatUserNumberGroup(String formatUserNumberGroup) {
		this.formatUserNumberGroup = formatUserNumberGroup;
	}

	public String getFormatUserNumberRegx() {
		return formatUserNumberRegx;
	}

	public void setFormatUserNumberRegx(String formatUserNumberRegx) {
		this.formatUserNumberRegx = formatUserNumberRegx;
	}

	public String getFormatUserNumberRegxValue() {
		return formatUserNumberRegxValue;
	}

	public void setFormatUserNumberRegxValue(String formatUserNumberRegxValue) {
		this.formatUserNumberRegxValue = formatUserNumberRegxValue;
	}

	/**
     * @Description:
     * 对文件的路径进行配置, 创建一个虚拟路径/Path/**
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler(virtualPath)
                .addResourceLocations("file:"+locationPath);
    }

}
