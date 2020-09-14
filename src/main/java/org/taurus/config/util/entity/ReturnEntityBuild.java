package org.taurus.config.util.entity;

import java.util.List;
import java.util.Map;

public class ReturnEntityBuild {
	
	private ReturnEntity returnEntity = new ReturnEntity();
	
	public ReturnEntityBuild status(boolean status) {
		returnEntity.setStatus(status);
		return this;
	}
	
	public ReturnEntityBuild message(String message) {
		returnEntity.setMessage(message);
		return this;
	}
	
	public ReturnEntityBuild listData(List<?> listData) {
		returnEntity.setListData(listData);
		return this;
	}
	
	public ReturnEntityBuild mapData(Map<String, ?> mapData) {
		returnEntity.setMapData(mapData);
		return this;
	}
	
	public ReturnEntityBuild objectData(Object objectData) {
		returnEntity.setObjectData(objectData);
		return this;
	}
	
	public ReturnEntity build() {
		return returnEntity;
	}

	public ReturnEntityBuild(Boolean status) {
		returnEntity.setStatus(status);
	}

	

	
}
