package org.taurus.entity.sys;

import java.io.Serializable;
import java.util.List;

public class TSUrlExtendEntity extends TSUrlEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 权限列表
	 */
	private List<TSAuthEntity> authList;

	public List<TSAuthEntity> getAuthList() {
		return authList;
	}

	public void setAuthList(List<TSAuthEntity> authList) {
		this.authList = authList;
	}

	@Override
	public String toString() {
		return "TSUrlExtendEntity [authList=" + authList + "]";
	}

}
