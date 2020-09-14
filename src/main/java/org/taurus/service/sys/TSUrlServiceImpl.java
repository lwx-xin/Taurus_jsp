package org.taurus.service.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taurus.config.util.CodeKeyValue;
import org.taurus.config.util.ListUtil;
import org.taurus.config.util.SessionUtil;
import org.taurus.config.util.StrUtil;
import org.taurus.dao.sys.TAuthUrlDao;
import org.taurus.dao.sys.TSUrlDao;
import org.taurus.entity.sys.TAuthUrlEntity;
import org.taurus.entity.sys.TSAuthEntity;
import org.taurus.entity.sys.TSUrlEntity;
import org.taurus.entity.sys.TSUrlExtendEntity;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class TSUrlServiceImpl extends ServiceImpl<TSUrlDao, TSUrlEntity> implements TSUrlService {
	
	@Resource
	private TSUrlDao urlDao;
	
	@Resource
	private TAuthUrlDao authUrlDao;
	
	@Resource
	private TAuthUrlService authUrlService;

	@Override
	public List<TSUrlExtendEntity> selectExtendInfo(TSUrlExtendEntity urlExtendEntity) {
		return urlDao.selectExtendInfo(urlExtendEntity);
	}

	@Override
	@Transactional
	public boolean editUrlDetail(TSUrlExtendEntity urlExtendEntity,HttpSession session) {
		if (urlExtendEntity==null) {
			return false;
		}

		//当前登录用户
		String loginUserId = SessionUtil.getUserId(session);
		
		String urlId = urlExtendEntity.getUrlId();
		if (StrUtil.isEmpty(urlId)) {
			//新增请求
			urlId = StrUtil.getUUID();
			urlExtendEntity.setUrlId(urlId);
			urlExtendEntity.setUrlCreateUserId(loginUserId);
			urlExtendEntity.setUrlCreateTime(new Date());
			urlExtendEntity.setUrlModifyUserId(loginUserId);
			urlExtendEntity.setUrlModifyTime(new Date());
		} else {
			//编辑请求
			urlExtendEntity.setUrlModifyUserId(loginUserId);
			urlExtendEntity.setUrlModifyTime(new Date());
		}
		saveOrUpdate(urlExtendEntity);
		
		//删除当前请求的权限关联信息
		TAuthUrlEntity authUrlParam = new TAuthUrlEntity();
		authUrlParam.setUrlId(urlId);
		Wrapper<TAuthUrlEntity> delAuthUserWrapper = new QueryWrapper<TAuthUrlEntity>(authUrlParam);
		authUrlDao.delete(delAuthUserWrapper);
		
		List<TSAuthEntity> authList = urlExtendEntity.getAuthList();
		List<TAuthUrlEntity> authUrlList = new ArrayList<TAuthUrlEntity>();
		if (ListUtil.isNotEmpty(authList)) {
			for (TSAuthEntity tsAuthEntity : authList) {
				String authId = tsAuthEntity.getAuthId();
				
				TAuthUrlEntity authUserEntity = new TAuthUrlEntity();
				authUserEntity.setAuthUrlId(StrUtil.getUUID());
				authUserEntity.setAuthId(authId);
				authUserEntity.setUrlId(urlId);
				authUserEntity.setAuthUrlDelFlg(CodeKeyValue.DEL_FLG_NO.value());
				authUserEntity.setAuthUrlCreateTime(new Date());
				authUserEntity.setAuthUrlCreateUserId(loginUserId);
				authUserEntity.setAuthUrlModifyTime(new Date());
				authUserEntity.setAuthUrlModifyUserId(loginUserId);
				
				authUrlList.add(authUserEntity);
			}
			authUrlService.saveBatch(authUrlList, authUrlList.size());
		}
		
		return true;
	}

}
