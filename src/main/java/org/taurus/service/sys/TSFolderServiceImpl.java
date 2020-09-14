package org.taurus.service.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.taurus.config.TaurusConfig;
import org.taurus.config.util.CodeKeyValue;
import org.taurus.config.util.JsonUtil;
import org.taurus.config.util.ListUtil;
import org.taurus.config.util.SessionUtil;
import org.taurus.config.util.StrUtil;
import org.taurus.dao.sys.TSFolderDao;
import org.taurus.entity.sys.TSFolderEntity;
import org.taurus.entity.sys.TSFolderExtendEntity;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
@Service
public class TSFolderServiceImpl extends ServiceImpl<TSFolderDao, TSFolderEntity> implements TSFolderService {
	
	@Resource
	private TaurusConfig taurusConfig;
	
	@Resource
	private TSUserService userService;
	
	@Resource
	private TSFolderDao folderDao;

	@Override
	public List<TSFolderExtendEntity> getChildrenTree(String folderOwner,HttpSession session) {
		String loginUserId = SessionUtil.getUserId(session);
		//判断是否是管理员
		boolean userIsHaveAdminAuth = userService.userIsHaveAuth(taurusConfig.getAdminAuthId(), loginUserId);
		List<TSFolderEntity> allFolderList = null;
		if(!userIsHaveAdminAuth) {
			TSFolderEntity entity = new TSFolderEntity();
			entity.setFolderOwner(folderOwner);
			Wrapper<TSFolderEntity> queryWrapper = new QueryWrapper<TSFolderEntity>(entity);
			allFolderList = list(queryWrapper);
		} else {
			allFolderList = list();
		}
		
		
		Map<String, List<TSFolderEntity>> allFolderMap = new HashMap<String, List<TSFolderEntity>>();
		if (ListUtil.isNotEmpty(allFolderList)) {
			for (TSFolderEntity tsFolderEntity : allFolderList) {
				//父节点id
				String parentFolderId = StrUtil.formatNull(tsFolderEntity.getParentFolderId());
				//当前父节点下的全部子节点
				List<TSFolderEntity> list = allFolderMap.get(parentFolderId);
				if (ListUtil.isEmpty(list)) {
					list = new ArrayList<TSFolderEntity>();
				}
				list.add(tsFolderEntity);
				allFolderMap.put(parentFolderId, list);
			}
		}
		
		//全部的顶级节点下的子节点
		List<TSFolderExtendEntity> nodeChildren = nodeChildren("", allFolderMap);
		
		return nodeChildren;
	}
	
	private List<TSFolderExtendEntity> nodeChildren(String parentFolderId, Map<String, List<TSFolderEntity>> allFolderMap){
		List<TSFolderExtendEntity> list = new ArrayList<TSFolderExtendEntity>();
		
		List<TSFolderEntity> nodeList = allFolderMap.get(parentFolderId);
		if (ListUtil.isNotEmpty(nodeList)) {
			for (TSFolderEntity tsFolderEntity : nodeList) {
				String folderId = tsFolderEntity.getFolderId();
				List<TSFolderExtendEntity> childrenNodeList = nodeChildren(folderId, allFolderMap);
				TSFolderExtendEntity folderExtendEntity = (TSFolderExtendEntity) JsonUtil.entityToEntity(tsFolderEntity, TSFolderExtendEntity.class);
				folderExtendEntity.setChildrenNode(childrenNodeList);
				list.add(folderExtendEntity);
			}
		}
		return list;
	}

	@Override
	public String getNodePath(String folderId) {
		//当前文件夹的信息
		TSFolderEntity folderEntity = getById(folderId);
		if (folderEntity==null) {
			return null;
		}

		TSFolderEntity entity = new TSFolderEntity();
		entity.setFolderOwner(folderEntity.getFolderOwner());
		Wrapper<TSFolderEntity> queryWrapper = new QueryWrapper<TSFolderEntity>(entity);
		List<TSFolderEntity> allFolderList = list(queryWrapper);
		
		Map<String, TSFolderEntity> nodeMap = new HashMap<String, TSFolderEntity>();
		if (ListUtil.isNotEmpty(allFolderList)) {
			for (TSFolderEntity tsFolderEntity : allFolderList) {
				nodeMap.put(tsFolderEntity.getFolderId(), tsFolderEntity);
			}
		}
		
		String parentFolderId = folderId;
		String folderPath = "";
		while (StrUtil.isNotEmpty(parentFolderId)) {
			TSFolderEntity tsFolderEntity = nodeMap.get(parentFolderId);
			String folderName = tsFolderEntity.getFolderName();
			String parentFolderId2 = tsFolderEntity.getParentFolderId();
			if (StrUtil.isEmpty(folderPath)) {
				folderPath = folderName;
			} else {
				folderPath = String.format("%s%s%s", folderName, "/", folderPath);
			}
			parentFolderId=parentFolderId2;
		}
		
		return folderPath;
	}

	@Override
	public boolean editFolder(String folderId,String folderName,String editType,HttpSession session) {
		
		String loginUserId = SessionUtil.getUserId(session);
		
		if ("update".equals(editType)) {
			TSFolderEntity entity = getById(folderId);
			if (entity!=null) {
				entity.setFolderName(folderName);
				entity.setFolderModifyTime(new Date());
				entity.setFolderModifyUser(loginUserId);
				return updateById(entity);
			}
		} else if ("insert".equals(editType)) {
			TSFolderEntity entity = new TSFolderEntity();
			entity.setFolderId(StrUtil.getUUID());
			entity.setFolderName(folderName);
			entity.setParentFolderId(folderId);
			entity.setFolderOwner(loginUserId);
			entity.setFolderDelFlg(CodeKeyValue.DEL_FLG_NO.value());
			entity.setFolderCreateTime(new Date());
			entity.setFolderCreateUser(loginUserId);
			entity.setFolderModifyTime(new Date());
			entity.setFolderModifyUser(loginUserId);
			return save(entity);
		}
		
		return false;
	}

}
