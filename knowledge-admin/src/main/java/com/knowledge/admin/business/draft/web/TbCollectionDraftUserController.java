package com.knowledge.admin.business.draft.web;

import java.util.Date;
import java.util.Map;
import org.springframework.stereotype.Controller;
import com.knowledge.admin.base.login.BaseAdminController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.feilong.core.Validator;
import com.knowledge.common.base.enums.EnumResult;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.business.draft.service.ITbCollectionDraftUserService;
import com.knowledge.common.business.draft.entity.TbCollectionDraftUser;

/**
 * <p>
 * 投稿管理  前端控制器
 * </p>
 *
 * @author francis
 * @since 2018-12-08
 */
@Controller
@RequestMapping("/draftuser/")

public class TbCollectionDraftUserController extends BaseAdminController {

	@Autowired
	private ITbCollectionDraftUserService draftService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("listUI")
    public String listUI(@RequestParam Map<String,Object> map,Map<String,Object> result,Integer page) {
		Condition create = Condition.create();
		if(Validator.isNotNullOrEmpty(map.get("name"))) {
			create.like("name", map.get("name").toString());
		}
		if(Validator.isNotNullOrEmpty(map.get("studentName"))) {
			create.like("student_name", map.get("studentName").toString());
		}
		if(Validator.isNotNullOrEmpty(map.get("schoolName"))) {
			create.like("school_name", map.get("schoolName").toString());
		}
		if(Validator.isNotNullOrEmpty(map.get("email"))) {
			create.eq("email", map.get("email").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("from")) && Validator.isNotNullOrEmpty(map.get("to"))) {
			create.between("DATE_FORMAT(draft_time, '%Y-%m-%d')", map.get("from"), map.get("to"));
		}
		if (Validator.isNotNullOrEmpty(map.get("status")) && !map.get("status").equals("-1")) {
			create.eq("status",map.get("status"));
		}
		if (Validator.isNotNullOrEmpty(map.get("type")) && !map.get("type").equals("-1")) {
			create.eq("type",map.get("type"));
		}
		
		//排序
		if(null!=map.get("sort") && "".equals(map.get("sort"))==false){
			create.orderBy(map.get("sort").toString(),"true".equals(map.get("isAsc").toString()));
		}else{
			create.orderBy("draft_time",false);
		}
		
		
		Page<TbCollectionDraftUser> list = draftService.selectPage(new Page<TbCollectionDraftUser>(null==page?0:page, 10),create);
		
		map.put("page", list);
		result.putAll(map);
		return "draftuser/list";
    }
	
	@RequestMapping("form")
    public String form(Map<String,Object> map) {
		return "draftuser/form";
    }
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Result add(TbCollectionDraftUser tbcollectiondraftuser){
		boolean result = false;
		if(Validator.isNotNullOrEmpty(tbcollectiondraftuser .getId())){
			result = draftService.updateById(tbcollectiondraftuser);
		}else{
			tbcollectiondraftuser .setCreateUser(getUserEntity().getAccountName());
			result = draftService.insert(tbcollectiondraftuser);
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
		TbCollectionDraftUser tbcollectiondraftuser = draftService.selectById(id);
		map.put("record", tbcollectiondraftuser);
		return "draftuser/view";
    }
	
	@RequestMapping(value="review/{id}/{status}",method=RequestMethod.GET)
	@ResponseBody
    public Result select(Map<String,Object> map,@PathVariable(required=true) Integer id,@PathVariable(required=true) Integer status) {	
		TbCollectionDraftUser tbcollectiondraftuser = draftService.selectById(id);
		if (Validator.isNullOrEmpty(tbcollectiondraftuser)) {
			return fail(EnumResult.ERROR_PARAMS);
		}
		if(tbcollectiondraftuser.getStatus() == status) {
			return fail("該投稿已處理","-1");
		}
		tbcollectiondraftuser.setStatus(status);
		tbcollectiondraftuser.setReviewer(getAccountName());
		tbcollectiondraftuser.setReviewTime(new Date());
		if (tbcollectiondraftuser.updateById()) {
			return success();
		}
		return error();
    }
}
