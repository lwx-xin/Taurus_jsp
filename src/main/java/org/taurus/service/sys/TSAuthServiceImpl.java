package org.taurus.service.sys;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taurus.config.util.SessionUtil;
import org.taurus.config.util.StrUtil;
import org.taurus.dao.sys.TSAuthDao;
import org.taurus.entity.sys.TSAuthEntity;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class TSAuthServiceImpl extends ServiceImpl<TSAuthDao, TSAuthEntity> implements TSAuthService {
	
	@Override
	@Transactional
	public boolean editAuthDetail(TSAuthEntity authEntity,HttpSession session) {
		
		if (authEntity==null) {
			return false;
		}
		
		//登录用户
		String loginUserId = SessionUtil.getUserId(session);
		
		String authId = authEntity.getAuthId();
		if (StrUtil.isEmpty(authId)) {
			//添加权限
			authEntity.setAuthId(StrUtil.getUUID());
			authEntity.setAuthCreateUserId(loginUserId);
			authEntity.setAuthCreateTime(new Date());
			authEntity.setAuthModifyUserId(loginUserId);
			authEntity.setAuthModifyTime(new Date());
		} else {
			//编辑权限
			authEntity.setAuthModifyUserId(loginUserId);
			authEntity.setAuthModifyTime(new Date());
		}
		
		return saveOrUpdate(authEntity);
	}

}
