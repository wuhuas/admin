package com.knowledge.common.business.news.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.knowledge.common.business.news.entity.NewsVo;
import com.knowledge.common.business.news.entity.TbNewsInfo;

/**
 * <p>
  * 资讯信息详情 Mapper 接口
 * </p>
 *
 * @author francis
 * @since 2018-11-09
 */
public interface TbNewsInfoDao extends BaseMapper<TbNewsInfo> {

	
	/**
	 * 自定连表分页查询
	 * @param page
	 * @param map
	 * @return
	 */
	List<TbNewsInfo> selectByPage(Page<TbNewsInfo> page,Map<String, Object> map);
	
	List<NewsVo> selectAllPage(Page<NewsVo> page,Map<String, Object> map);
	
	List<TbNewsInfo> selectByTop(@Param("ew") Wrapper<TbNewsInfo> wrapper);
	
	TbNewsInfo selectByIdnews(@Param("id") Integer id);

	List<Map<String,String>> selectCategoryCount();
	
}