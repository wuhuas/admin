package com.knowledge.admin.base.role.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.knowledge.admin.base.role.entity.TbRole;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author francis
 */
public interface ITbRoleService extends IService<TbRole> {
	
	public void savePermission(Integer roleId,List<Integer> resourceIds);
	
	public boolean deleteRoleResource(Integer roleId);
}
