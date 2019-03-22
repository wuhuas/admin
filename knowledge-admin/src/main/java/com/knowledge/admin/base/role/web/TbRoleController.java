package com.knowledge.admin.base.role.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.knowledge.admin.base.login.BaseAdminController;
import com.knowledge.admin.base.model.JSTreeEntity;
import com.knowledge.admin.base.resource.entity.TbResource;
import com.knowledge.admin.base.resource.service.ITbResourceService;
import com.knowledge.admin.base.role.entity.TbRole;
import com.knowledge.admin.base.role.service.ITbRoleService;
import com.knowledge.admin.base.user.entity.TbUser;
import com.knowledge.admin.base.util.TreeUtil;
import com.knowledge.common.base.enums.EnumResult;
import com.knowledge.common.base.model.Result;

/**
 * <p>
 * 角色表  前端控制器
 * </p>	
 *
 * @author francis
 */
@Controller
@RequestMapping("/role/")
public class TbRoleController extends BaseAdminController{
	
	@Autowired
	private ITbRoleService roleService;
	
	@Autowired
	private ITbResourceService resourceService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("listUI")
    public String listUI(Map<String,Object> map,Integer page) {
		Condition create = Condition.create();
		TbUser user = getUserEntity();
		if (!user.getAccountName().equalsIgnoreCase("administrator")) {
			create.ne("r_key", "administrator");
		}
		Page<TbRole> roles = roleService.selectPage(new Page<TbRole>(null==page?0:page, 10),create);
		map.put("page", roles);
		return "role/list";
    }
	
	@RequestMapping("form")
    public String form(Map<String,Object> map) {
		return "role/form";
    }
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Result add(TbRole role){
		if(role.getId()==null){
			role.setCreateTime(new Date(System.currentTimeMillis()));
			role.setUpdateTime(new Date(System.currentTimeMillis()));
		}else
		{
			role.setUpdateTime(new Date(System.currentTimeMillis()));
		}
		
		if(!roleService.insertOrUpdate(role)){
			return error();
		}
		return success();
	}
	
	@RequestMapping(value="{roleId}/delete",method=RequestMethod.DELETE)
	@ResponseBody
    public Result delete(@PathVariable(required=true) Integer roleId) {	
		if (roleId == 1) {
			return fail(EnumResult.ERROR_ILLEGAL_OPERATION);
		}
		if(!roleService.deleteRoleResource(roleId)){
			return error();
		}
		return success();
    }	
	
	@RequestMapping(value="{roleId}/select",method=RequestMethod.GET)
    public String select(Map<String,Object> map,@PathVariable(required=true) Integer roleId) {	
		TbRole role = roleService.selectById(roleId);
		map.put("role", role);
		return "role/edit";
    }	
	
	@RequestMapping(value="{roleId}/permission",method=RequestMethod.GET)
    public String permission(Map<String,Object> map,@PathVariable(required=true) Integer roleId) {	
		TbRole role = roleService.selectById(roleId);
		map.put("isHide", 0);
		map.put("roleId", roleId);
		map.put("role", getUserEntity().getRole().getKey());
		List<TbResource> resources = resourceService.queryResourceList(map);
		map.clear();
		List<JSTreeEntity> jstreeList = new TreeUtil().generateJSTree(resources);
		map.put("role", role);
		map.put("resources", jstreeList);
		return "role/permission";
    }	
	
	@RequestMapping(value="{roleId}/getPermission",method=RequestMethod.GET)
	@ResponseBody
    public Object getPermission(@PathVariable(required=true) Integer roleId) {
		Map<String,Object> map = new HashMap<>();
		map.put("isHide", 0);
		map.put("roleId", roleId);
		map.put("role", getUserEntity().getRole().getKey());
		List<TbResource> resources = resourceService.queryResourceList(map);
		List<JSTreeEntity> jstreeList = new TreeUtil().generateJSTree(resources);
		return jstreeList;
    }	
	
	@RequestMapping(value="savePermission",method = RequestMethod.POST)
	@ResponseBody
	public Result permission(int roleId, @RequestParam("resourceIds[]") List<Integer> resourceIds){
		Result bean = new Result();
		roleService.savePermission(roleId,resourceIds);
		return bean;
	}
}
