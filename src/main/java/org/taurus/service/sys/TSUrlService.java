package org.taurus.service.sys;

import java.util.List;

import org.taurus.entity.sys.TSUrlEntity;
import org.taurus.entity.sys.TSUrlExtendEntity;

import com.baomidou.mybatisplus.extension.service.IService;

public interface TSUrlService extends IService<TSUrlEntity> {
	
	/**
	 * 查询请求信息以及关联的权限信息
	 * @param urlExtendEntity
	 * @return
	 */
	List<TSUrlExtendEntity> selectExtendInfo(TSUrlExtendEntity urlExtendEntity);
	
	boolean editUrlDetail(TSUrlExtendEntity urlExtendEntity);

}
