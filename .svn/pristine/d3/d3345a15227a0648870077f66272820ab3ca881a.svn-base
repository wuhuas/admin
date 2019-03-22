package com.knowledge.common.business.periodical.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.knowledge.common.base.model.Pager;
import com.knowledge.common.business.periodical.entity.TbSelectedPeriodical;

/**
 * <p>
 * 精选期刊 服务类
 * </p>
 *
 * @author francis
 * @since 2018-11-28
 */
public interface ITbSelectedPeriodicalService extends IService<TbSelectedPeriodical> {

	Page<TbSelectedPeriodical> selectPageAll(Pager<TbSelectedPeriodical> pagePlus);

	List<TbSelectedPeriodical> selectTop();

	void updateState();
	
}
