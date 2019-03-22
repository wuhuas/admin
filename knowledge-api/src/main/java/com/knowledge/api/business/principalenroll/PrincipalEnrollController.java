package com.knowledge.api.business.principalenroll;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feilong.core.Validator;
import com.knowledge.common.base.index.BaseController;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.business.principalEnroll.entity.TbPrincipalEnroll;
import com.knowledge.common.business.principalEnroll.entity.TbPrincipalEnrollGroup;
import com.knowledge.common.business.principalEnroll.service.ITbPrincipalEnrollGroupService;
import com.knowledge.common.business.principalEnroll.service.ITbPrincipalEnrollOptionService;
import com.knowledge.common.business.principalEnroll.service.ITbPrincipalEnrollService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/principalenroll/")
@Api(tags = { "填写报名资料" })
public class PrincipalEnrollController extends BaseController{
	
	
	@Autowired
	private ITbPrincipalEnrollService    enrollService;
	
	@Autowired
	private ITbPrincipalEnrollOptionService  optionService;
	
	@Autowired
	private ITbPrincipalEnrollGroupService  groupService;
	
	
	@GetMapping("/findOption")
	@ApiOperation(value = "获取选项 ",notes="获取选项 ")
	public Result message(){
		return json(optionService.selectList(null));
	}
	
	
	@PostMapping("/save")
	@ApiOperation(value = "保存信息 ",notes="保存信息 ")
	public Result save(@RequestBody TbPrincipalEnroll enroll){
		
		if(Validator.isNotNullOrEmpty(enroll)) {
			enroll.setCreateTime(new Date());
			enrollService.insert(enroll);
			if(Validator.isNotNullOrEmpty(enroll.getGroups())){
				for(TbPrincipalEnrollGroup grop:enroll.getGroups()) {
					grop.setEnrollId(enroll.getId());
					groupService.insert(grop);
				}
			}
			return success();
		}
		return error();
	}

}
