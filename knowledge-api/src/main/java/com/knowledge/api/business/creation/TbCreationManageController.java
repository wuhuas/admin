package com.knowledge.api.business.creation;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.feilong.core.Validator;
import com.knowledge.common.base.index.BaseController;
import com.knowledge.common.base.model.Pager;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.business.creation.entity.TbCreationManage;
import com.knowledge.common.business.creation.service.ITbCreationManageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 创作天地管理  前端控制器
 * </p>
 *
 * @author jide
 * @since 2018-12-04
 */
@RestController
@Api(value="创作天地Controller",tags={"创作天地操作接口"})
public class TbCreationManageController extends BaseController {

	@Autowired
	private ITbCreationManageService creationService;
	
	@SuppressWarnings("unchecked")
	@PostMapping("/creation/list")
	@ApiOperation(value = "创作天地列表查询  ",notes="setTop 置顶   ;type作品类型（0=书法 1=绘画 2=摄影 3=多媒体 4=作文 5=其他）"
			+ "type 传值 1(书法/绘画)  2(摄影/多媒体)  3(作文) 4（其他）")
    public Result listUI(@RequestBody Pager<TbCreationManage> param) {
		Condition create = Condition.create();
		Map<String, Object> map = param.getMap();
		if(Validator.isNotNullOrEmpty(map.get("type"))) {
			if(map.get("type").equals(1)) {
			create.and("(type=0 or type=1)",null);
			    }
			if(map.get("type").equals(2)) {

				create.and("(type=2 or type=3)",null);
				}
			if(map.get("type").equals(3)) {
				create.eq("type",4);
				}
			if(map.get("type").equals(4)) {
				create.eq("type",5);
				}
		}
		if(Validator.isNotNullOrEmpty(map.get("setTop"))) {
			create.eq("recommend_works",1);
		}
		create.eq("status",1);
		create.orderBy("weight", false);
		
		Page<TbCreationManage> list = creationService.selectPage(param.getPagePlus(),create);
		return json(list);
    }
	
	
	@GetMapping("/creation/getCreationInfo")
	@ApiOperation(value = "创作天地详情信息 ",notes="创作天地详情信息")
    public Result getCreationInfo(@RequestParam Integer id) {
		TbCreationManage list = creationService.selectById(id);
		return json(list);
    }
	

	
	
}
