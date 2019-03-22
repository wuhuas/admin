package com.knowledge.api.business.periodicaltips;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.feilong.core.Validator;
import com.knowledge.common.base.index.BaseController;
import com.knowledge.common.base.model.Pager;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.business.periodicaltips.entity.TbPeriodicalTips;
import com.knowledge.common.business.periodicaltips.service.ITbPeriodicalTipsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/periodicaltips/")
@Api(tags = { "成长寄语" })
public class PeriodicalTipsController extends BaseController {

	@Autowired
	private ITbPeriodicalTipsService periodicalTipsService;

	@SuppressWarnings("unchecked")
	@PostMapping("getPeriodicalTips")
	@ApiOperation("查询成长寄语")
	public Result getPeriodicalTips(@RequestBody Pager<TbPeriodicalTips> pager) {
		Page<TbPeriodicalTips> page = periodicalTipsService.selectPage(pager.getPagePlus(), Condition.create()
				.eq(Validator.isNotNullOrEmpty(pager.getMap())
						&& Validator.isNotNullOrEmpty(pager.getMap().get("type")), "type", pager.getMap().get("type"))
				.eq("status", 1).orderBy("weight", false).orderBy("id", false));
		return json(page);
	}
}
