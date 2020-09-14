package org.taurus.extend.fileService.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.taurus.config.TaurusConfig;
import org.taurus.extend.fileService.service.DownloadFileService;

@Service
public class DownloadFileServiceImpl implements DownloadFileService {
	
	@Resource
	private TaurusConfig taurusConfig;

	@Override
	public boolean downloadFile(HttpServletResponse response, String folderFile, String fileName) throws Exception {
		
		return downloadFile(response, folderFile + "/" + fileName);
	}

	@Override
	public boolean downloadFile(HttpServletResponse response, String filePath) throws Exception {
		
		String servicePath = taurusConfig.getLocationPath();
		
		File file = new File(servicePath + filePath);
		if (!file.exists()) {
			return false;
		}
		
		String fileName = new String(file.getName().getBytes(),"ISO8859-1");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition",  "attachment;filename="+fileName);
		
		OutputStream outputStream = response.getOutputStream();
		response.setContentLength((int)file.length());
		IOUtils.copy(new FileInputStream(file), outputStream);
		response.flushBuffer();
		return true;
	}

}
