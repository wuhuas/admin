package com.knowledge.common.business.principalEnroll.service.impl;

import com.knowledge.common.business.activity.entity.TbActivityEnrollManage;
import com.knowledge.common.business.activity.entity.TbPastActivityManage;
import com.knowledge.common.business.principalEnroll.entity.TbPrincipalEnroll;
import com.knowledge.common.business.principalEnroll.mapper.TbPrincipalEnrollDao;
import com.knowledge.common.business.principalEnroll.service.ITbPrincipalEnrollService;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 校长报名表 服务实现类
 * </p>
 *
 * @author xiong
 * @since 2019-03-12
 */
@Service
public class TbPrincipalEnrollServiceImpl extends ServiceImpl<TbPrincipalEnrollDao, TbPrincipalEnroll> implements ITbPrincipalEnrollService {

	@Autowired
	private TbPrincipalEnrollDao tbPrincipalEnrollDao;
	
	/**
	 * 自定义分页查询
	 */
	@Override
	public Page<TbPrincipalEnroll> selectByPage(Page<TbPrincipalEnroll> page, Map<String, Object> map) {
		page.setRecords(tbPrincipalEnrollDao.selectByPage(page, map));
		return page;
	}
	
}
