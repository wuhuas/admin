package com.knowledge.admin.base.loginlog.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.knowledge.admin.base.login.BaseAdminController;
import com.knowledge.admin.base.loginlog.entity.TbLoginInfo;
import com.knowledge.admin.base.loginlog.service.ITbLoginInfoService;

/**
 * <p>
 *   前端控制器
 * </p>
 *
 * @author xj
 * @since 2017-01-04
 */
@Controller
@RequestMapping("/loginlog/")

public class TbLoginInfoController extends BaseAdminController {

	@Autowired
	private ITbLoginInfoService loginlogService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("listUI")
    public String listUI(Map<String,Object> map,Integer page,String accountName) {
		Condition create = Condition.create();
		if (!getAccountName().equals("system")) {
			create.ne("account_name", "system");
		}
		create.like("account_name", accountName).orderBy("id", false);
		Page<TbLoginInfo> list = loginlogService.selectPage(new Page<TbLoginInfo>(null==page?0:page, 10),create);
		map.put("page", list);
		map.put("accountName", accountName);
		return "loginlog/list";
    }
	

}
