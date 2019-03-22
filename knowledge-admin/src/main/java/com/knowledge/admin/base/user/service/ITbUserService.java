package com.knowledge.admin.base.user.service;

import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.knowledge.admin.base.user.entity.TbUser;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author francis
 */
public interface ITbUserService extends IService<TbUser> {
	
	public Page<TbUser> selectUserList(Page<TbUser> page,String name,String loginRole);
	
	public TbUser selectUserRole(Map<String, Object> parameter);
	
	public boolean insertAll(TbUser user);
	
	public boolean updateAll(TbUser user);
	
	public boolean delUser(Integer userId);
}
