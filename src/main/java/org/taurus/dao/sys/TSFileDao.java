package org.taurus.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.taurus.entity.sys.TSFileEntity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import io.lettuce.core.dynamic.annotation.Param;

@Mapper
public interface TSFileDao extends BaseMapper<TSFileEntity> {
	
	/**
	 * 根据文件所属者查出全部文件路径
	 * @param fileOwner
	 * @return
	 */
	List<String> getFolderByOwner(@Param("fileOwner")String fileOwner);

}
