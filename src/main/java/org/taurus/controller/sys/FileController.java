package org.taurus.controller.sys;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
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
import org.taurus.config.TaurusConfig;
import org.taurus.config.util.CommonUtil;
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
	private TSFileService fileService;

	@Resource
	private TSFolderService folderService;

	@Resource
	private UploadFileService uploadFileService;

	@Resource
	private DownloadFileService downloadFileService;

	@Resource
	private TaurusConfig taurusConfig;

	@RequestMapping(value = "/openFileList", method = RequestMethod.GET)
	public String openFileList() throws IOException {
		return "sys/fileList";
	}

	@RequestMapping(value = "/initFileList", method = RequestMethod.POST)
	@ResponseBody
	public ReturnEntity initFileList(TSFileExtendEntity fileExtendEntity) {
		// 文件列表
		QueryWrapper<TSFileEntity> queryWrapper = new QueryWrapper<TSFileEntity>(fileExtendEntity);
		queryWrapper.orderByDesc("file_create_time");
		List<TSFileEntity> fileInfoList = fileService.list(queryWrapper);
		return new ReturnEntityBuild(true).listData(fileInfoList).build();
	}

	@RequestMapping(value = "/initFolderList", method = RequestMethod.POST)
	@ResponseBody
	public ReturnEntity initFolderList(TSFileExtendEntity fileExtendEntity, HttpSession session) {
		String loginUserId = SessionUtil.getUserId(session);
		// 菜单列表
		List<TSFolderExtendEntity> childrenTree = folderService.getChildrenTree(loginUserId, session);
		return new ReturnEntityBuild(true).listData(childrenTree).build();
	}

	@RequestMapping(value = "/openUploadFile", method = RequestMethod.GET)
	public String openUploadFile(@RequestParam("folderId") String folderId) throws IOException {
		request.setAttribute("folderId", folderId);
		return "sys/uploadFile";
	}

	@RequestMapping(value = "/getFilePath", method = RequestMethod.POST)
	@ResponseBody
	public ReturnEntity getFilePath(TSFileExtendEntity fileExtendEntity, HttpSession session) throws IOException {
		String fileId = fileExtendEntity.getFileId();
		if (StrUtil.isNotEmpty(fileId)) {
			String filePath = fileService.getFilePath(fileId, session);
			if (StrUtil.isNotEmpty(filePath)) {
				return new ReturnEntityBuild(true).objectData(filePath).build();
			}
		}
		return new ReturnEntityBuild(false).message("未知的文件").build();
	}

	@RequestMapping(value = "/openShowVideo", method = RequestMethod.GET)
	public String openShowVideo(String videoId, HttpServletRequest request) throws IOException {
		request.setAttribute("videoId", videoId);
		if (CommonUtil.isMobileDevice(request)) {
			// 移动端
			return "sys/showVideo_mobile";
		} else {
			// pc端
			return "sys/showVideo_pc";
		}
	}

	@RequestMapping(value = "/openShowAudio", method = RequestMethod.GET)
	public String openShowAudio(String audioPath) throws IOException {
		request.setAttribute("audioPath", audioPath);
		return "sys/showAudio";
	}

	@RequestMapping(value = "/addFile", method = RequestMethod.GET)
	public String addfile() {
		return "sys/addFile";
	}

	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	@ResponseBody
	public ReturnEntity fileUpload(@RequestParam("file[]") MultipartFile[] file, String folderId, HttpSession session)
			throws IOException {
		List<TSFileEntity> uploadFiles = uploadFileService.uploadFiles(file, folderId, session);
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
	public void downloadFile(String filePath, HttpServletResponse response) throws Exception {
		downloadFileService.downloadFile(response, filePath);
	}

	/**
	 * 获取视频流
	 * 
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("resource")
	@RequestMapping(value = "getVideo", method = RequestMethod.GET)
	public void getVideo(HttpServletRequest request, HttpServletResponse response, HttpSession session, String fileId) {

		// 获取文件路径
		String filePath = fileService.getFilePath(fileId, session);
		if (StrUtil.isEmpty(filePath)) {
			return;
		}
		filePath = taurusConfig.getLocationPath() + filePath;

		response.reset();
		// 获取从那个字节开始读取文件
		String rangeString = request.getHeader("Range");
		if (rangeString == null) {
			return;
		}

		try {
			// 获取响应的输出流
			OutputStream outputStream = response.getOutputStream();
			File file = new File(filePath);
			if (file.exists()) {
				RandomAccessFile targetFile = new RandomAccessFile(file, "r");
				long fileLength = targetFile.length();
				// 播放
				if (rangeString != null) {
					long range = Long
							.valueOf(rangeString.substring(rangeString.indexOf("=") + 1, rangeString.indexOf("-")));
					// 设置内容类型
					response.setHeader("Content-Type", "video/mp4");
					// 设置此次相应返回的数据长度
					response.setHeader("Content-Length", String.valueOf(fileLength - range));
					// 设置此次相应返回的数据范围
					response.setHeader("Content-Range", "bytes " + range + "-" + (fileLength - 1) + "/" + fileLength);
					// 返回码需要为206，而不是200
					response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
					// 设定文件读取开始位置（以字节为单位）
					targetFile.seek(range);
				}
//				else {// 下载
//					System.err.println("下载");
//					// 设置响应头，把文件名字设置好
//					response.setHeader("Content-Disposition", "attachment; filename=" + "test.mp4");
//					// 设置文件长度
//					response.setHeader("Content-Length", String.valueOf(fileLength));
//					// 解决编码问题
//					response.setHeader("Content-Type", "application/octet-stream");
//				}

				byte[] cache = new byte[1024 * 300];
				int flag;
				while ((flag = targetFile.read(cache)) != -1) {
					outputStream.write(cache, 0, flag);
				}
			} else {
				String message = "file:" + "test.mp4" + " not exists";
				// 解决编码问题
				response.setHeader("Content-Type", "application/json");
				outputStream.write(message.getBytes(StandardCharsets.UTF_8));
			}

			outputStream.flush();
			outputStream.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
