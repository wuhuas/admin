package com.knowledge.admin.business.draft.web;

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
import com.knowledge.common.business.creation.entity.TbCreationManage;
import com.knowledge.common.business.creation.service.ITbCreationManageService;

/**
 * <p>
 * 创作天地管理  前端控制器
 * </p>
 *
 * @author francis
 * @since 2018-12-08
 */
@Controller
@RequestMapping("/creation/")

public class TbCreationManageController extends BaseAdminController {

	@Autowired
	private ITbCreationManageService creationService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("listUI")
    public String listUI(@RequestParam Map<String,Object> map,Map<String,Object> result,Integer page) {
		Condition create = Condition.create();
		if(Validator.isNotNullOrEmpty(map.get("name"))) {
			create.like("name", map.get("name").toString());
		}
		if(Validator.isNotNullOrEmpty(map.get("studentName"))) {
			create.like("studentName", map.get("studentName").toString());
		}
		if(Validator.isNotNullOrEmpty(map.get("type")) && !map.get("type").equals("-1")) {
			create.eq("type", map.get("type").toString());
		}
		if(Validator.isNotNullOrEmpty(map.get("status")) && !map.get("status").equals("0")) {
			create.eq("status", map.get("status").toString());
		}
		if(Validator.isNotNullOrEmpty(map.get("isHome")) && !map.get("isHome").equals("0")) {
			create.eq("is_home", map.get("isHome").toString());
		}
		if(Validator.isNotNullOrEmpty(map.get("recommendWorks")) && !map.get("recommendWorks").equals("0")) {
			create.eq("recommend_works", map.get("recommendWorks").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("from")) && Validator.isNotNullOrEmpty(map.get("to"))) {
			create.between("DATE_FORMAT(create_time, '%Y-%m-%d')", map.get("from"), map.get("to"));
		}
		
		//排序
		if(null!=map.get("sort") && "".equals(map.get("sort"))==false){
			create.orderBy(map.get("sort").toString(),"true".equals(map.get("isAsc").toString()));
		}else{
			create.orderBy("create_time",false);
		}
		
		
		Page<TbCreationManage> list = creationService.selectPage(new Page<TbCreationManage>(null==page?0:page, 10),create);
		map.put("page", list);
		result.putAll(map);
		return "creation/list";
    }
	
	@RequestMapping("form")
    public String form(Map<String,Object> map) {
		return "creation/form";
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Result add(TbCreationManage tbcreationmanage){
		
		
		//作文不能推荐   4
		if(4==tbcreationmanage.getType() && null!=tbcreationmanage.getIsHome()){
			return fail(EnumResult.ERROR_COMPOSITION_STOP);
		}
		
		
		boolean result = false;
		tbcreationmanage.setStatus(tbcreationmanage.getStatus() == null ? 2 : tbcreationmanage.getStatus());
		tbcreationmanage.setType(tbcreationmanage.getType() == null ? 2 : tbcreationmanage.getType());
		tbcreationmanage.setIsHome(tbcreationmanage.getIsHome() == null ? 2 : tbcreationmanage.getIsHome());
		tbcreationmanage.setRecommendWorks(tbcreationmanage.getRecommendWorks() == null ? 2 : tbcreationmanage.getRecommendWorks());

		
		if("".equals(tbcreationmanage.getCoverUrl())){
			tbcreationmanage.setCoverUrl(null);
		}
		

		if(Validator.isNotNullOrEmpty(tbcreationmanage .getId())){
			if (tbcreationmanage.getRecommendWorks() == 1 && creationService.selectCount(Condition.create().eq("recommend_works",1).ne("id", tbcreationmanage .getId())) >= 3) {
				return fail(EnumResult.ERROR_RECOMMEND_WORKS_ONLY_THREE);
			}
			tbcreationmanage.setUpdateUser(getAccountName());
			tbcreationmanage.setUpdateTime(new Date());
			result = creationService.updateById(tbcreationmanage);
		}else{
			if (tbcreationmanage.getRecommendWorks() == 1 && creationService.selectCount(Condition.create().eq("recommend_works",1)) >= 3) {
				return fail(EnumResult.ERROR_RECOMMEND_WORKS_ONLY_THREE);
			}
			tbcreationmanage.setCreateUser(getAccountName());
			tbcreationmanage.setCreateTime(new Date());
			result = creationService.insert(tbcreationmanage);
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
		if(!creationService.deleteById(id)){
			return error();
		}
		return success();
    }	
	
	@RequestMapping(value="{id}/select",method=RequestMethod.GET)
    public String select(Map<String,Object> map,@PathVariable(required=true) Integer id) {	
		TbCreationManage tbcreationmanage = creationService.selectById(id);
		map.put("record", tbcreationmanage);
		return "creation/edit";
    }
	
	@RequestMapping(value="changeStatus/{id}/{status}",method=RequestMethod.GET)
	@ResponseBody
	public Result changeStatus(@PathVariable("id") Integer id,@PathVariable("status") Integer status) {
		TbCreationManage tbcreationmanage = creationService.selectById(id);
		if (Validator.isNullOrEmpty(tbcreationmanage)) {
			return fail(EnumResult.ERROR_PARAMS);
		}
		tbcreationmanage.setStatus(status);
		tbcreationmanage.setUpdateUser(getAccountName());
		if(tbcreationmanage.updateById()) {
			return success();
		}else {
			return error();
		}
	}
	
	@RequestMapping(value="changeIsHome/{id}/{isHome}",method=RequestMethod.GET)
	@ResponseBody
	public Result changeIsHome(@PathVariable("id") Integer id,@PathVariable("isHome") Integer isHome) {
		TbCreationManage tbcreationmanage = creationService.selectById(id);
		
		//作文不能推荐   4
		if(4==tbcreationmanage.getType()){
			return fail(EnumResult.ERROR_COMPOSITION_STOP);
		}
		
		
		if (Validator.isNullOrEmpty(tbcreationmanage)) {
			return fail(EnumResult.ERROR_PARAMS);
		}
		tbcreationmanage.setIsHome(isHome);
		tbcreationmanage.setUpdateUser(getAccountName());
		if(tbcreationmanage.updateById()) {
			return success();
		}else {
			return error();
		}
	}
	
}
