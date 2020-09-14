package org.taurus.service.sys;

import java.util.List;

import org.taurus.entity.sys.TSFolderEntity;
import org.taurus.entity.sys.TSFolderExtendEntity;

import com.baomidou.mybatisplus.extension.service.IService;

public interface TSFolderService extends IService<TSFolderEntity> {
	
	/**
	 * 查找当前用户的文件夹节点
	 * @param folderOwner
	 * @return
	 */
	List<TSFolderExtendEntity> getChildrenTree(String folderOwner);
	
	/**
	 * 获取当前文件夹的路径(A/B/c)
	 * @param folderId
	 * @return
	 */
	String getNodePath(String folderId);
	
	/**
	 * 编辑文件夹
	 * @param folderEntity
	 * @param editType insert/update
	 * @return
	 */
	boolean editFolder(String folderId,String folderName,String editType);

}
