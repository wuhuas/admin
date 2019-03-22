package com.knowledge.common.business.activity.service.impl;

import com.knowledge.common.business.activity.entity.TbPastActivityManage;
import com.knowledge.common.business.activity.mapper.TbPastActivityManageDao;
import com.knowledge.common.business.activity.service.ITbPastActivityManageService;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 以往活动管理 服务实现类
 * </p>
 *
 * @author xiong
 * @since 2018-11-14
 */
@Service
public class TbPastActivityManageServiceImpl extends ServiceImpl<TbPastActivityManageDao, TbPastActivityManage> implements ITbPastActivityManageService {

	@Autowired
	private TbPastActivityManageDao pastActivityManageDao;
	
	@Override
	public Page<TbPastActivityManage> selectByPage(Page<TbPastActivityManage> page,
			Wrapper<TbPastActivityManage> wrapper) {
		page.setRecords(pastActivityManageDao.selectByPage(page, wrapper));
		return page;
	}
	
}
