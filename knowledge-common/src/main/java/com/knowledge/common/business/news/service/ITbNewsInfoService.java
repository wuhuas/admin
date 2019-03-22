package com.knowledge.common.business.news.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.knowledge.common.business.news.entity.NewsVo;
import com.knowledge.common.business.news.entity.TbNewsInfo;

/**
 * <p>
 * 资讯信息详情 服务类
 * </p>
 *
 * @author francis
 * @since 2018-11-09
 */
public interface ITbNewsInfoService extends IService<TbNewsInfo> {
	
	Page<TbNewsInfo> selectByPage(Page<TbNewsInfo> page,Map<String, Object> map);
	
	Page<NewsVo> selectAllPage(Page<NewsVo> page,Map<String, Object> map);
	
	List<TbNewsInfo> selectByTop(Wrapper<TbNewsInfo> wrapper);
	
	List<Map<String,String>> selectCategoryCount();

}
