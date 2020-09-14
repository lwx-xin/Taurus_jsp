package org.taurus.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.taurus.entity.sys.TSUrlEntity;
import org.taurus.entity.sys.TSUrlExtendEntity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface TSUrlDao extends BaseMapper<TSUrlEntity> {
	
	/**
	 * 根据用户id获取该用户能访问的请求
	 * @param userId
	 * @return
	 */
	List<TSUrlExtendEntity> getUrlByUser(@Param("userId") String userId);
	
	/**
	 * 查询请求信息以及关联的权限信息
	 * @param urlExtendEntity
	 * @return
	 */
	List<TSUrlExtendEntity> selectExtendInfo(TSUrlExtendEntity urlExtendEntity);

}
