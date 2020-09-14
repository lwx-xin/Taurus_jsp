package org.taurus.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.taurus.entity.sys.TAuthUrlEntity;
import org.taurus.entity.sys.TAuthUrlExtendEntity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface TAuthUrlDao extends BaseMapper<TAuthUrlEntity> {
	
	List<TAuthUrlExtendEntity> selectAll();

}
