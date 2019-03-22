package com.knowledge.admin.base.resource.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.feilong.core.bean.ConvertUtil;
import com.knowledge.admin.base.resource.entity.TbResource;
import com.knowledge.admin.base.resource.mapper.TbResourceMapper;
import com.knowledge.admin.base.resource.service.ITbResourceService;
import com.knowledge.admin.base.resourcerole.mapper.TbResourcesRoleMapper;
import com.knowledge.admin.base.util.TreeUtil;

/**
 * <p>
 * 资源表  服务实现类
 * </p>
 *
 * @author francis
 */
@Service
public class TbResourceServiceImpl extends ServiceImpl<TbResourceMapper, TbResource> implements ITbResourceService {

	@Autowired
	private TbResourceMapper resourceMapper;
	
	@Autowired
	private TbResourcesRoleMapper resourceRoleMapper;
	
	@Override
	public List<TbResource> findResourcesByUserId(int userId) {
		return resourceMapper.findResourcesByUserId(userId);
	}

	@Override
	public List<TbResource> findResourcesMenuByUserId(int userId) {
		List<TbResource> resources = resourceMapper.findResourcesMenuByUserId(userId);
		List<TbResource> treeMenuList =new TreeUtil().treeMenuList(resources, null);
		return treeMenuList;
	}

	@Override
	public List<TbResource> queryResourceList(Map<String, Object> parameter) {
		return resourceMapper.queryResourceList(parameter);
	}

	@Override
	public Page<TbResource> selectResourceList(Page<TbResource> page, Map<String, Object> parameter) {
		page.setRecords(resourceMapper.selectResourceList(page,parameter));
		return page;
	}

	@Override
	@Transactional
	public void deleteRoleResource(int resourceId) {
		resourceRoleMapper.deleteByMap(ConvertUtil.toMap("s_id",(Object)resourceId));
		resourceMapper.deleteById(resourceId);
		
	}
}
