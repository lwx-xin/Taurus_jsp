package org.taurus.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.taurus.entity.sys.TSUserEntity;
import org.taurus.entity.sys.TSUserExtendEntity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface TSUserDao extends BaseMapper<TSUserEntity> {
	
	/**
	 * 查询用户信息以及关联的权限信息
	 * @param userId 为空时查询全部
	 * @return
	 */
	List<TSUserExtendEntity> selectExtendInfo(@Param("userId")String userId);

}
