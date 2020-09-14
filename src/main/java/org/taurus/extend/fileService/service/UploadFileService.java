package org.taurus.extend.fileService.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.taurus.entity.sys.TSFileEntity;

public interface UploadFileService {
	
	/**
	 * 文件上传
	 * @param file 文件
	 * @param folderFile 上级文件夹
	 * @return
	 * @throws IOException
	 */
	public TSFileEntity uploadFile(MultipartFile file, String fileFolderId) throws IOException;
	
	public List<TSFileEntity> uploadFiles(MultipartFile[] files, String fileFolderId) throws IOException;
	
}
