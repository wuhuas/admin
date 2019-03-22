package com.knowledge.common.business.principalEnroll.service;


import com.knowledge.common.business.principalEnroll.entity.TbPrincipalEnroll;

import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 校长报名表 服务类
 * </p>
 *
 * @author xiong
 * @since 2019-03-12
 */
public interface ITbPrincipalEnrollService extends IService<TbPrincipalEnroll> {
	
	/**
	 * 自定义分页查询
	 */
	Page<TbPrincipalEnroll> selectByPage(Page<TbPrincipalEnroll> page, Map<String, Object> map);
	
	
	
}
