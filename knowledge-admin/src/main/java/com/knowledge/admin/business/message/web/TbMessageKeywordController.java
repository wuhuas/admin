package com.knowledge.admin.business.message.web;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.feilong.core.Validator;
import com.knowledge.admin.base.login.BaseAdminController;
import com.knowledge.common.base.enums.EnumResult;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.business.message.entity.TbMessageKeyword;
import com.knowledge.common.business.message.service.ITbMessageKeywordService;

/**
 * <p>
 * 留言 关键字管理  前端控制器
 * </p>
 *
 * @author francis
 * @since 2018-12-03
 */
@Controller
@RequestMapping("/messagekeyword/")
public class TbMessageKeywordController extends BaseAdminController {

	@Autowired
	private ITbMessageKeywordService messageService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("listUI")
    public String listUI(@RequestParam Map<String,Object> map,Map<String,Object> result,Integer page) {
		Condition create = Condition.create();
		if (Validator.isNotNullOrEmpty(map.get("keyword"))) {
			create.like("keyword", map.get("keyword").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("status")) && !map.get("status").equals("0")) {
			create.eq("status", map.get("status").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("from")) && Validator.isNotNullOrEmpty(map.get("to"))) {
			create.between("create_time", map.get("from").toString(), map.get("to").toString());
		}
		create.orderBy("id", false);
		Page<TbMessageKeyword> list = messageService.selectPage(new Page<TbMessageKeyword>(null==page?0:page, 10),create);
		map.put("page", list);
		result.putAll(map);
		return "message/keyword/list";
    }
	
	@RequestMapping("form")
    public String form(Map<String,Object> map) {
		return "message/keyword/form";
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Result add(TbMessageKeyword tbmessagekeyword){
		boolean result = false;
		tbmessagekeyword.setStatus(tbmessagekeyword.getStatus() == null ? 2 : tbmessagekeyword.getStatus());
		if(Validator.isNotNullOrEmpty(tbmessagekeyword .getId())){
			if(Validator.isNotNullOrEmpty(messageService.selectList(Condition.create().eq("keyword", tbmessagekeyword.getKeyword()).ne("id", tbmessagekeyword .getId())))) {
				return fail(EnumResult.ERROR_KEYWORD_DOES_EXIST);
			}
			tbmessagekeyword.setUpdateTime(new Date());
			tbmessagekeyword.setUpdateUser(getUserEntity().getAccountName());
			result = messageService.updateById(tbmessagekeyword);
		}else{
			if(Validator.isNotNullOrEmpty(messageService.selectList(Condition.create().eq("keyword", tbmessagekeyword.getKeyword())))) {
				return fail(EnumResult.ERROR_KEYWORD_DOES_EXIST);
			}
			tbmessagekeyword.setCreateUser(getUserEntity().getAccountName());
			result = messageService.insert(tbmessagekeyword);
		}
		if(result){
			return success();
		}else{
			return error();
		}
	}
	
	@RequestMapping(value="{id}/delete",method=RequestMethod.DELETE)
	@ResponseBody
    public Result delete(@PathVariable(required=true) Integer id) {	
		if(!messageService.deleteById(id)){
			return error();
		}
		return success();
    }	
	
	@RequestMapping(value="{id}/select",method=RequestMethod.GET)
    public String select(Map<String,Object> map,@PathVariable(required=true) Integer id) {	
		TbMessageKeyword tbmessagekeyword = messageService.selectById(id);
		map.put("record", tbmessagekeyword);
		return "message/keyword/edit";
    }
	
	@RequestMapping(value="changeStatus/{id}/{status}",method=RequestMethod.GET)
	@ResponseBody
	public Result changeStatus(@PathVariable("id") Integer id,@PathVariable("status") Integer status) {
		TbMessageKeyword tbmessagekeyword = messageService.selectById(id);
		if (Validator.isNullOrEmpty(tbmessagekeyword)) {
			return fail(EnumResult.ERROR_PARAMS);
		}
		tbmessagekeyword.setStatus(status);
		if(tbmessagekeyword.updateById()) {
			return success();
		}else {
			return error();
		}
	}
}
