package org.taurus.service.sys;

import org.taurus.entity.sys.TSLogEntity;

import com.baomidou.mybatisplus.extension.service.IService;

public interface TSLogService extends IService<TSLogEntity> {
	
	boolean saveLogInfo(Exception exception, String userId) throws Exception;

}
