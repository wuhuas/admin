package com.knowledge.admin.business.activity.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.feilong.core.Validator;
import com.feilong.core.date.DateUtil;
import com.knowledge.admin.base.login.BaseAdminController;
import com.knowledge.common.base.enums.EnumResult;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.business.activity.entity.TbActivityManage;
import com.knowledge.common.business.activity.entity.TbPastActivityManage;
import com.knowledge.common.business.activity.service.ITbActivityManageService;
import com.knowledge.common.business.activity.service.ITbPastActivityManageService;

/**
 * <p>
 * 以往活动管理  前端控制器
 * </p>
 *
 * @author xiong
 * @since 2018-11-14
 */
@Controller
@RequestMapping("/activityPast/")

public class TbPastActivityManageController extends BaseAdminController {

	@Autowired
	private ITbPastActivityManageService activityPastService;
	
	@Autowired
	private ITbActivityManageService activityService;

	@SuppressWarnings("unchecked")
	@RequestMapping("listUI")
    public String listUI(Map<String,Object> map,Integer page, String title,String activityFromTime,String activityToTime,Integer status,String relation) {
		
		//條件查詢構造器
		Condition create = Condition.create();
		create.isWhere(true);
		//按照名字搜索
		if (Validator.isNotNullOrEmpty(title)) {
			create.like("title", title);
		}
		
		
		//指定時間範圍內    活动时间
		if (Validator.isNotNullOrEmpty(activityFromTime) && Validator.isNotNullOrEmpty(activityToTime)) {
			create.between("DATE_FORMAT(activity_time, '%Y-%m-%d')", activityFromTime, activityToTime);
		}
		
		
		//狀態
		if (Validator.isNotNullOrEmpty(status) && status != 0) {
			create.eq("status" , status);
		}
		
		//relation相當於活動id
		if (Validator.isNotNullOrEmpty(relation) && "0".equals(relation)==false) {
			create.andNew("FIND_IN_SET('"+relation+"',relation)");
		}
		
		//排序
		create.orderBy("id", false);
		
		Page<TbPastActivityManage> list = activityPastService.selectByPage(new Page<TbPastActivityManage>(null==page?0:page, 10), create);
		map.put("activities", getActivityList());
		map.put("page", list);
		map.put("title", title);
		map.put("activityFromTime", activityFromTime);
		map.put("activityToTime", activityToTime);
		map.put("status", status);
		map.put("relation", relation);
		return "activityPast/list";
    }
	
	@RequestMapping("form")
    public String form(Map<String,Object> map) {
		map.put("activities", getActivityList());
		return "activityPast/form";
    }
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Result add(TbPastActivityManage pastActivityManage,String SetActivityTime,String SetActivityTimeEnd){
		
		if(null != SetActivityTime && 10 == SetActivityTime.length()){
			//设置活动时间
			pastActivityManage.setActivityTime(com.feilong.core.date.DateUtil.toDate(SetActivityTime,"yyyy-MM-dd"));
		}
		
		if(null != SetActivityTimeEnd && 10 == SetActivityTimeEnd.length()){
			//设置活动结束时间
			pastActivityManage.setActivityTimeEnd(com.feilong.core.date.DateUtil.toDate(SetActivityTimeEnd,"yyyy-MM-dd"));
		}
		
		boolean result = false;
		pastActivityManage.setStatus(pastActivityManage.getStatus() == null ? 2 : pastActivityManage.getStatus());
		if(Validator.isNotNullOrEmpty(pastActivityManage .getId())){
			result = activityPastService.updateById(pastActivityManage);
		}else{
			pastActivityManage.setReleaseName(getAccountName());
			pastActivityManage.setReleaseTime(new Date());
			result = activityPastService.insert(pastActivityManage);
		}
		if(result){
			return success();
		}else{
			return error();
		}
	}
	
	
	@RequestMapping(value="{id}/select",method=RequestMethod.GET)
    public String select(Map<String,Object> map,@PathVariable(required=true) Integer id) {	
		TbPastActivityManage pastActivityManage = activityPastService.selectById(id);
		map.put("activities", getActivityList());
		List<String> ids = new ArrayList<>();
		if(Validator.isNotNullOrEmpty(pastActivityManage)) {
			String relation = pastActivityManage.getRelation();
			if(Validator.isNotNullOrEmpty(relation)) {
				ids = Arrays.asList(relation.split(","));
			}
		}
		map.put("relationList", ids);
		map.put("record", pastActivityManage);
		return "activityPast/edit";
    }
	
	@SuppressWarnings("unchecked")
	private List<TbPastActivityManage> getActivityList(){
		return activityPastService.selectList(Condition.create().eq("status", 1));
	}
	
	@RequestMapping(value="changeStatus/{id}/{status}",method=RequestMethod.GET)
	@ResponseBody
	public Result changeStatus(@PathVariable("id") Integer id,@PathVariable("status") Integer status) {
		TbPastActivityManage pastActivityManage  = activityPastService.selectById(id);
		if (Validator.isNullOrEmpty(pastActivityManage)) {
			return fail(EnumResult.ERROR_PARAMS);
		}
		pastActivityManage.setStatus(status);
		if(pastActivityManage.updateById()) {
			return success();
		}else {
			return error();
		}
	}
}
