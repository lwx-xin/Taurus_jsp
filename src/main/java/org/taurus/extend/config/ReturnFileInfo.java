package org.taurus.extend.config;

public class ReturnFileInfo {
	
	/**
	 * 文件名(无后缀)
	 */
	private String baseName;
	
	/**
	 * 文件后缀
	 */
	private String extension;
	
	/**
	 * 分类目录
	 */
	private String folderFile;
	
	/**
	 * 文件路径
	 */
	private String filePath;
	
	/**
	 * 文件完整路径
	 */
	private String fileServicePath;

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileServicePath() {
		return fileServicePath;
	}

	public void setFileServicePath(String fileServicePath) {
		this.fileServicePath = fileServicePath;
	}

	public String getFolderFile() {
		return folderFile;
	}

	public void setFolderFile(String folderFile) {
		this.folderFile = folderFile;
	}

	@Override
	public String toString() {
		return "FileInfo [baseName=" + baseName + ", extension=" + extension + ", folderFile=" + folderFile
				+ ", filePath=" + filePath + ", fileServicePath=" + fileServicePath + "]";
	}


}
