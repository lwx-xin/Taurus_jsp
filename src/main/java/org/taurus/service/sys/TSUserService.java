package org.taurus.service.sys;

import java.util.List;

import org.taurus.entity.sys.TSUserEntity;
import org.taurus.entity.sys.TSUserExtendEntity;

import com.baomidou.mybatisplus.extension.service.IService;

public interface TSUserService extends IService<TSUserEntity> {
	
	/**
	 * 查询用户信息以及关联的权限信息
	 * @param userId 为空时查询全部
	 * @return
	 */
	List<TSUserExtendEntity> selectExtendInfo(String userId);
	
	boolean	editUserDetail(TSUserExtendEntity extendEntity);
	
	/**
	 * 用户是否拥有某个权限
	 * @param authId
	 * @param userId
	 * @return
	 */
	boolean userIsHaveAuth(String authId,String userId);

}
