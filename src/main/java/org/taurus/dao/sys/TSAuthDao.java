package org.taurus.dao.sys;

import org.apache.ibatis.annotations.Mapper;
import org.taurus.entity.sys.TSAuthEntity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface TSAuthDao extends BaseMapper<TSAuthEntity> {

}
