package org.taurus.service.sys;

import javax.servlet.http.HttpSession;

import org.taurus.entity.sys.TSAuthEntity;

import com.baomidou.mybatisplus.extension.service.IService;

public interface TSAuthService extends IService<TSAuthEntity> {
	
	boolean editAuthDetail(TSAuthEntity authEntity,HttpSession session);

}
