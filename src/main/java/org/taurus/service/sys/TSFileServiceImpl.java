package org.taurus.service.sys;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.taurus.config.TaurusConfig;
import org.taurus.config.util.SessionUtil;
import org.taurus.config.util.StrUtil;
import org.taurus.dao.sys.TSFileDao;
import org.taurus.entity.sys.TSFileEntity;
import org.taurus.entity.sys.TSFolderEntity;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class TSFileServiceImpl extends ServiceImpl<TSFileDao, TSFileEntity> implements TSFileService {
	
	@Resource
	private TaurusConfig taurusConfig;
	
	@Resource
	private TSFileDao fileDao;
	
	@Resource
	private TSUserService userService;
	
	@Resource
	private TSFolderService folderService;

	@Override
	public String getFilePath(String fileId,HttpSession session) {
		String loginUserId = SessionUtil.getUserId(session);
		
		//当前文件
		TSFileEntity thisFile = getById(fileId);
		if (thisFile==null) {
			return null;
		}
		String fileFolderId = thisFile.getFileFolderId();
		String fileName = thisFile.getFileName();
		
		//当前文件所属文件夹
		TSFolderEntity thisFolder = folderService.getById(fileFolderId);
		if (thisFolder==null) {
			return null;
		}
		String folderId = thisFolder.getFolderId();
		String folderOwner = thisFolder.getFolderOwner();
		
		if (StrUtil.strIsNotEquals(folderOwner, loginUserId)) {
			//判断是否是管理员
			boolean userIsHaveAdminAuth = userService.userIsHaveAuth(taurusConfig.getAdminAuthId(), loginUserId);
			if (!userIsHaveAdminAuth) {
				return null;
			}
		}
		
		String nodePath = folderService.getNodePath(folderId);
		if (StrUtil.isNotEmpty(nodePath)) {
			nodePath=nodePath+"/"+fileName;
		}
		
		return nodePath;
	}

}
