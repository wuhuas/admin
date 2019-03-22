package com.knowledge.admin.business.periodical.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.knowledge.common.base.email.async.SendEmailAsync;
import com.knowledge.common.base.enums.EnumResult;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.business.member.entity.TbMember;
import com.knowledge.common.business.member.service.ITbMemberService;
import com.knowledge.common.business.periodical.entity.TbSelectedPeriodical;
import com.knowledge.common.business.periodical.service.ITbSelectedPeriodicalService;

/**
 * <p>
 * 精选期刊  前端控制器
 * </p>
 *
 * @author francis
 * @since 2018-11-28
 */
@Controller
@RequestMapping("/periodical/")

public class TbSelectedPeriodicalController extends BaseAdminController {

	private Logger log  =LoggerFactory.getLogger(TbSelectedPeriodicalController.class);
	
	@Autowired
	private ITbSelectedPeriodicalService periodicalService;
	
	@Autowired
	private SendEmailAsync sendEmailAsync;
	
	@Autowired
	private ITbMemberService memberService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("listUI")
    public String listUI(@RequestParam Map<String,Object> map,Map<String,Object> result,Integer page) {
		Condition create = Condition.create();
		if (Validator.isNotNullOrEmpty(map.get("name"))) {
			create.like("name", map.get("name").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("periodsNumber"))) {
			create.eq("periods_number", map.get("periodsNumber").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("releaseMonth"))) {
			create.eq("release_month", map.get("releaseMonth").toString());
		}
		
		if (Validator.isNotNullOrEmpty(map.get("from")) && Validator.isNotNullOrEmpty(map.get("to"))) {
			create.between("DATE_FORMAT(create_time, '%Y-%m-%d')", map.get("from").toString(), map.get("to").toString());
		}
		
		
		//排序
		if(null!=map.get("sort") && "".equals(map.get("sort"))==false){
			create.orderBy(map.get("sort").toString(),"true".equals(map.get("isAsc").toString()));
		}else{
			create.orderBy("id",false);
		}

		
		Page<TbSelectedPeriodical> list = periodicalService.selectPage(new Page<TbSelectedPeriodical>(null==page?0:page, 10),create);
		map.put("page", list);
		result.putAll(map);
		return "periodical/list";
    }
	
	@RequestMapping("form")
    public String form(Map<String,Object> map) {
		return "periodical/form";
    }
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Result add(TbSelectedPeriodical tbselectedperiodical){
		
		
		boolean result = false;
		if(Validator.isNotNullOrEmpty(tbselectedperiodical .getId())){
			
			
			tbselectedperiodical.setUpdateTime(new Date());
			tbselectedperiodical .setUpdateUser(getUserEntity().getAccountName());
			result = periodicalService.updateById(tbselectedperiodical);

		}else{
			tbselectedperiodical .setCreateUser(getUserEntity().getAccountName());
			result = periodicalService.insert(tbselectedperiodical);
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
		if(!periodicalService.deleteById(id)){
			return error();
		}
		return success();
    }	
	
	@RequestMapping(value="{id}/select",method=RequestMethod.GET)
    public String select(Map<String,Object> map,@PathVariable(required=true) Integer id) {	
		TbSelectedPeriodical tbselectedperiodical = periodicalService.selectById(id);
		map.put("record", tbselectedperiodical);
		return "periodical/edit";
    }
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="sendEmail",method=RequestMethod.GET)
	@ResponseBody
    public Result sendEmail(Map<String,Object> map,@RequestParam Integer id,@RequestParam String coverUrl) {	
		TbSelectedPeriodical tbselectedperiodical = periodicalService.selectById(id);
		if(Validator.isNullOrEmpty(tbselectedperiodical)) {
			return fail(EnumResult.ERROR_PARAMS);
		}
		//查詢所有訂閱的人
		List<TbMember> members = memberService.selectList(Condition.create().andNew("sub_email IS NOT NULL AND sub_email <> ''"));
		log.debug(">>>>>>>>>>>>>>>>>>>>>>發送郵件 準備進入異步！");
		sendEmailAsync.sendEleMagazine(tbselectedperiodical,coverUrl,members);
		log.debug(">>>>>>>>>>>>>>>>>>>>>>發送郵件 完成！");
		tbselectedperiodical.setSendEmail(1);
		tbselectedperiodical.setSendTime(new Date());
		if(periodicalService.updateById(tbselectedperiodical)) {
			return success();
		}
		return error();
    }
}
