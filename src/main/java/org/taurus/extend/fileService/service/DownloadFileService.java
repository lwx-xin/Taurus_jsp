package org.taurus.extend.fileService.service;

import javax.servlet.http.HttpServletResponse;

public interface DownloadFileService {
	
	public boolean downloadFile(HttpServletResponse response, String folderFile, String fileName) throws Exception;
	
	public boolean downloadFile(HttpServletResponse response, String filePath) throws Exception;
}
