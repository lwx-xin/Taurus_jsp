package org.taurus.extend.fileService.service;

import java.io.IOException;

public interface RemoveFileService {
	
	public boolean removeFile(String folderFile, String fileName) throws IOException;
	
	public boolean removeFile(String filePath) throws IOException;

}
