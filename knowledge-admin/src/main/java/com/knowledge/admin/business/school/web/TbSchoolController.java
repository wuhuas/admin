package com.knowledge.admin.business.school.web;

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
import com.knowledge.common.business.school.entity.TbSchoolCalendarRecommend;
import com.knowledge.common.business.school.service.ITbSchoolCalendarRecommendService;

/**
 * 校曆推薦
 * @author wb
 *
 */
@Controller
@RequestMapping("/school/")
public class TbSchoolController extends BaseAdminController {

	
	@Autowired
	private ITbSchoolCalendarRecommendService iTbSchoolCalendarRecommendService;
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("listUI")
    public String listUI(@RequestParam Map<String,Object> map,Map<String, Object> result,Integer page) {
		
		Condition create = Condition.create();
		if(Validator.isNotNullOrEmpty(map.get("title"))) {
			create.like("title", map.get("title").toString());
		}
		if(Validator.isNotNullOrEmpty(map.get("school"))) {
			create.like("school", map.get("school").toString());
		}
		
		if (Validator.isNotNullOrEmpty(map.get("from")) && Validator.isNotNullOrEmpty(map.get("to"))) {
			create.between("DATE_FORMAT(activity_date, '%Y-%m-%d')", map.get("from"), map.get("to"));
		}
		if (Validator.isNotNullOrEmpty(map.get("status")) && !map.get("status").equals("0")) {
			create.eq("status",map.get("status"));
		}
		
		//排序
		if(null!=map.get("sort") && "".equals(map.get("sort"))==false){
			create.orderBy(map.get("sort").toString(),"true".equals(map.get("isAsc").toString()));
		}else{
			create.orderBy("id",false);
		}
		
		Page<TbSchoolCalendarRecommend> list = iTbSchoolCalendarRecommendService.selectPage(new Page<TbSchoolCalendarRecommend>(null==page?0:page, 10),create);
		map.put("page", list);
		result.putAll(map);
		return "school/list";
    }
	
	
	
	
	
	@RequestMapping(value="changeStatus/{id}/{status}",method=RequestMethod.GET)
	@ResponseBody
	public Result changeStatus(@PathVariable("id") Integer id,@PathVariable("status") Integer status) {
		TbSchoolCalendarRecommend tbSchoolCalendarRecommend = iTbSchoolCalendarRecommendService.selectById(id);
		if (Validator.isNullOrEmpty(tbSchoolCalendarRecommend)) {
			return fail(EnumResult.ERROR_PARAMS);
		}
		tbSchoolCalendarRecommend.setStatus(status);
		if(iTbSchoolCalendarRecommendService.updateById(tbSchoolCalendarRecommend)) {
			return success();
		}else {
			return error();
		}
	}
	
	
	
	
	@RequestMapping("form")
    public String form(Map<String,Object> map) {
		return "school/form";
    }
	
	
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Result add(TbSchoolCalendarRecommend tbSchoolCalendarRecommend,String from){
		boolean result = false;

		tbSchoolCalendarRecommend.setActivityDate(com.feilong.core.date.DateUtil.toDate(from,"yyyy-MM-dd"));

		
		if(null==tbSchoolCalendarRecommend.getStatus()){
			tbSchoolCalendarRecommend.setStatus(2);
		}
		
		if(Validator.isNotNullOrEmpty(tbSchoolCalendarRecommend.getId())){
			result = iTbSchoolCalendarRecommendService.updateById(tbSchoolCalendarRecommend);
		}else{
			tbSchoolCalendarRecommend.setCreateTime(new Date());
			tbSchoolCalendarRecommend.setCreateName(getAccountName());
			result = iTbSchoolCalendarRecommendService.insert(tbSchoolCalendarRecommend);
		}
		
		
		
		if(result){
			return success();
		}else{
			return error();
		}
	}
	
	
	
	
	@RequestMapping(value="{id}/select",method=RequestMethod.GET)
    public String select(Map<String,Object> map,@PathVariable(required=true) Integer id) {	
		TbSchoolCalendarRecommend tbSchoolCalendarRecommend = iTbSchoolCalendarRecommendService.selectById(id);
		map.put("record", tbSchoolCalendarRecommend);
		return "school/edit";
    }
	
	
	
	
}
