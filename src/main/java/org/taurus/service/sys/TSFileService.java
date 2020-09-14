package org.taurus.service.sys;

import org.taurus.entity.sys.TSFileEntity;

import com.baomidou.mybatisplus.extension.service.IService;

public interface TSFileService extends IService<TSFileEntity> {
	
	/**
	 * 获取文件路径
	 * @param fileId
	 * @return
	 */
	String getFilePath(String fileId);
	
}
