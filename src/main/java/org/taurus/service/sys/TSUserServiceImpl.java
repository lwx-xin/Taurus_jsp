package org.taurus.service.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taurus.config.TaurusConfig;
import org.taurus.config.util.CodeKeyValue;
import org.taurus.config.util.FormatUtil;
import org.taurus.config.util.ListUtil;
import org.taurus.config.util.SessionUtil;
import org.taurus.config.util.StrUtil;
import org.taurus.dao.sys.TAuthUserDao;
import org.taurus.dao.sys.TSUserDao;
import org.taurus.entity.sys.TAuthUserEntity;
import org.taurus.entity.sys.TSAuthEntity;
import org.taurus.entity.sys.TSFolderEntity;
import org.taurus.entity.sys.TSUserEntity;
import org.taurus.entity.sys.TSUserExtendEntity;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class TSUserServiceImpl extends ServiceImpl<TSUserDao, TSUserEntity> implements TSUserService {
	
	@Resource
	private TaurusConfig config;
	
	@Resource
	private TSUserDao userDao;
	
	@Resource
	private TAuthUserDao authUserDao;
	
	@Resource
	private TAuthUserService authUserService;
	
	@Resource
	private TSFolderService folderService;
	
	@Resource
	private FormatUtil formatUtil;

	@Override
	public List<TSUserExtendEntity> selectExtendInfo(String userId) {
		return userDao.selectExtendInfo(userId);
	}

	@Override
	@Transactional
	public boolean editUserDetail(TSUserExtendEntity extendEntity,HttpSession session) {
		if (extendEntity==null) {
			return false;
		}
		
		//当前登录用户
		String loginUserId = SessionUtil.getUserId(session);
		
		String userId = extendEntity.getUserId();
		if (StrUtil.isEmpty(userId)) {
			//新增用户
			userId = StrUtil.getUUID();
			//账号
			String userNumber = formatUtil.getFormatStr(config.getFormatUserNumberGroup());
			//加密前密码
			String userPwd = extendEntity.getUserPwd();
			//加密后密码
			String pwdMD5 = StrUtil.getMD5Pwd(userPwd, userNumber);
			
			extendEntity.setUserId(userId);
			extendEntity.setUserNumber(userNumber);
			extendEntity.setUserPwd(pwdMD5);
			extendEntity.setUserCreateUserId(loginUserId);
			extendEntity.setUserCreateTime(new Date());
			extendEntity.setUserModifyUserId(loginUserId);
			extendEntity.setUserModifyTime(new Date());
			
			//添加文件夹
			TSFolderEntity entity = new TSFolderEntity();
			entity.setFolderId(StrUtil.getUUID());
			entity.setFolderName(userNumber);
			entity.setParentFolderId(null);
			entity.setFolderOwner(userId);
			entity.setFolderDelFlg(CodeKeyValue.DEL_FLG_NO.value());
			entity.setFolderCreateTime(new Date());
			entity.setFolderCreateUser(loginUserId);
			entity.setFolderModifyTime(new Date());
			entity.setFolderModifyUser(loginUserId);
			folderService.save(entity);
		} else {
			//编辑用户
			extendEntity.setUserPwd(null);
			extendEntity.setUserModifyUserId(loginUserId);
			extendEntity.setUserModifyTime(new Date());
		}
		saveOrUpdate(extendEntity);
		
		//删除当前用户的权限关联信息
		TAuthUserEntity authUserParam = new TAuthUserEntity();
		authUserParam.setUserId(userId);
		Wrapper<TAuthUserEntity> delAuthUserWrapper = new QueryWrapper<TAuthUserEntity>(authUserParam);
		authUserDao.delete(delAuthUserWrapper);
		
		List<TSAuthEntity> authList = extendEntity.getAuthList();
		List<TAuthUserEntity> authUserList = new ArrayList<TAuthUserEntity>();
		if (ListUtil.isNotEmpty(authList)) {
			for (TSAuthEntity tsAuthEntity : authList) {
				String authId = tsAuthEntity.getAuthId();
				
				TAuthUserEntity authUserEntity = new TAuthUserEntity();
				authUserEntity.setAuthUserId(StrUtil.getUUID());
				authUserEntity.setAuthId(authId);
				authUserEntity.setUserId(userId);
				authUserEntity.setAuthUserDelFlg(CodeKeyValue.DEL_FLG_NO.value());
				authUserEntity.setAuthUserCreateTime(new Date());
				authUserEntity.setAuthUserCreateUserId(loginUserId);
				authUserEntity.setAuthUserModifyTime(new Date());
				authUserEntity.setAuthUserModifyUserId(loginUserId);
				
				authUserList.add(authUserEntity);
			}
			authUserService.saveBatch(authUserList, authUserList.size());
		}
		
		return true;
	}

	@Override
	public boolean userIsHaveAuth(String authId,String userId) {
		TAuthUserEntity authUserEntity = new TAuthUserEntity();
		authUserEntity.setAuthId(authId);
		authUserEntity.setUserId(userId);
		Wrapper<TAuthUserEntity> queryWrapper = new QueryWrapper<TAuthUserEntity>();
		List<TAuthUserEntity> list = authUserService.list(queryWrapper);
		if (ListUtil.isEmpty(list)) {
			return false;
		}
		return true;
	}

}
