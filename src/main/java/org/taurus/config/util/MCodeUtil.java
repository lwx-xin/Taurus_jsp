package org.taurus.config.util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.taurus.config.LoadCodeData;
import org.taurus.entity.sys.TMCodeEntity;

public class MCodeUtil {
	
	/**
	 * 获取code的name
	 * @param group
	 * @param codeValue
	 * @return
	 */
	public static String getCodeName(String group, String codeValue) {
		String codeName = "";
		Map<String, List<TMCodeEntity>> sys_mCode = LoadCodeData.tmCode;
		
		List<TMCodeEntity> list = sys_mCode.get(group);
		if (ListUtil.isNotEmpty(list)) {
			for (TMCodeEntity codeEntity : list) {
				String name = codeEntity.getCodeName();
				String value = codeEntity.getCodeValue();
				if (StrUtil.strIsEquals(codeValue, value)) {
					codeName = name;
					break;
				}
			}
		}
		
		return codeName;
	}
	
	/**
	 * 获取当前分组的code列表
	 * @param group
	 * @return
	 */
	public static Map<String, String> getGroupMap(String group) {
		Map<String, List<TMCodeEntity>> sys_mCode = LoadCodeData.tmCode;
		List<TMCodeEntity> list = sys_mCode.get(group);
		
		List<TMCodeEntity> sortList = list.stream().sorted(Comparator.comparing(TMCodeEntity::getCodeOrder)).collect(Collectors.toList());
		
		Map<String, String> map = new HashMap<String, String>();
		if (ListUtil.isNotEmpty(sortList)) {
			for (TMCodeEntity codeEntity : sortList) {
				String delFlg = codeEntity.getCodeDelFlg();
				if (CodeKeyValue.DEL_FLG_YES.value().equals(delFlg)) {
					continue;
				}
				
				String name = codeEntity.getCodeName();
				String value = codeEntity.getCodeValue();
				map.put(value, name);
			}
		}
		return map;
	}

}
