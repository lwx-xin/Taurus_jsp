package org.taurus.controller.sys;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.taurus.config.util.ListUtil;
import org.taurus.config.util.SessionUtil;
import org.taurus.config.util.StrUtil;
import org.taurus.config.util.entity.ReturnEntity;
import org.taurus.config.util.entity.ReturnEntityBuild;
import org.taurus.entity.sys.TSFileEntity;
import org.taurus.entity.sys.TSFileExtendEntity;
import org.taurus.entity.sys.TSFolderExtendEntity;
import org.taurus.extend.fileService.service.DownloadFileService;
import org.taurus.extend.fileService.service.UploadFileService;
import org.taurus.service.sys.TSFileService;
import org.taurus.service.sys.TSFolderService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

@Controller
@RequestMapping("system/fileController")
public class FileController {
	
	@Resource
	private HttpServletRequest request;
	
	@Resource
	private HttpSession session;
	
	@Resource
	private TSFileService fileService;
	
	@Resource
	private TSFolderService folderService;
	
	@Resource
	private UploadFileService uploadFileService;
	
	@Resource
	private DownloadFileService downloadFileService;
	
	@RequestMapping(value = "/openFileList", method = RequestMethod.GET)
	public String openFileList() throws IOException {
		return "sys/fileList";
	}

	@RequestMapping(value = "/initFileList", method = RequestMethod.POST)
	@ResponseBody
	public ReturnEntity initFileList(TSFileExtendEntity fileExtendEntity){
		//文件列表
		QueryWrapper<TSFileEntity> queryWrapper = new QueryWrapper<TSFileEntity>(fileExtendEntity);
		queryWrapper.orderByDesc("file_create_time");
		List<TSFileEntity> fileInfoList = fileService.list(queryWrapper);
		return new ReturnEntityBuild(true).listData(fileInfoList).build();
	}

	@RequestMapping(value = "/initFolderList", method = RequestMethod.POST)
	@ResponseBody
	public ReturnEntity initFolderList(TSFileExtendEntity fileExtendEntity){
		String loginUserId = SessionUtil.getUserId(session);
		//菜单列表
		List<TSFolderExtendEntity> childrenTree = folderService.getChildrenTree(loginUserId);
		return new ReturnEntityBuild(true).listData(childrenTree).build();
	}
	
	@RequestMapping(value = "/openUploadFile", method = RequestMethod.GET)
	public String openUploadFile(@RequestParam("folderId")String folderId) throws IOException {
		request.setAttribute("folderId", folderId);
		return "sys/uploadFile";
	}
	
	@RequestMapping(value = "/getFilePath", method = RequestMethod.POST)
	@ResponseBody
	public ReturnEntity getFilePath(TSFileExtendEntity fileExtendEntity) throws IOException {
		String fileId = fileExtendEntity.getFileId();
		if (StrUtil.isNotEmpty(fileId)) {
			String filePath = fileService.getFilePath(fileId);
			if (StrUtil.isNotEmpty(filePath)) {
				return new ReturnEntityBuild(true).objectData(filePath).build();
			}
		}
		return new ReturnEntityBuild(false).message("未知的文件").build();
	}
	
	
	
	@RequestMapping(value = "/openShowVideo", method = RequestMethod.GET)
	public String openShowVideo(String videoPath) throws IOException {
		request.setAttribute("videoPath", videoPath);
		return "sys/showVideo";
	}
	
	@RequestMapping(value = "/openShowAudio", method = RequestMethod.GET)
	public String openShowAudio(String audioPath) throws IOException {
		request.setAttribute("audioPath", audioPath);
		return "sys/showAudio";
	}
	
	@RequestMapping(value = "/addFile", method = RequestMethod.GET)
	public String addfile(){
		return "sys/addFile";
	}
	
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	@ResponseBody
	public ReturnEntity fileUpload(@RequestParam("file[]") MultipartFile[] file, String folderId) throws IOException {
		List<TSFileEntity> uploadFiles = uploadFileService.uploadFiles(file, folderId);
		if (ListUtil.isEmpty(uploadFiles)) {
			return new ReturnEntityBuild(false).message("文件上传失败").build();
		}
		return new ReturnEntityBuild(true).listData(uploadFiles).build();
	}

	@RequestMapping(value = "/openPdf", method = RequestMethod.GET)
	public String openPdf(String pdfPath, String pdfName) {
		request.setAttribute("pdfPath", pdfPath);
		request.setAttribute("pdfName", pdfName);
		return "sys/openPdf";
	}

	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	public void downloadFile(String filePath,HttpServletResponse response) throws Exception {
		downloadFileService.downloadFile(response, filePath);
	}
	
}
