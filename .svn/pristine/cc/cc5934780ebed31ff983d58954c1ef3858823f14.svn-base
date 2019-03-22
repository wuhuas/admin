package com.knowledge.common.business.activity.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.knowledge.common.base.model.Pager;
import com.knowledge.common.business.activity.bean.ActivityAndDraft;
import com.knowledge.common.business.activity.entity.TbActivityEnrollManage;
import com.knowledge.common.business.activity.mapper.TbActivityEnrollManageDao;
import com.knowledge.common.business.activity.service.ITbActivityEnrollManageService;

/**
 * <p>
 * 活动报名管理 服务实现类
 * </p>
 *
 * @author xiong
 * @since 2018-11-14
 */
@Service
public class TbActivityEnrollManageServiceImpl extends ServiceImpl<TbActivityEnrollManageDao, TbActivityEnrollManage>
		implements ITbActivityEnrollManageService {

	@Autowired
	private TbActivityEnrollManageDao tbActivityEnrollManageDao;

	@Override
	public Page<TbActivityEnrollManage> selectByPage(Page<TbActivityEnrollManage> page, Map<String, Object> map) {
		page.setRecords(tbActivityEnrollManageDao.selectByPage(page, map));
		return page;
	}

	@Override
	public TbActivityEnrollManage selectAllById(Integer id) {
		return tbActivityEnrollManageDao.selectAllById(id);
	}

	@Override
	public Page<ActivityAndDraft> selectUserActivityAndDraft(Pager<ActivityAndDraft> pager, String createUser) {
		Page<ActivityAndDraft> page = pager.getPagePlus();
		Map<String, Object> map = pager.getMap();
		map.put("createUser", createUser);
		return page.setRecords(tbActivityEnrollManageDao.selectActivityAndDraft(page, map));
	}

	@Override
	public List<TbActivityEnrollManage> selectByPage(Map<String, Object> map) {
		return tbActivityEnrollManageDao.selectByPage(map);
	}
}
