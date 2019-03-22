package com.knowledge.admin.business.activity.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.feilong.core.DatePattern;
import com.feilong.core.date.DateUtil;
import com.knowledge.admin.base.login.BaseAdminController;
import com.knowledge.admin.base.vo.ActivityEnrollVo;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.business.activity.entity.TbActivityEnrollManage;
import com.knowledge.common.business.activity.service.ITbActivityEnrollManageService;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;

/**
 * <p>
 * 活动报名管理  前端控制器
 * </p>
 *
 * @author xiong
 * @since 2018-11-14
 */
@Controller
@RequestMapping("/activityEnroll/")

public class TbActivityEnrollManageController extends BaseAdminController {

	//活動報名管理
	@Autowired
	private ITbActivityEnrollManageService activityEnrollService;
	
	@RequestMapping("listUI")
    public String listUI(@RequestParam Map<String,Object> params,Map<String,Object> result,Integer page) {
		
		Condition create = Condition.create();
		create.orderBy("create_time",false);
		
		Page<TbActivityEnrollManage> list = activityEnrollService.selectByPage(new Page<TbActivityEnrollManage>(null==page?0:page, 10),params);
		
		
		
		//资讯类别列表
		params.put("page", list);
		result.putAll(params);
		return "activityEnroll/list";
    }
	
	
	/**
	 * 獲取狀態
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping(value="changeStatus/{id}/{status}",method=RequestMethod.GET)
	@ResponseBody
	public Result changeStatus(@PathVariable("id") Integer id,@PathVariable("status") Integer status) {
		TbActivityEnrollManage tbActivityEnrollManage = activityEnrollService.selectById(id);
		tbActivityEnrollManage.setStatus(status);
		tbActivityEnrollManage.setExamineName(getAccountName());
		tbActivityEnrollManage.setExamineTime(new Date());
		tbActivityEnrollManage.setUpdateTime(new Date());
		
		
		if(tbActivityEnrollManage.updateById()) {
			return success();
		}else {
			return error();
		}
	}
	
	/**
	 * 根據ID查詢相關信息
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value="{id}/select",method=RequestMethod.GET)
    public String select(Map<String,Object> map,@PathVariable(required=true) Integer id) {	
		TbActivityEnrollManage tbActivityEnrollManage = activityEnrollService.selectAllById(id);
		map.put("record", tbActivityEnrollManage);
		System.err.println(map.toString());
		return "activityEnroll/see";
    }
	
	@GetMapping("export")
    public Result export(@RequestParam Map<String,Object> map, HttpServletResponse response) {
        // 设置默认文件名
        String filename = "活動報名列表_" + DateUtil.toString(new Date(), DatePattern.COMMON_DATE_AND_TIME);
        // 设置响应头

        response.setCharacterEncoding("UTF-8");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "ISO8859-1") + ".xls");
        } catch (UnsupportedEncodingException e1) {
        	e1.printStackTrace();
            return fail(e1.getMessage(), "1");
        }
        response.setContentType("application/vnd.ms-excel");
        // 设置查询条件
		List<TbActivityEnrollManage> list = activityEnrollService.selectByPage(map);
		List<ActivityEnrollVo> enrollVos = new ArrayList<>();
		for (TbActivityEnrollManage ae : list) {
			ActivityEnrollVo enrollVo = new ActivityEnrollVo();
			enrollVo.setActivityName(ae.getActivityName() == null ? "" : ae.getActivityName());
			enrollVo.setActivityTime(DateUtil.toString(ae.getActivityStartTime(), "yyyy-MM-dd HH:mm")+" ~ "+DateUtil.toString(ae.getActivityEndTime(), "yyyy-MM-dd HH:mm"));
			enrollVo.setAnnualClass(ae.getAnnualClass() == null ? "" : ae.getAnnualClass());
			enrollVo.setEmail(ae.getEmail() == null ? "" : ae.getEmail());
			enrollVo.setEnrollTime(DateUtil.toString(ae.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			enrollVo.setIsCost(ae.getIsCost());
			enrollVo.setName(ae.getName() == null ? "" : ae.getName());
			enrollVo.setPhone(ae.getPhone() == null ? "" : ae.getPhone());
			enrollVo.setSchool(ae.getSchool() == null ? "" : ae.getSchool());
			enrollVo.setStatus(ae.getStatus());
			enrollVo.setPayVoucherUrl(ae.getPayVoucherUrl() == null ? "" : ae.getPayVoucherUrl());
			enrollVos.add(enrollVo);
		}
		System.err.println(enrollVos.toString());
        ExportParams params = new ExportParams("活動報名列表", "activityEnrollList");
        Workbook excel = ExcelExportUtil.exportExcel(params, ActivityEnrollVo.class, enrollVos);
        try(OutputStream output = response.getOutputStream()) {
            excel.write(output);
            return success();
        } catch (IOException e) {
        	e.printStackTrace();
            return error();
        }
    }
	
}



