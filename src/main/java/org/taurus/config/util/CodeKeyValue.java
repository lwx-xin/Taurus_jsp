package org.taurus.config.util;

public enum CodeKeyValue {

	/**
	 * 删除状态-禁用
	 */
	DEL_FLG_YES("del_flg","0","禁用"),
	/**
	 * 删除状态-启用
	 */
	DEL_FLG_NO("del_flg","1","启用"),
	
	/**
	 * 日志状态-未解决
	 */
	LOG_STATUS0("log_status","0","未解决"),
	/**
	 * 日志状态-解决中
	 */
	LOG_STATUS1("log_status","1","解决中"),
	/**
	 * 日志状态-已解决
	 */
	LOG_STATUS2("log_status","2","已解决"),
	
	/**
	 * 文件分组-日志
	 */
	FILE_TYPE_LOG("file_type","log","日志"),
	/**
	 * 文件分组-视频
	 */
	FILE_TYPE_VIDEO("file_type","video","视频"),
	/**
	 * 文件分组-文本
	 */
	FILE_TYPE_TXT("file_type","txt","文本"),
	/**
	 * 文件分组-音频
	 */
	FILE_TYPE_AUDIO("file_type","audio","音频"),
	/**
	 * 文件分组-图片
	 */
	FILE_TYPE_PICTURE("file_type","picture","图片"),
	/**
	 * 文件分组-Word
	 */
	FILE_TYPE_WORD("file_type","word","Word"),
	/**
	 * 文件分组-Pdf
	 */
	FILE_TYPE_PDF("file_type","pdf","Pdf"),
	/**
	 * 文件分组-Excel
	 */
	FILE_TYPE_EXCEL("file_type","excel","Excel"),
	/**
	 * 文件分组-压缩包
	 */
	FILE_TYPE_RAR("file_type","rar","压缩包"),
	/**
	 * 文件分组-未知
	 */
	FILE_TYPE_OTHER("file_type","other","未知");
	
	private String group;

	private String value;

	private String name;

	private CodeKeyValue(String group, String value, String name) {
		this.group = group;
		this.value = value;
		this.name = name;
	}
	public String value(){
		return value;
	}
	
	public String group() {
		return group;
	}
	
	public String names() {
		return name;
	}
}
