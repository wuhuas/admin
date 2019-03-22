package com.knowledge.admin.business.member.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.feilong.core.DatePattern;
import com.feilong.core.Validator;
import com.feilong.core.date.DateUtil;
import com.knowledge.admin.base.login.BaseAdminController;
import com.knowledge.admin.base.vo.MemberVo;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.business.member.entity.TbMember;
import com.knowledge.common.business.member.service.ITbMemberService;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;

/**
 * <p>
 * 用户信息管理  前端控制器
 * </p>
 *
 * @author francis
 * @since 2018-12-04
 */
@Controller
@RequestMapping("/member/")

public class TbMemberController extends BaseAdminController {

	@Autowired
	private ITbMemberService memberService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("listUI")
    public String listUI(@RequestParam Map<String,Object> map,Map<String,Object> result,Integer page) {
		Condition create = Condition.create();
		if (Validator.isNotNullOrEmpty(map.get("nickname"))) {
			create.like("nickname", map.get("nickname").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("school"))) {
			create.like("school", map.get("school").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("phone"))) {
			create.eq("phone", map.get("phone").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("email"))) {
			create.eq("email", map.get("email").toString());
		}
		
		
		//排序
		if(null!=map.get("sort") && "".equals(map.get("sort"))==false){
			create.orderBy(map.get("sort").toString(),"true".equals(map.get("isAsc").toString()));
		}else{
			create.orderBy("id",false);
		}
		
		
		Page<TbMember> list = memberService.selectPage(new Page<TbMember>(null==page?0:page, 10),create);
		map.put("page", list);
		result.putAll(map);
		return "member/list";
    }
	
	@RequestMapping(value="{id}/select",method=RequestMethod.GET)
    public String select(Map<String,Object> map,@PathVariable(required=true) Integer id) {	
		TbMember tbmember = memberService.selectById(id);
		map.put("record", tbmember);
		return "member/view";
    }	
	
	
	
	@SuppressWarnings("unchecked")
	@GetMapping(value="export")
    public Result export(@RequestParam Map<String,Object> map, HttpServletResponse response) {
		
		 // 设置默认文件名
        String filename = "用戶列表_" + DateUtil.toString(new Date(), DatePattern.COMMON_DATE_AND_TIME);
        // 设置响应头

        response.setCharacterEncoding("UTF-8");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "ISO8859-1") + ".xls");
        } catch (UnsupportedEncodingException e1) {
        	e1.printStackTrace();
            return fail(e1.getMessage(), "1");
        }
        response.setContentType("application/vnd.ms-excel");
		Condition create = Condition.create();
		if (Validator.isNotNullOrEmpty(map.get("nickname"))) {
			create.like("nickname", map.get("nickname").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("school"))) {
			create.like("school", map.get("school").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("phone"))) {
			create.eq("phone", map.get("phone").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("email"))) {
			create.eq("email", map.get("email").toString());
		}
		if(null!=map.get("sort")){
			create.orderBy(map.get("sort").toString(),"true".equals(map.get("isAsc").toString()));
		}else{
			create.orderBy("id",false);
		}
		//按照搜索結果導出Excel
		List<TbMember> members = memberService.selectList(create);
		List<MemberVo> array = JSONArray.parseArray(JSON.toJSONString(members), MemberVo.class);
		System.err.println(array.toString());
		ExportParams params = new ExportParams("用戶列表", "memberList");
        Workbook excel = ExcelExportUtil.exportExcel(params, MemberVo.class, array);
        try(OutputStream output = response.getOutputStream()) {
            excel.write(output);
            return success();
        } catch (IOException e) {
        	e.printStackTrace();
            return error();
        }
    }	
	
	
	
	
	
	
	
}
