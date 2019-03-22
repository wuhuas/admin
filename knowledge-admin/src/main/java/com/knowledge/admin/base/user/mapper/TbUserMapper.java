package com.knowledge.admin.base.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.knowledge.admin.base.user.entity.TbUser;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author francis
 */
public interface TbUserMapper extends BaseMapper<TbUser> {
	
	public List<TbUser> selectUserList(Pagination page,@Param("name") String name,@Param("loginRole") String loginRole);
	
	public TbUser selectUserRole(Map<String, Object> parameter);
}