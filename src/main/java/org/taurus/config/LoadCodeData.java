package org.taurus.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.taurus.config.util.CodeKeyValue;
import org.taurus.config.util.ListUtil;
import org.taurus.entity.sys.TMCodeEntity;
import org.taurus.service.sys.TMCodeService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * 加载全部的code
 * @author 祈
 *
 */
@Order(4)//value越小越先加载
@Configuration
public class LoadCodeData {
	
	/**
	 * all code
	 */
	public static Map<String, List<TMCodeEntity>> tmCode = new HashMap<String, List<TMCodeEntity>>();
	
	@Resource
	TMCodeService codeService;
	
	/**
	 * 加载全部的code
	 */
	@Bean
	public void loadCode() {
		TMCodeEntity selectParam = new TMCodeEntity();
		selectParam.setCodeDelFlg(CodeKeyValue.DEL_FLG_NO.value());
		
		QueryWrapper<TMCodeEntity> queryWrapper = new QueryWrapper<TMCodeEntity>(selectParam);
		List<TMCodeEntity> codeList = codeService.list(queryWrapper);
		
		if (ListUtil.isNotEmpty(codeList)) {
			for (TMCodeEntity codeEntity : codeList) {
				String codeGroup = codeEntity.getCodeGroup();
				
				List<TMCodeEntity> list = tmCode.get(codeGroup);
				if (ListUtil.isEmpty(list)) {
					list = new ArrayList<>();
				}
				list.add(codeEntity);
				tmCode.put(codeGroup, list);
			}
		}
	}

}
