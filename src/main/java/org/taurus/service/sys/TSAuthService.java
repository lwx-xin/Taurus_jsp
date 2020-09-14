package org.taurus.service.sys;

import org.taurus.entity.sys.TSAuthEntity;

import com.baomidou.mybatisplus.extension.service.IService;

public interface TSAuthService extends IService<TSAuthEntity> {
	
	boolean editAuthDetail(TSAuthEntity authEntity);

}
