package org.taurus.controller.sys;

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
import org.taurus.config.util.CodeKeyValue;
import org.taurus.config.util.JsonUtil;
import org.taurus.config.util.ListUtil;
import org.taurus.config.util.MCodeUtil;
import org.taurus.config.util.StrUtil;
import org.taurus.config.util.entity.ReturnEntity;
import org.taurus.config.util.entity.ReturnEntityBuild;
import org.taurus.entity.sys.TSAuthEntity;
import org.taurus.entity.sys.TSUrlExtendEntity;
import org.taurus.service.sys.TSAuthService;
import org.taurus.service.sys.TSUrlService;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

@Controller
@RequestMapping("system/urlController")
public class UrlController {
	
	@Resource
	private HttpServletRequest request;

	@Resource
	private TSAuthService authService;

	@Resource
	private TSUrlService urlService;
	
	@RequestMapping(value = "/openUrlList", method = RequestMethod.GET)
	public String openUserList() {
		Map<String, String> delFlgJson = MCodeUtil.getGroupMap(CodeKeyValue.DEL_FLG_NO.group());
		request.setAttribute("delFlgJson", JsonUtil.mapToJson(delFlgJson));
		return "sys/urlList";
	}

	@RequestMapping(value = "/initUrlList", method = RequestMethod.POST)
	@ResponseBody
	public ReturnEntity initAuthList(TSUrlExtendEntity urlExtendEntity){
		List<TSUrlExtendEntity> list_detail = urlService.selectExtendInfo(urlExtendEntity);
		return new ReturnEntityBuild(true).listData(list_detail).build();
	}
	
	@RequestMapping(value = "/openEditUrl", method = RequestMethod.GET)
	public String openEditAuth(String urlId) {
		if (StrUtil.isNotEmpty(urlId)) {
			request.setAttribute("urlId", urlId);
		}
		
		TSAuthEntity query = new TSAuthEntity();
		query.setAuthDelFlg(CodeKeyValue.DEL_FLG_NO.value());
		
		Wrapper<TSAuthEntity> queryWrapper = new QueryWrapper<TSAuthEntity>(query);
		List<TSAuthEntity> allAuth = authService.list(queryWrapper);
		request.setAttribute("allAuthJson", JsonUtil.listToJson(allAuth));
		
		return "sys/editUrl";
	}
	
	@RequestMapping(value = "/initEditUrl", method = RequestMethod.POST)
	@ResponseBody
	public ReturnEntity initEditAuth(TSUrlExtendEntity urlExtendEntity) {
		if (urlExtendEntity!=null && StrUtil.isNotEmpty(urlExtendEntity.getUrlId())) {
			List<TSUrlExtendEntity> list_detail = urlService.selectExtendInfo(urlExtendEntity);
			if (ListUtil.isNotEmpty(list_detail)) {
				return new ReturnEntityBuild(true).objectData(list_detail.get(0)).build();
			}
		}
		return new ReturnEntityBuild(true).build();
	}
	
	@RequestMapping(value = "/editUrlDetail", method = RequestMethod.POST)
	@ResponseBody
	public ReturnEntity editUrlDetail(@RequestBody TSUrlExtendEntity urlExtendEntity,HttpSession session) {
		boolean updateUrlDetailFlg = urlService.editUrlDetail(urlExtendEntity,session);
		if (updateUrlDetailFlg) {
			return new ReturnEntityBuild(true).message("请求信息修改成功！").build();
		}
		return new ReturnEntityBuild(false).message("请求信息修改失败！").build();
	}

}
