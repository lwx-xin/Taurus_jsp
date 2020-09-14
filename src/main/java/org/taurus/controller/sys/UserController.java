package org.taurus.controller.sys;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.taurus.config.util.CodeKeyValue;
import org.taurus.config.util.JsonUtil;
import org.taurus.config.util.ListUtil;
import org.taurus.config.util.MCodeUtil;
import org.taurus.config.util.StrUtil;
import org.taurus.config.util.entity.ReturnEntity;
import org.taurus.config.util.entity.ReturnEntityBuild;
import org.taurus.entity.sys.TSAuthEntity;
import org.taurus.entity.sys.TSUserExtendEntity;
import org.taurus.service.sys.TSAuthService;
import org.taurus.service.sys.TSUserService;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

@Controller
@RequestMapping("system/userController")
public class UserController {
	
	@Resource
	private TSUserService userService;

	@Resource
	private TSAuthService authService;
	
	@RequestMapping(value = "/openUserList", method = RequestMethod.GET)
	public String openUserList(HttpServletRequest request) {
		Map<String, String> delFlgJson = MCodeUtil.getGroupMap(CodeKeyValue.DEL_FLG_NO.group());
		request.setAttribute("delFlgJson", JsonUtil.mapToJson(delFlgJson));
		return "sys/userList";
	}

	@RequestMapping(value = "/initUserList", method = RequestMethod.POST)
	@ResponseBody
	public ReturnEntity initUserList(TSUserExtendEntity extendEntity){
		List<TSUserExtendEntity> userList = userService.selectExtendInfo(null);
		return new ReturnEntityBuild(true).listData(userList).build();
	}
	
	@RequestMapping(value = "/openEditUser", method = RequestMethod.GET)
	public String openEditUser(String userId, HttpServletRequest request) {
		if (StrUtil.isNotEmpty(userId)) {
			request.setAttribute("userId", userId);
		}
		
		TSAuthEntity query = new TSAuthEntity();
		query.setAuthDelFlg(CodeKeyValue.DEL_FLG_NO.value());
		
		Wrapper<TSAuthEntity> queryWrapper = new QueryWrapper<TSAuthEntity>(query);
		List<TSAuthEntity> allAuth = authService.list(queryWrapper);
		request.setAttribute("allAuthJson", JsonUtil.listToJson(allAuth));
		
		return "sys/editUser";
	}
	
	@RequestMapping(value = "/initEditUser", method = RequestMethod.POST)
	@ResponseBody
	public ReturnEntity initEditUser(TSUserExtendEntity extendEntity) {
		if (extendEntity!=null && StrUtil.isNotEmpty(extendEntity.getUserId())) {
			
			List<TSUserExtendEntity> list_detail = userService.selectExtendInfo(extendEntity.getUserId());
			if (ListUtil.isNotEmpty(list_detail)) {
				return new ReturnEntityBuild(true).objectData(list_detail.get(0)).build();
			}
		}
		return new ReturnEntityBuild(true).build();
	}
	
	@RequestMapping(value = "/editUserDetail", method = RequestMethod.POST)
	@ResponseBody
	public ReturnEntity editUserDetail(@RequestBody TSUserExtendEntity extendEntity) {
		boolean updateUserDetailFlg = userService.editUserDetail(extendEntity);
		if (updateUserDetailFlg) {
			return new ReturnEntityBuild(true).message("用户信息修改成功！").build();
		}
		return new ReturnEntityBuild(false).message("用户信息修改失败！").build();
	}
	
	@RequestMapping(value = "/getUserInfoById", method = RequestMethod.POST)
	@ResponseBody
	public ReturnEntity getUserInfoById(TSUserExtendEntity extendEntity) {
		if (extendEntity!=null && StrUtil.isNotEmpty(extendEntity.getUserId())) {
			
			List<TSUserExtendEntity> list_detail = userService.selectExtendInfo(extendEntity.getUserId());
			if (ListUtil.isNotEmpty(list_detail)) {
				return new ReturnEntityBuild(true).objectData(list_detail.get(0)).build();
			}
		}
		return new ReturnEntityBuild(false).message("未知的用户").build();
	}
}
