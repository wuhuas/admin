package com.knowledge.admin.business.activity.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.springframework.stereotype.Controller;
import com.knowledge.admin.base.login.BaseAdminController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.feilong.core.Validator;
import com.knowledge.common.base.enums.EnumResult;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.business.activity.service.ITbActivityManageService;


import com.knowledge.common.business.activity.entity.TbActivityManage;

/**
 * <p>
 * 活动管理  前端控制器
 * </p>
 *
 * @author xiong
 * @since 2018-11-14
 */
@Controller
@RequestMapping("/activity/")

public class TbActivityManageController extends BaseAdminController {

	@Autowired
	private ITbActivityManageService activityService;
	

	@SuppressWarnings("unchecked")
	@RequestMapping("listUI")
	/**
	 * 獲取列表
	 * @param map
	 * @param page
	 * @param name
	 * @param enrollFrom
	 * @param enrollTo
	 * @param startFrom
	 * @param endTo
	 * @param status
	 * @return
	 */
    public String listUI(Map<String,Object> map, Integer page, String name, String enrollFrom ,String enrollTo,
    		String startFrom ,String endTo,Integer status) {
		
		//條件查詢構造器
		Condition create = Condition.create();
		
		//按照名字搜索
		if (Validator.isNotNullOrEmpty(name)) {
			create.like("activity_name", name);
		}
		
		//指定時間範圍內    報名時間
		if (Validator.isNotNullOrEmpty(enrollFrom) && Validator.isNotNullOrEmpty(enrollTo)) {
			create.between("DATE_FORMAT(enroll_start_time, '%Y-%m-%d')", enrollFrom, enrollTo);
			//create.between("DATE_FORMAT(enroll_end_time, '%Y-%m-%d')", enrollFrom, enrollTo);
		}
		
		//指定時間範圍內    活動時間
		if (Validator.isNotNullOrEmpty(startFrom) && Validator.isNotNullOrEmpty(endTo)) {
			create.between("DATE_FORMAT(activity_start_time, '%Y-%m-%d')", startFrom, endTo);
			//create.between("DATE_FORMAT(activity_end_time, '%Y-%m-%d')", startFrom, endTo);
		}
		
		//狀態
		if (Validator.isNotNullOrEmpty(status) && status != 0) {
			create.eq("status",status);
		}
		
		//排序
		create.orderBy("create_time", false);
		
		Page<TbActivityManage> list = activityService.selectPage(new Page<TbActivityManage>(null==page?0:page, 10), create);
		
		map.put("page", list);
		map.put("name", name);
		map.put("enrollFrom", enrollFrom);
		map.put("enrollTo", enrollTo);
		map.put("startFrom", startFrom);
		map.put("endTo", endTo);
		map.put("status", status);
		return "activity/list";
    }
	
	
	@RequestMapping(value="changeStatus/{id}/{status}",method=RequestMethod.GET)
	@ResponseBody
	/**
	 * 獲取狀態
	 * @param id
	 * @param status
	 * @return
	 */
	public Result changeStatus(@PathVariable("id") Integer id,@PathVariable("status") Integer status) {
		TbActivityManage activityManage = activityService.selectById(id);
		activityManage.setStatus(status);
		activityManage.setUpdateTime(new Date());
		//還差修改用戶名
		//activityManage.setUpdateUser(updateUser);
		if(activityManage.updateById()) {
			return success();
		}else {
			return error();
		}
	}
	
	
	/**
	 * 跳轉到表格頁面
	 * @param map
	 * @return
	 */
	@RequestMapping("form")
    public String form(Map<String,Object> map) {
		return "activity/form";
    }
	
	
	/**
	 * 根據ID查詢相關信息
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value="{id}/select",method=RequestMethod.GET)
    public String select(Map<String,Object> map,@PathVariable(required=true) Integer id) {	
		TbActivityManage activityManage = activityService.selectById(id);
		
		
		map.put("record", activityManage);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		String sDate=sdf.format(activityManage.getEnrollStartTime());
		map.put("enrollFrom", sDate);//报名开始时间
		
		sDate=sdf.format(activityManage.getEnrollEndTime());
		map.put("enrollTo", sDate);//报名結束时间
		

		sDate=sdf.format(activityManage.getActivityStartTime());
		map.put("startFrom", sDate);//活動開始时间
		

		sDate=sdf.format(activityManage.getActivityEndTime());
		map.put("startTo", sDate);//活動結束时间

		
		return "activity/edit";
    }
	
	
	
	/**
	 * 
	 * @param activityManage
	 * @param enrollFrom 开始报名时间
	 * @param enrollTo   结束报名时间
	 * @param startFrom  活动开始时间
	 * @param startTo    活动结束时间
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Result add(TbActivityManage activityManage,String enrollFrom,String enrollTo,String startFrom,String startTo){
		boolean result = false;
		
		//活動狀態
		activityManage.setStatus(activityManage.getStatus() == null ? 2 : activityManage.getStatus());
		
		//收費活動
		activityManage.setIsCost(activityManage.getIsCost() == null ? 2 : activityManage.getIsCost());
		//设置报名开始时间
		activityManage.setEnrollStartTime(com.feilong.core.date.DateUtil.toDate(enrollFrom,"yyyy-MM-dd HH:mm:ss"));
		//设置报名结束时间
		activityManage.setEnrollEndTime(com.feilong.core.date.DateUtil.toDate(enrollTo,"yyyy-MM-dd HH:mm:ss"));
		//设置活动开始时间
		activityManage.setActivityStartTime(com.feilong.core.date.DateUtil.toDate(startFrom,"yyyy-MM-dd HH:mm:ss"));
		//设置活动结束时间
		activityManage.setActivityEndTime(com.feilong.core.date.DateUtil.toDate(startTo,"yyyy-MM-dd HH:mm:ss"));
		
		
		if("".equals(activityManage.getCoverUrl())){
			activityManage.setCoverUrl(null);
		}
		if("".equals(activityManage.getEnclosureUrl())){
			activityManage.setEnclosureUrl(null);
		}
		
		
		if(Validator.isNotNullOrEmpty(activityManage .getId())){
			activityManage.setUpdateUser(getAccountName());
			activityManage.setUpdateTime(new Date());
			result = activityService.updateById(activityManage);
		}else{
			activityManage.setCreateUser(getAccountName());
			activityManage.setCreateTime(new Date());
			result = activityService.insert(activityManage);
		}
		if(result){
			return success();
		}else{
			return fail(EnumResult.ERROR);
		}
	}
	
	
	
	@RequestMapping(value="{id}/delete",method=RequestMethod.DELETE)
	@ResponseBody
    public Result delete(@PathVariable(required=true) Integer id) {	
		if(!activityService.deleteById(id)){
			return fail(EnumResult.ERROR);
		}
		return success();
    }	
	

}

