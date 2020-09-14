package org.taurus.config.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.taurus.entity.sys.TMFormatEntity;
import org.taurus.service.sys.TMFormatService;

@Component
public class FormatUtil {
	
	@Resource
	private TMFormatService formatService;
	
	/**
	 * 根据指定格式获取字符串
	 * @param group
	 * @return
	 */
	public String getFormatStr(String group) {
		String formatStr = "";
		
		TMFormatEntity entity = formatService.getById(group);
		
		if (entity!=null) {
			
			String regex = entity.getFormatRegex();
			List<Object> regexValue = JsonUtil.jsonToList(entity.getFormatRegexValue(), Object.class);
			
			List<Object> param = new ArrayList<Object>();
			for (Object value : regexValue) {
				if (value instanceof Integer) {
					int valueInt = Integer.parseInt(value.toString());
					valueInt++;
					param.add(valueInt);
				} else {
					param.add(value);
				}
			}
			
			formatStr = String.format(regex, param.toArray());
			
			String formatRegexValue = JsonUtil.listToJson(param);
			entity.setFormatRegexValue(formatRegexValue);
			formatService.updateById(entity);
		}
		
		return formatStr;
	}

}
