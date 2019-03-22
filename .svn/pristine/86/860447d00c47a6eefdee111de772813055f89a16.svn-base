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
import com.knowledge.common.business.draft.entity.TbCollectionDraftManage;
import com.knowledge.common.business.draft.service.ITbCollectionDraftManageService;

/**
 * <p>
 * 征稿启示管理  前端控制器
 * </p>
 *
 * @author francis
 * @since 2018-12-08
 */
@Controller
@RequestMapping("/draft/")
public class TbCollectionDraftManageController extends BaseAdminController {

	@Autowired
	private ITbCollectionDraftManageService draftService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("listUI")
    public String listUI(@RequestParam Map<String,Object> map,Map<String, Object> result,Integer page) {
		Condition create = Condition.create();
		if(Validator.isNotNullOrEmpty(map.get("title"))) {
			create.like("title", map.get("title").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("from")) && Validator.isNotNullOrEmpty(map.get("to"))) {
			create.between("DATE_FORMAT(release_time, '%Y-%m-%d')", map.get("from"), map.get("to"));
		}
		if (Validator.isNotNullOrEmpty(map.get("status")) && !map.get("status").equals("0")) {
			create.eq("status",map.get("status"));
		}
		
		
		//排序
		if(null!=map.get("sort") && "".equals(map.get("sort"))==false){
			create.orderBy(map.get("sort").toString(),"true".equals(map.get("isAsc").toString()));
		}else{
			create.orderBy("release_time",false);
		}
		
		
		Page<TbCollectionDraftManage> list = draftService.selectPage(new Page<TbCollectionDraftManage>(null==page?0:page, 10),create);
		map.put("page", list);
		result.putAll(map);
		return "draft/list";
    }
	
	@RequestMapping("form")
    public String form(Map<String,Object> map) {
		return "draft/form";
    }
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Result add(TbCollectionDraftManage tbcollectiondraftmanage){
		
		
		//每次只有一個征稿啟示上架
		Condition create = Condition.create();
		create.eq("status", 1);
		


		
		
		if("".equals(tbcollectiondraftmanage.getCoverUrl())){
			tbcollectiondraftmanage.setCoverUrl(null);
		}
		if("".equals(tbcollectiondraftmanage.getEnclosureUrl())){
			tbcollectiondraftmanage.setEnclosureUrl(null);
		}
		
		boolean result = false;
		tbcollectiondraftmanage.setStatus(tbcollectiondraftmanage.getStatus() == null ? 2 : tbcollectiondraftmanage.getStatus());
		if(Validator.isNotNullOrEmpty(tbcollectiondraftmanage .getId())){
			
			//每次只有一個征稿啟示上架
			create.ne("id", tbcollectiondraftmanage.getId());
			Integer total=draftService.selectCount(create);
			if(total>=1 && tbcollectiondraftmanage.getStatus()!=null){
				return fail(EnumResult.ERROR_DRAFT_UPPER_MAX);
			}
			
			tbcollectiondraftmanage.setUpdateTime(new Date());
			tbcollectiondraftmanage.setUpdateUser(getAccountName());
			result = draftService.updateById(tbcollectiondraftmanage);
		}else{
			
			//每次只有一個征稿啟示上架
			Integer total=draftService.selectCount(create);
			if(total>=1 && tbcollectiondraftmanage.getStatus()!=null){
				return fail(EnumResult.ERROR_DRAFT_UPPER_MAX);
			}
			
			
			tbcollectiondraftmanage.setReleaseTime(new Date());
			tbcollectiondraftmanage.setReleaseUser(getAccountName());
			result = draftService.insert(tbcollectiondraftmanage);
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
		if(!draftService.deleteById(id)){
			return error();
		}
		return success();
    }	
	
	@RequestMapping(value="{id}/select",method=RequestMethod.GET)
    public String select(Map<String,Object> map,@PathVariable(required=true) Integer id) {	
		TbCollectionDraftManage tbcollectiondraftmanage = draftService.selectById(id);
		map.put("record", tbcollectiondraftmanage);
		return "draft/edit";
    }
	
	@RequestMapping(value="changeStatus/{id}/{status}",method=RequestMethod.GET)
	@ResponseBody
	public Result changeStatus(@PathVariable("id") Integer id,@PathVariable("status") Integer status) {
		TbCollectionDraftManage tbcollectiondraftmanage = draftService.selectById(id);
		
		
		//每次只有一個征稿啟示上架
		Condition create = Condition.create();
		create.eq("status", 1);
		Integer total=draftService.selectCount(create);
		
		if(total>=1 && status!=2){
			return fail(EnumResult.ERROR_DRAFT_UPPER_MAX);
		}
		
		
		if (Validator.isNullOrEmpty(tbcollectiondraftmanage)) {
			return fail(EnumResult.ERROR_PARAMS);
		}
		tbcollectiondraftmanage.setStatus(status);
		if(tbcollectiondraftmanage.updateById()) {
			return success();
		}else {
			return error();
		}
	}
}
