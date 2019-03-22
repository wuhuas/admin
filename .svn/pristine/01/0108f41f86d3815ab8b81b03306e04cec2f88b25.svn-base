package com.knowledge.admin.base.resource.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.knowledge.admin.base.resource.entity.TbResource;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author francis
 */
public interface TbResourceMapper extends BaseMapper<TbResource> {
	
	public List<TbResource> findResourcesByUserId(@Param(value="userId") int userId);
	
	public List<TbResource> findResourcesMenuByUserId(@Param(value="userId") int userId);
	
    public List<TbResource> queryResourceList(Map<String, Object> parameter);
    
    public List<TbResource> selectResourceList(Pagination page,Map<String, Object> parameter);
}