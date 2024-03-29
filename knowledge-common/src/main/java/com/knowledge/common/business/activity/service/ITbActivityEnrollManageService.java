package com.knowledge.common.business.activity.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.knowledge.common.base.model.Pager;
import com.knowledge.common.business.activity.bean.ActivityAndDraft;
import com.knowledge.common.business.activity.entity.TbActivityEnrollManage;

/**
 * <p>
 * 活动报名管理 服务类
 * </p>
 *
 * @author xiong
 * @since 2018-11-14
 */
public interface ITbActivityEnrollManageService extends IService<TbActivityEnrollManage> {
	Page<TbActivityEnrollManage> selectByPage(Page<TbActivityEnrollManage> page, Map<String, Object> map);

	TbActivityEnrollManage selectAllById(Integer id);

	/**
	 * 根据用户名查询报名活动和投稿
	 * 
	 * @param createUser
	 * @return
	 */
	Page<ActivityAndDraft> selectUserActivityAndDraft(Pager<ActivityAndDraft> pager, String createUser);
	
	/**
	 * 查詢所有報名信息（導出excel 使用）
	 * @param map
	 * @return
	 */
	List<TbActivityEnrollManage> selectByPage(Map<String, Object> map);
}
