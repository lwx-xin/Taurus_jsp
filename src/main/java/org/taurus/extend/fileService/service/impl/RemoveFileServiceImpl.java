package org.taurus.extend.fileService.service.impl;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.taurus.config.TaurusConfig;
import org.taurus.extend.fileService.service.RemoveFileService;

@Service
public class RemoveFileServiceImpl implements RemoveFileService{
	
	@Resource
	private TaurusConfig taurusConfig;

	@Override
	public boolean removeFile(String folderFile, String fileName) throws IOException {
		
		return removeFile(folderFile + "/" + fileName);
	}

	@Override
	public boolean removeFile(String filePath) throws IOException {
		
		String servicePath = taurusConfig.getLocationPath();
		
		File file = new File(servicePath + filePath);
		if (!file.exists()) {
			return false;
		}
		FileUtils.forceDelete(file);
		file.delete();
		
		return true;
	}

}
