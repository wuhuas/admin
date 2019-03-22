package com.knowledge.api.business.schoolcalendar;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.Condition;
import com.feilong.core.DatePattern;
import com.feilong.core.date.DateUtil;
import com.knowledge.common.base.index.BaseController;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.business.school.service.ITbSchoolCalendarRecommendService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/schoolcalendar/")
@Api(tags = { "校历推荐" })
public class SchoolCalendarController extends BaseController {

	@Autowired
	private ITbSchoolCalendarRecommendService schoolCalendarRecommendService;

	@SuppressWarnings("unchecked")
	@GetMapping("getSchoolCalendar")
	@ApiOperation("校历推荐")
	public Result getSchoolCalendar() {
		return json(schoolCalendarRecommendService.selectList(Condition.create().eq("status", 1)
				.le("activity_date", DateUtil.toString(new Date(), DatePattern.COMMON_DATE))
				.orderBy("activity_date", false)));
	}
	
	@GetMapping("getSchoolCalendarById")
	@ApiOperation("校历推荐详情")
	public Result getSchoolCalendarById(@RequestParam Integer id) {
		return json(schoolCalendarRecommendService.selectById(id));
	}

}
