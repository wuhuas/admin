package com.knowledge.admin.business.periodical.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.feilong.core.Validator;
import com.feilong.core.date.DateUtil;
import com.knowledge.admin.base.login.BaseAdminController;
import com.knowledge.common.base.email.async.SendEmailAsync;
import com.knowledge.common.base.enums.EnumResult;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.business.periodical.entity.TbMagazineOrder;
import com.knowledge.common.business.periodical.service.ITbMagazineOrderService;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;

/**
 * <p>
 * 杂志订单  前端控制器
 * </p>
 *
 * @author francis
 * @since 2018-11-28
 */
@Controller
@RequestMapping("/magazineorder/")
public class TbMagazineOrderController extends BaseAdminController {

	private Logger log  =LoggerFactory.getLogger(TbSelectedPeriodicalController.class);
	
	@Autowired
	private ITbMagazineOrderService magazineOrderService;
	
	@Autowired
	private SendEmailAsync sendEmailAsync;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("listUI")
    public String listUI(@RequestParam Map<String,Object> map,Map<String,Object> result,Integer page) {
		Condition create = Condition.create();
		if (Validator.isNotNullOrEmpty(map.get("name"))) {
			create.like("name", map.get("name").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("school"))) {
			create.eq("school", map.get("school").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("phone"))) {
			create.eq("phone", map.get("phone").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("email"))) {
			create.eq("email", map.get("email").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("status")) && !map.get("status").equals("0")) {
			create.eq("status", map.get("status").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("type")) && !map.get("type").equals("0")) {
			create.eq("type", map.get("type").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("from")) && Validator.isNotNullOrEmpty(map.get("to"))) {
			create.between("DATE_FORMAT(reserve_time, '%Y-%m-%d')", map.get("from").toString(), map.get("to").toString());
		}
		
		
		//排序
		if(null!=map.get("sort") && "".equals(map.get("sort"))==false){
			create.orderBy(map.get("sort").toString(),"true".equals(map.get("isAsc").toString()));
		}else{
			create.orderBy("id",false);
		}
		
		
		Page<TbMagazineOrder> list = magazineOrderService.selectPage(new Page<TbMagazineOrder>(null==page?0:page, 10),create);
		map.put("page", list);
		result.putAll(map);
		return "magazineorder/list";
    }
	
	/**
	 * 審核
	 * @param id
	 * @return
	 */
	@RequestMapping(value="{id}/{status}/review",method=RequestMethod.GET)
	@ResponseBody
    public Result review(@PathVariable(required=true) Integer id,@PathVariable(required=true) Integer status) {
		TbMagazineOrder tbmagazineorder = magazineOrderService.selectById(id);
		if(Validator.isNullOrEmpty(tbmagazineorder)){
			return fail(EnumResult.ERROR_PARAMS);
		}
		tbmagazineorder.setStatus(status);
		tbmagazineorder.setExamineTime(new Date());
		tbmagazineorder.setExamineName(getUserEntity().getAccountName());
		if (magazineOrderService.updateById(tbmagazineorder)) {
			
			//发送成功邮件给用户
			log.debug(">>>>>>>>>>>>>>>>>>>>>>發送郵件 準備進入異步！");
			sendEmailAsync.sendMagazineStatusByEmail(tbmagazineorder.getEmail(),tbmagazineorder.getName(),tbmagazineorder.getType(), status == 1);
			log.debug(">>>>>>>>>>>>>>>>>>>>>>發送郵件 完成！");
			
			return success();

			
		}
		
		
		return error();
    }	
	
	@RequestMapping(value="{id}/select",method=RequestMethod.GET)
    public String select(Map<String,Object> map,@PathVariable(required=true) Integer id) {	
		TbMagazineOrder tbmagazineorder = magazineOrderService.selectById(id);
		
		map.put("record", tbmagazineorder);
		return "magazineorder/view";
    }
	
	@SuppressWarnings("unchecked")
	@GetMapping("export")
    public Result export(@RequestParam Map<String,Object> map, HttpServletResponse response) {
        // 设置默认文件名
        String filename = "紙質雜誌訂閱列表_" + DateUtil.toString(new Date(), DatePattern.COMMON_DATE_AND_TIME);
        // 设置响应头

        response.setCharacterEncoding("UTF-8");
        try {
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String(filename.getBytes(), "ISO8859-1") + ".xls");
        } catch (UnsupportedEncodingException e1) {
        	e1.printStackTrace();
            return fail(e1.getMessage(), "1");
        }
        response.setContentType("application/vnd.ms-excel");
        // 设置查询条件
        Condition create = Condition.create();
		if (Validator.isNotNullOrEmpty(map.get("name"))) {
			create.like("name", map.get("name").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("school"))) {
			create.eq("school", map.get("school").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("phone"))) {
			create.eq("phone", map.get("phone").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("email"))) {
			create.eq("email", map.get("email").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("status")) && !map.get("status").equals("0")) {
			create.eq("status", map.get("status").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("type")) && !map.get("type").equals("0")) {
			create.eq("type", map.get("type").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("from")) && Validator.isNotNullOrEmpty(map.get("to"))) {
			create.between("DATE_FORMAT(reserve_time, '%Y-%m-%d')", map.get("from").toString(), map.get("to").toString());
		}
		if(Validator.isNotNullOrEmpty(map.get("sort"))){
			create.orderBy(map.get("sort").toString(),"true".equals(map.get("isAsc").toString()));
		}else{
			create.orderBy("id",false);
		}
		List<TbMagazineOrder> list = magazineOrderService.selectList(create);
		for (TbMagazineOrder magazineOrder : list) {
			magazineOrder.setSubYears(magazineOrder.getSubscribeYear()+" 年");
			/*if (Validator.isNullOrEmpty(magazineOrder.getSubscribeYear()) ) {
				magazineOrder.setSubYears(" ");
			}else if(magazineOrder.getSubscribeYear() == 0) {
				Date startTime = magazineOrder.getStartTime();
				Date endTime = magazineOrder.getEndTime();
				magazineOrder.setSubYears(DateUtil.toString(startTime, "yyyy-MM")+" ~ "+DateUtil.toString(endTime, "yyyy-MM"));
			}else {
				break;
			}*/
		}
        ExportParams params = new ExportParams("紙質雜誌訂閱列表", "magazineOrderList");
        Workbook excel = ExcelExportUtil.exportExcel(params, TbMagazineOrder.class, list);
        try(OutputStream output = response.getOutputStream()) {
            excel.write(output);
            return success();
        } catch (IOException e) {
        	e.printStackTrace();
            return error();
        }
    }
}
