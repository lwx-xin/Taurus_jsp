package org.taurus.entity.sys;

import java.util.List;

public class TSFolderExtendEntity extends TSFolderEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 子节点
	 */
	private List<TSFolderExtendEntity> childrenNode;
	
	public List<TSFolderExtendEntity> getChildrenNode() {
		return childrenNode;
	}

	public void setChildrenNode(List<TSFolderExtendEntity> childrenNode) {
		this.childrenNode = childrenNode;
	}

	@Override
	public String toString() {
		return "TSFolderExtendEntity [childrenNode=" + childrenNode + "]";
	}

}
