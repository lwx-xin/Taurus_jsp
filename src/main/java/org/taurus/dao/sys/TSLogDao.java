package org.taurus.dao.sys;

import org.apache.ibatis.annotations.Mapper;
import org.taurus.entity.sys.TSLogEntity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface TSLogDao extends BaseMapper<TSLogEntity> {

}
