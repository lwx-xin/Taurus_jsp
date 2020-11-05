package org.taurus.controller.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.taurus.config.util.ListUtil;
import org.taurus.config.util.StrUtil;
import org.taurus.config.util.entity.ReturnEntity;
import org.taurus.config.util.entity.ReturnEntityBuild;
import org.taurus.entity.sys.TAuthUrlEntity;
import org.taurus.entity.sys.TAuthUserEntity;
import org.taurus.entity.sys.TSAuthEntity;
import org.taurus.service.sys.TAuthUrlService;
import org.taurus.service.sys.TAuthUserService;
import org.taurus.service.sys.TSAuthService;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

@Controller
@RequestMapping("system/authController")
public class AuthController {
	
	@Resource
	TSAuthService authService;
	
	@Resource
	TAuthUrlService authUrlService;
	
	@Resource
	TAuthUserService authUserService;
	
	@RequestMapping(value = "/openAuthList", method = RequestMethod.GET)
	public String openAuthList(HttpServletRequest request) {
		
		return "sys/authList";
	}

	@RequestMapping(value = "/initAuthList", method = RequestMethod.POST)
	@ResponseBody
	public ReturnEntity initAuthList(TSAuthEntity authEntity){
		Wrapper<TSAuthEntity> queryWrapper = new QueryWrapper<TSAuthEntity>(authEntity);
		List<TSAuthEntity> allAuth = authService.list(queryWrapper);
		return new ReturnEntityBuild(true).listData(allAuth).build();
	}
	
	@RequestMapping(value = "/openEditAuth", method = RequestMethod.GET)
	public String openEditAuth(String authId, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(authId)) {
			request.setAttribute("authId", authId);
		}
		
		return "sys/editAuth";
	}
	
	@RequestMapping(value = "/initEditAuth", method = RequestMethod.POST)
	@ResponseBody
	public ReturnEntity initEditAuth(TSAuthEntity authorityEntity) {
		if (authorityEntity!=null && StrUtil.isNotEmpty(authorityEntity.getAuthId())) {
			authorityEntity = authService.getById(authorityEntity.getAuthId());
			return new ReturnEntityBuild(true).objectData(authorityEntity).build();
		}
		return new ReturnEntityBuild(true).build();
	}

	@RequestMapping(value = "/editUrlDetailCheck", method = RequestMethod.POST)
	@ResponseBody
	public ReturnEntity editUrlDetailCheck(String authId) {
		
		//使用了该权限的请求
		TAuthUrlEntity authUrlEntity = new TAuthUrlEntity();
		authUrlEntity.setAuthId(authId);
		Wrapper<TAuthUrlEntity> authUrlWrapper = new QueryWrapper<TAuthUrlEntity>(authUrlEntity);
		List<TAuthUrlEntity> urlIdList = authUrlService.list(authUrlWrapper);
		
		//使用了该权限的用户
		TAuthUserEntity authUserEntity = new TAuthUserEntity();
		authUserEntity.setAuthId(authId);
		Wrapper<TAuthUserEntity> authUserWrapper = new QueryWrapper<TAuthUserEntity>(authUserEntity);
		List<TAuthUserEntity> userIdList = authUserService.list(authUserWrapper);
		
		//没有用户和请求使用该权限
		if (ListUtil.isEmpty(urlIdList) && ListUtil.isEmpty(userIdList)) {
			return new ReturnEntityBuild(true).build();
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("urlIdList", urlIdList);
		map.put("userIdList", userIdList);
		return new ReturnEntityBuild(false).mapData(map).message("该权限已被使用！").build();
	}
	
	@RequestMapping(value = "/editAuthDetail", method = RequestMethod.POST)
	@ResponseBody
	public ReturnEntity editAuthDetail(@RequestBody TSAuthEntity authEntity,HttpSession session) {
		boolean editFlg = authService.editAuthDetail(authEntity,session);
		if (editFlg) {
			return new ReturnEntityBuild(true).message("权限信息修改成功！").build();
		}
		return new ReturnEntityBuild(false).message("权限信息修改失败！").build();
	}
	
}
