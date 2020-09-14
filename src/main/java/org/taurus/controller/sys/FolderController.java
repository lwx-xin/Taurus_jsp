package org.taurus.controller.sys;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.taurus.config.util.StrUtil;
import org.taurus.config.util.entity.ReturnEntity;
import org.taurus.config.util.entity.ReturnEntityBuild;
import org.taurus.entity.sys.TSFolderEntity;
import org.taurus.service.sys.TSFolderService;

@Controller
@RequestMapping("system/folderController")
public class FolderController {

	@Resource
	private HttpServletRequest request;

	@Resource
	private TSFolderService folderService;

	@RequestMapping(value = "/openAddFolder", method = RequestMethod.GET)
	public String openAddFolder(String folderId) throws Exception {
		request.setAttribute("folderId", folderId);
		request.setAttribute("editType", "insert");
		return "sys/editFolder";
	}

	@RequestMapping(value = "/openUpdateFolder", method = RequestMethod.GET)
	public String openUpdateFolder(String folderId) throws Exception {
		request.setAttribute("folderId", folderId);
		request.setAttribute("editType", "update");
		return "sys/editFolder";
	}

	@RequestMapping(value = "/initEditFolder", method = RequestMethod.POST)
	@ResponseBody
	public ReturnEntity initEditFolder(TSFolderEntity folderEntity) {
		String folderId = folderEntity.getFolderId();
		if (StrUtil.isNotEmpty(folderId)) {
			folderEntity = folderService.getById(folderId);
			return new ReturnEntityBuild(true).objectData(folderEntity).build();
		}
		return new ReturnEntityBuild(false).message("").build();
	}

	@RequestMapping(value = "/editFolderDetail", method = RequestMethod.POST)
	@ResponseBody
	public ReturnEntity editFolderDetail(String folderId,String folderName,String editType,HttpSession session) {
		boolean editFolder = folderService.editFolder(folderId, folderName, editType,session);
		if (editFolder) {
			return new ReturnEntityBuild(true).build();
		}
		return new ReturnEntityBuild(false).message("文件夹编辑失败").build();
	}

}
