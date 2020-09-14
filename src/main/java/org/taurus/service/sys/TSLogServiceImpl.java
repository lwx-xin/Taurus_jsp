package org.taurus.service.sys;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taurus.config.TaurusConfig;
import org.taurus.config.util.CodeKeyValue;
import org.taurus.config.util.FileUtil;
import org.taurus.config.util.StrUtil;
import org.taurus.dao.sys.TSLogDao;
import org.taurus.entity.sys.TSFileEntity;
import org.taurus.entity.sys.TSLogEntity;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class TSLogServiceImpl extends ServiceImpl<TSLogDao, TSLogEntity> implements TSLogService {
	
	@Resource
	TaurusConfig taurusConfig;
	
	@Resource
	TSFileService fileService;
	
	@Resource
	private TSFolderService folderServie;

	@Override
	@Transactional
	public boolean saveLogInfo(Exception exception, String userId) throws Exception{
		if (exception==null) {
			return false;
		}

		String logId = StrUtil.getUUID();
		String fileId = StrUtil.getUUID();
		String localPath = taurusConfig.getLocationPath();
		String systemLogFolderId = taurusConfig.getSystemLogFolderId();
		
		//记录log
		TSLogEntity logEntity = new TSLogEntity();
		logEntity.setLogId(logId);
		logEntity.setExceptionType(exception.getClass().getName());
		logEntity.setExceptionMessage(exception.getLocalizedMessage());
		logEntity.setExceptionInfo("");
		logEntity.setExceptionStatus(CodeKeyValue.LOG_STATUS0.value());
		logEntity.setLogDelFlg(CodeKeyValue.DEL_FLG_NO.value());
		logEntity.setLogCreateTime(new Date());
		logEntity.setLogCreateUser(userId);
		logEntity.setLogModifyTime(new Date());
		logEntity.setLogModifyUser(userId);
		save(logEntity);
		
		//记录文件
		// 时间戳
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss-SSS");
		String timestamp = dateFormat.format(new Date());
		// 文件类型
		String suffix = "log";
		//文件分组
		String fileType = FileUtil.getFileType(suffix);
		// 文件名-未处理
		String fileNameRealy = timestamp + "." + suffix;
		// 文件名-处理后 = 文件名-未处理+时间戳
		String fileName = timestamp + "." + suffix;
		// 文件大小KB
		String fileSize = "0";
		// 文件路径
		String filePath = folderServie.getNodePath(systemLogFolderId);
		
		TSFileEntity fileEntity = new TSFileEntity();
		fileEntity.setFileId(fileId);
		fileEntity.setFileType(fileType);
		fileEntity.setFileNameRealy(fileNameRealy);
		fileEntity.setFileName(fileName);
		fileEntity.setFileSize(fileSize);
		fileEntity.setFileFolderId(systemLogFolderId);
		fileEntity.setFileDelFlg(CodeKeyValue.DEL_FLG_NO.value());
		fileEntity.setFileCreateTime(new Date());
		fileEntity.setFileCreateUser(userId);
		fileEntity.setFileModifyTime(new Date());
		fileEntity.setFileModifyUser(userId);
		fileService.save(fileEntity);

		//保存文件
		try {
			FileUtil.createDir(localPath + filePath);
			OutputStream out= new FileOutputStream(localPath + filePath + "/" + fileName);
			PrintStream ps = new PrintStream(out);
			exception.printStackTrace(ps);
			out.close();
			ps.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		
		return true;
	}

}
