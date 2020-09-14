package org.taurus.config.util;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.taurus.config.util.entity.JsonDateValueProcessor;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@SuppressWarnings({ "unchecked", "rawtypes", "deprecation", "static-access" })
public class JsonUtil {
	
	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 实体转json字符串
	 * @param object
	 * @return
	 */
	public static String entityToJson(Object object){
		if (object==null) {
			return "";
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject jsonObject = JSONObject.fromObject(object, jsonConfig);

		return jsonObject.toString();
	}
	
	/**
	 * list转json字符串
	 * @param list
	 * @return
	 */
	public static String listToJson(List<?> list){
		if (ListUtil.isEmpty(list)) {
			return "";
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONArray jsonArrays = JSONArray.fromObject(list, jsonConfig);

		return jsonArrays.toString();
	}
	
	/**
	 * map转json字符串
	 * @param map
	 * @return
	 */
	public static String mapToJson(Map<?, ?> map){
		if (map==null) {
			return "";
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject jsonObject = new JSONObject();
		jsonObject.putAll(map, jsonConfig);

		return jsonObject.toString();
	}
	
	/**
	 * entity转map：entity->json->map
	 * @param object
	 * @return
	 */
	public static Map<String, Object> entityToMap(Object object){
		if (object == null) {
			return null;
		}
		String entityJson = entityToJson(object);
		return JSONObject.fromObject(entityJson);
	}
	
	/**
	* json string 转换为 map 对象
	* @param json
	* @return
	*/
	public static Map<String, Object> jsonToMap(String json) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		return (Map)jsonObject;
	}
	
	/**
	 * json string 转换为 list
	 * @param <T>
	 * @param json
	 * @param c
	 * @return
	 */
	public static <T> T jsonToList(String json, Class c) {
		JSONArray jsonArray = JSONArray.fromObject(json);
		return (T)jsonArray.toList(jsonArray, c);
	}

	/**
	 * json string 转换为 entity
	 * @param json
	 * @param type
	 * @return
	 */
	public static <T> T jsonToEntity(String json, Class c) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		return (T)JSONObject.toBean(jsonObject, c);
	}
	
	/**
	 * 格式化list数据：list->json->list
	 * @param list
	 * @return
	 */
	public static List<?> listToList(List<?> list){
		if (ListUtil.isEmpty(list)) {
			return null;
		}
		String listJson = listToJson(list);
		JSONArray jsonArray = JSONArray.fromObject(listJson);
		return (List<String>) JSONArray.toList(jsonArray);
	}
	
	public static Object jsonToObject(String jsonStr,Class<?> entity){
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		return JSONObject.toBean(jsonObject,entity);
	}

	public static Object formatJson(String json) {
		Object readValue = "";
		try {
			readValue = objectMapper.readValue(json, Object.class);
			if (readValue instanceof List) {
				formatJson_list((List<Object>)readValue);
			} else if (readValue instanceof Map) {
				formatJson_map((Map<String, Object>)readValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return readValue;
	}
	
	public static Object entityToEntity(Object entity,Class c) {
		String entityToJson = entityToJson(entity);
		return jsonToEntity(entityToJson, c);
	}
	
	private static void formatJson_map(Map<String, Object> map) throws Exception {
		if (MapUtils.isNotEmpty(map)) {
			for (String key : map.keySet()) {
				Object value = map.get(key);
				
				if (value instanceof List) {
					
					formatJson_list((List<Object>)value);
				} else if (value instanceof Map) {
					
					formatJson_map((Map<String, Object>)value);
				} else if (value instanceof String) {
					
					value = formatJson_string(value.toString());
					map.put(key, value);
				}
			}
		}
	}
	
	private static void formatJson_list(List<Object> list) throws Exception {
		if (ListUtil.isNotEmpty(list)) {
			for (Object value : list) {
				
				if (value instanceof List) {
					
					formatJson_list((List<Object>)value);
				} else if (value instanceof Map) {
					
					formatJson_map((Map<String, Object>)value);
				} else if (value instanceof String) {
					
					Collections.replaceAll(list, value, formatJson_string(value.toString()));
				}
			}
		}
	}
	
	private static Object formatJson_string(String str) throws Exception {
		
		if (StrUtil.isEmpty(str)) {
			return "";
		}
		
		if (str.startsWith("{") && str.endsWith("}")) {
			Map readValue = objectMapper.readValue(str, Map.class);
			formatJson_map(readValue);
			return readValue;
		} else if (str.startsWith("[") && str.endsWith("]")) {
			List readValue = objectMapper.readValue(str, List.class);
			formatJson_list(readValue);
			return readValue;
		}
		
		return str;
	}
	
}
