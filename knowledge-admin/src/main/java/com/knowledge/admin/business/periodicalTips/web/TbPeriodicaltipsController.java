package com.knowledge.admin.business.periodicalTips.web;

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
import com.knowledge.common.business.periodicaltips.entity.TbPeriodicalTips;
import com.knowledge.common.business.periodicaltips.service.ITbPeriodicalTipsService;


/**
 * 成長寄語
 * @author wb
 *
 */
@Controller
@RequestMapping("/periodicalTips/")
public class TbPeriodicaltipsController extends BaseAdminController {

	@Autowired
	private ITbPeriodicalTipsService iTbPeriodicalTipsService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("listUI")
    public String listUI(@RequestParam Map<String,Object> map,Map<String, Object> result,Integer page) {
		Condition create = Condition.create();
		if(Validator.isNotNullOrEmpty(map.get("title"))) {
			create.like("title", map.get("title").toString());
		}
		if(Validator.isNotNullOrEmpty(map.get("name"))) {
			create.like("name", map.get("name").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("from")) && Validator.isNotNullOrEmpty(map.get("to"))) {
			create.between("DATE_FORMAT(create_time, '%Y-%m-%d')", map.get("from"), map.get("to"));
		}
		if (Validator.isNotNullOrEmpty(map.get("status")) && !map.get("status").equals("0")) {
			create.eq("status",map.get("status"));
		}
		
		//排序
		if(null!=map.get("sort") && "".equals(map.get("sort"))==false){
			create.orderBy(map.get("sort").toString(),"true".equals(map.get("isAsc").toString()));
		}else{
			create.orderBy("weight",false);
		}
		
		Page<TbPeriodicalTips> list = iTbPeriodicalTipsService.selectPage(new Page<TbPeriodicalTips>(null==page?0:page, 10),create);
		map.put("page", list);
		result.putAll(map);
		return "periodicaltips/list";
    }
	
	
	
	@RequestMapping(value="changeStatus/{id}/{status}",method=RequestMethod.GET)
	@ResponseBody
	public Result changeStatus(@PathVariable("id") Integer id,@PathVariable("status") Integer status) {
		TbPeriodicalTips tbPeriodicalTips = iTbPeriodicalTipsService.selectById(id);
		if (Validator.isNullOrEmpty(tbPeriodicalTips)) {
			return fail(EnumResult.ERROR_PARAMS);
		}
		tbPeriodicalTips.setStatus(status);
		
		if(iTbPeriodicalTipsService.updateById(tbPeriodicalTips)) {
			return success();
		}else {
			return error();
		}
	}
	
	
	
	
	
	@RequestMapping(value="changeIsHome/{id}/{isHome}",method=RequestMethod.GET)
	@ResponseBody
	public Result changeIsHome(@PathVariable("id") Integer id,@PathVariable("isHome") Integer isHome) {
		TbPeriodicalTips tbPeriodicalTips = iTbPeriodicalTipsService.selectById(id);
		if (Validator.isNullOrEmpty(tbPeriodicalTips)) {
			return fail(EnumResult.ERROR_PARAMS);
		}
		tbPeriodicalTips.setIsHome(isHome);

		if(iTbPeriodicalTipsService.updateById(tbPeriodicalTips)) {
			return success();
		}else {
			return error();
		}
	}
	
	
	
	@RequestMapping("form")
    public String form(Map<String,Object> map) {
		return "periodicaltips/form";
    }
	
	
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Result add(TbPeriodicalTips tbPeriodicalTips){
		boolean result = false;
		
		
		tbPeriodicalTips.setCreateName(getAccountName());
		tbPeriodicalTips.setCreateTime(new Date());
		
		if(null==tbPeriodicalTips.getIsHome()){
			tbPeriodicalTips.setIsHome(2);
		}
		
		if(null==tbPeriodicalTips.getStatus()){
			tbPeriodicalTips.setStatus(2);
		}
		
		if(Validator.isNotNullOrEmpty(tbPeriodicalTips .getId())){
			result = iTbPeriodicalTipsService.updateById(tbPeriodicalTips);
		}else{
			result = iTbPeriodicalTipsService.insert(tbPeriodicalTips);
		}
		if(result){
			return success();
		}else{
			return error();
		}
	}
	
	
	
	
	@RequestMapping(value="{id}/select",method=RequestMethod.GET)
    public String select(Map<String,Object> map,@PathVariable(required=true) Integer id) {	
		TbPeriodicalTips tbPeriodicalTips = iTbPeriodicalTipsService.selectById(id);
		map.put("record", tbPeriodicalTips);
		return "periodicaltips/edit";
    }
	
	
	
}
