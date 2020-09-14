package org.taurus.extend.fileService.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.taurus.config.TaurusConfig;
import org.taurus.config.util.CodeKeyValue;
import org.taurus.config.util.FileUtil;
import org.taurus.config.util.SessionUtil;
import org.taurus.config.util.StrUtil;
import org.taurus.entity.sys.TSFileEntity;
import org.taurus.extend.fileService.service.UploadFileService;
import org.taurus.service.sys.TSFileService;
import org.taurus.service.sys.TSFolderService;

@Service
public class UploadFileServiceImpl implements UploadFileService {

	@Resource
	private HttpSession session;

	@Resource
	private TaurusConfig taurusConfig;

	@Resource
	private TSFileService fileService;
	
	@Resource
	private TSFolderService folderServie;

	@Override
	public TSFileEntity uploadFile(MultipartFile file, String fileFolderId) {
		if (file.getSize() <= 0) {
			// 空文件
			return null;
		}

		String loginUserId = SessionUtil.getUserId(session);

		// 文件名+后缀
		String originalFilename = file.getOriginalFilename();
		// 时间戳
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String timestamp = dateFormat.format(new Date());
		// 服务器文件根路径
		String localPath = taurusConfig.getLocationPath();

		// 文件编号
		String fileId = StrUtil.getUUID();
		// 文件后缀
		String suffix = FilenameUtils.getExtension(originalFilename);
		// 文件名-未处理
		String fileNameRealy = FilenameUtils.getBaseName(originalFilename) + "." + suffix;
		// 文件名-处理后 = 文件名-未处理+时间戳
		String fileName = FilenameUtils.getBaseName(originalFilename) + timestamp + "." + suffix;
		// 文件大小KB
		String fileSize = FileUtil.getFileSize(file);
		//文件分组
		String fileType = FileUtil.getFileType(suffix);
		// 文件路径
		String filePath = folderServie.getNodePath(fileFolderId);

		TSFileEntity fileEntity = new TSFileEntity();
		fileEntity.setFileId(fileId);
		fileEntity.setFileType(fileType);
		fileEntity.setFileNameRealy(fileNameRealy);
		fileEntity.setFileName(fileName);
		fileEntity.setFileSize(fileSize);
		fileEntity.setFileFolderId(fileFolderId);
		fileEntity.setFileDelFlg(CodeKeyValue.DEL_FLG_NO.value());
		fileEntity.setFileCreateTime(new Date());
		fileEntity.setFileCreateUser(loginUserId);
		fileEntity.setFileModifyTime(new Date());
		fileEntity.setFileModifyUser(loginUserId);
		boolean save = fileService.save(fileEntity);

		if (save) {
			try {
				// 数据保存成功，开始保存文件
				FileUtil.createDir(localPath + filePath);
				File uploadFile = new File(localPath + filePath + "/" + fileName);
				InputStream inputStream = file.getInputStream();
				FileUtils.copyInputStreamToFile(inputStream, uploadFile);
				inputStream.close();
			} catch (Exception e) {
				System.err.println(e);
				return null;
			}
			return fileEntity;
		} else {
			return null;
		}

	}

	@Override
	public List<TSFileEntity> uploadFiles(MultipartFile[] files, String fileFolderId) throws IOException {
		List<TSFileEntity> fileList = new ArrayList<TSFileEntity>();
		for (MultipartFile f : files) {
			TSFileEntity uploadFile = uploadFile(f, fileFolderId);
			if (uploadFile!=null) {
				fileList.add(uploadFile);
			}
		}
		return fileList;
	}

}
