package com.knowledge.common.business.message.service;

import java.util.Set;

import com.baomidou.mybatisplus.service.IService;
import com.knowledge.common.business.message.entity.TbMessageKeyword;

/**
 * <p>
 * 留言 关键字管理 服务类
 * </p>
 *
 * @author francis
 * @since 2018-12-03
 */
public interface ITbMessageKeywordService extends IService<TbMessageKeyword> {

	Set<String> selctAllKey();
	
}
