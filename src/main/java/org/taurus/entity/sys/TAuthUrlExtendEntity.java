package org.taurus.entity.sys;

public class TAuthUrlExtendEntity extends TAuthUrlEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 系统权限
	 */
	private TSAuthEntity authEntity;

	/**
	 * 系统请求
	 */
	private TSUrlEntity urlEntity;

	public TSAuthEntity getAuthEntity() {
		return authEntity;
	}

	public void setAuthEntity(TSAuthEntity authEntity) {
		this.authEntity = authEntity;
	}

	public TSUrlEntity getUrlEntity() {
		return urlEntity;
	}

	public void setUrlEntity(TSUrlEntity urlEntity) {
		this.urlEntity = urlEntity;
	}

	@Override
	public String toString() {
		return "TAuthUrlExtendEntity [authEntity=" + authEntity + ", urlEntity=" + urlEntity + "]";
	}

}
