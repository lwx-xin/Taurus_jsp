package org.taurus.dao.sys;

import org.apache.ibatis.annotations.Mapper;
import org.taurus.entity.sys.TAuthUserEntity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface TAuthUserDao extends BaseMapper<TAuthUserEntity> {
	
}
