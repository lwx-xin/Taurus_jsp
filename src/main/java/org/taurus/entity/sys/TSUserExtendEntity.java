package org.taurus.entity.sys;
import java.io.Serializable;
import java.util.List;


/**
 * 
 * 系统用户
 * 
 **/
public class TSUserExtendEntity extends TSUserEntity implements Serializable {

	/**
	 * 
	 */
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
		return "TSUserExtendEntity [authList=" + authList + "]";
	}

}
