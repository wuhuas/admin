package com.knowledge.common.business.news.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.knowledge.common.business.creation.entity.TbCreationManage;
import com.knowledge.common.business.creation.service.ITbCreationManageService;
import com.knowledge.common.business.news.entity.NewsVo;
import com.knowledge.common.business.news.entity.TbNewsInfo;
import com.knowledge.common.business.news.mapper.TbNewsInfoDao;
import com.knowledge.common.business.news.service.ITbNewsInfoService;

/**
 * <p>
 * 资讯信息详情 服务实现类
 * </p>
 *
 * @author francis
 * @since 2018-11-09
 */
@Service
public class TbNewsInfoServiceImpl extends ServiceImpl<TbNewsInfoDao, TbNewsInfo> implements ITbNewsInfoService {

	@Autowired
	private TbNewsInfoDao newsInfoDao;

	@Autowired
	private ITbCreationManageService creationServer;
	

	@Override
	public Page<TbNewsInfo> selectByPage(Page<TbNewsInfo> page, Map<String, Object> map) {
		page.setRecords(newsInfoDao.selectByPage(page, map));
		return page;
	}

	@Override
	public Page<NewsVo> selectAllPage(Page<NewsVo> page, Map<String, Object> map) {
		List<NewsVo> newsVos = newsInfoDao.selectAllPage(page, map);
		for (NewsVo news : newsVos) {
			if (news.getType().equals(1)) {
				TbNewsInfo info = newsInfoDao.selectByIdnews(news.getId());
				news.setTypeName(info.getCategoryName());
			}
			if (news.getType().equals(4)) {
				TbCreationManage creation = creationServer.selectById(news.getId());
				news.setTypeName(creation.getType().toString());
			}

			
		}

		page.setRecords(newsVos);
		return page;
	}

	@Override
	public List<TbNewsInfo> selectByTop(Wrapper<TbNewsInfo> wrapper) {
		return newsInfoDao.selectByTop(wrapper);
	}

	
	/**
	 * 查询类别的资讯总数
	 */
	@Override
	public List<Map<String, String>> selectCategoryCount() {
		// TODO Auto-generated method stub
		return newsInfoDao.selectCategoryCount();
	}


	

}

