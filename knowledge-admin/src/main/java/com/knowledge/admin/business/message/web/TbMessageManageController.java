package com.knowledge.admin.business.message.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.knowledge.admin.base.login.BaseAdminController;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.business.message.entity.TbMessageManage;
import com.knowledge.common.business.message.service.ITbMessageManageService;

/**
 * <p>
 * 留言管理  前端控制器
 * </p>
 *
 * @author francis
 * @since 2018-12-03
 */
@Controller
@RequestMapping("/message/")
public class TbMessageManageController extends BaseAdminController {

	@Autowired
	private ITbMessageManageService messageService;
	
	@RequestMapping("listUI")
    public String listUI(@RequestParam Map<String,Object> map,Map<String,Object> result,Integer page) {
		Page<TbMessageManage> list = messageService.selectByPage(new Page<TbMessageManage>(null==page?0:page, 10),map);
		map.put("page", list);
		result.putAll(map);
		return "message/list";
    }
	
	@RequestMapping(value="{id}/delete",method=RequestMethod.DELETE)
	@ResponseBody
    public Result delete(@PathVariable(required=true) Integer id) {	
		messageService.delete(id);
		return success();
    }
	
	@RequestMapping(value="{id}/select",method=RequestMethod.GET)
    public String select(Map<String,Object> map,@PathVariable(required=true) Integer id) {	
		TbMessageManage tbmessagemanage = messageService.selectById(id);
		map.put("record", tbmessagemanage);
		return "message/edit";
    }	
}
