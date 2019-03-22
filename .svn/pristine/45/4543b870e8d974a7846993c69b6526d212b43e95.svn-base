package com.knowledge.admin.base.resource.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.feilong.core.bean.ConvertUtil;
import com.knowledge.admin.base.login.BaseAdminController;
import com.knowledge.admin.base.model.Select2Entity;
import com.knowledge.admin.base.resource.entity.TbResource;
import com.knowledge.admin.base.resource.service.ITbResourceService;
import com.knowledge.admin.base.util.TreeUtil;
import com.knowledge.common.base.enums.EnumResult;
import com.knowledge.common.base.model.Result;


/**
 * <p>
 * 资源表  前端控制器
 * </p>
 *
 * @author francis
 */
@Controller
@RequestMapping("/resource/")
public class TbResourceController extends BaseAdminController{
	
	@Autowired
	private ITbResourceService resourceService;
	
	@RequestMapping("listUI")
    public String listUI(Map<String,Object> map,Integer page,@RequestParam(defaultValue="") String name, Integer type) {
		Page<TbResource> resources = resourceService.selectResourceList(new Page<TbResource>(null==page?0:page, 10),ConvertUtil.toMap("name",(Object)name,"type",(Object)type));
		map.put("page", resources);
		map.put("name", name);
		map.put("type", type);
		return "resource/list";
    }
	
	@RequestMapping("form")
    public String form(Map<String,Object> map) {
		List<TbResource> list = resourceService.selectByMap(null);
		List<Select2Entity> select2List = new TreeUtil().getSelectTree(list, null);
		map.put("resources", select2List);
		return "resource/form";
    }
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Result add(TbResource resource){
		Result bean = new Result();
		resource.setParentId(resource.getParentId()==0?null:resource.getParentId());
		if(resource.getId()==null){
			resource.setCreateTime(new Date(System.currentTimeMillis()));
			resource.setUpdateTime(new Date(System.currentTimeMillis()));
		}else
		{
			resource.setUpdateTime(new Date(System.currentTimeMillis()));
		}
		
		if(!resourceService.insertOrUpdate(resource)){
			return error();
		}
		return bean;
	}
	
	@RequestMapping(value="{id}/delete",method=RequestMethod.DELETE)
	@ResponseBody
    public Result delete(@PathVariable(required=true) Integer id) {	
		List<TbResource> childrens = resourceService.selectByMap(ConvertUtil.toMap("s_parent_id",(Object)id));
		if(childrens!=null && childrens.size()>0){
			return fail(EnumResult.ERROR_RESOURCE_DELETE);
		}else{
			resourceService.deleteRoleResource(id);
		}
		return success();
    }	
	
	@RequestMapping(value="{id}/select",method=RequestMethod.GET)
    public String select(Map<String,Object> map,@PathVariable(required=true) Integer id) {	
		TbResource resource = resourceService.selectById(id);
		List<TbResource> list = resourceService.selectByMap(null);
		List<Select2Entity> select2List = new TreeUtil().getSelectTree(list, null);
		map.put("resources", select2List);
		map.put("resource", resource);
		return "resource/edit";
    }	
	
	@RequestMapping("icon")
    public String icon() {
		return "resource/icon";
    }
	
}
