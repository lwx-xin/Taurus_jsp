package org.taurus.config.util.entity;

import java.util.List;
import java.util.Map;

public class ReturnEntity {
	
	private boolean status;

	private String message;
	
	private List<?> listData;
	
	private Map<String, ?> mapData;
	
	private Object objectData;
	
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<?> getListData() {
		return listData;
	}

	public void setListData(List<?> listData) {
		this.listData = listData;
	}

	public Map<String, ?> getMapData() {
		return mapData;
	}

	public void setMapData(Map<String, ?> mapData) {
		this.mapData = mapData;
	}

	public Object getObjectData() {
		return objectData;
	}

	public void setObjectData(Object objectData) {
		this.objectData = objectData;
	}

}
