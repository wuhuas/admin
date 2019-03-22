package com.knowledge.api.business.ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.Condition;
import com.knowledge.common.base.index.BaseController;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.business.ad.service.ITbAdService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ad/")
@Api(tags = { "广告中心" })
public class AdController extends BaseController {

	@Autowired
	private ITbAdService adService;

	@SuppressWarnings("unchecked")
	@GetMapping("getAd")
	@ApiOperation(value = "获取广告")
	public Result getAd(@RequestParam Integer type) {
		return json(adService
				.selectList(Condition.create().eq("type_id", type).orderBy("weight", false).orderBy("id", false)));
	}

}
