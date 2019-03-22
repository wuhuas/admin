package com.knowledge.api.business.news;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.Condition;
import com.knowledge.common.base.index.BaseController;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.business.news.entity.TbNewsCategory;
import com.knowledge.common.business.news.service.ITbNewsCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 资讯类别  前端控制器
 * </p>
 *
 * @author francis
 * @since 2018-11-09
 */
@RestController

@Api(value="资讯类型Controller",tags={"资讯类型操作接口"})
public class TbNewsCategoryController extends BaseController {

	@Autowired
	private ITbNewsCategoryService newsService;
	
	@SuppressWarnings("unchecked")
	@PostMapping("/newscategory/list")
	@ApiOperation(value = "咨询类型列表",notes="咨询类型列表")
    public Result listUI() {
		Condition create = Condition.create();
		create.andNew("status=1");
		create.orderBy("weight", false);
		List<TbNewsCategory> list = newsService.selectList(create);
		return json(list);
    }
	
		

}
