package com.knowledge.common.business.periodical.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.knowledge.common.base.model.Pager;
import com.knowledge.common.business.periodical.entity.TbSelectedPeriodical;
import com.knowledge.common.business.periodical.mapper.TbSelectedPeriodicalDao;
import com.knowledge.common.business.periodical.service.ITbSelectedPeriodicalService;

/**
 * <p>
 * 精选期刊 服务实现类
 * </p>
 *
 * @author francis
 * @since 2018-11-28
 */
@Service
public class TbSelectedPeriodicalServiceImpl extends ServiceImpl<TbSelectedPeriodicalDao, TbSelectedPeriodical> implements ITbSelectedPeriodicalService {

	@Autowired
	private TbSelectedPeriodicalDao periodicalDao;
	
	@Override
	public Page<TbSelectedPeriodical> selectPageAll(Pager<TbSelectedPeriodical> pager) {
		Page<TbSelectedPeriodical> page = pager.getPagePlus();
		Map<String, Object> map = pager.getMap();
		List<TbSelectedPeriodical> staff = periodicalDao.selectAll(page, map);
		return page.setRecords(staff);
	}

	@Override
	public List<TbSelectedPeriodical> selectTop() {
		
		return periodicalDao.selectTop();
	}

	@Override
	public void updateState() {
		periodicalDao.updateState();
		
	}
	
}
