package com.knowledge.common.business.message.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.knowledge.common.business.message.entity.TbMessageKeyword;
import com.knowledge.common.business.message.mapper.TbMessageKeywordDao;
import com.knowledge.common.business.message.service.ITbMessageKeywordService;

/**
 * <p>
 * 留言 关键字管理 服务实现类
 * </p>
 *
 * @author francis
 * @since 2018-12-03
 */
@Service
public class TbMessageKeywordServiceImpl extends ServiceImpl<TbMessageKeywordDao, TbMessageKeyword> implements ITbMessageKeywordService {
	@Autowired
	private TbMessageKeywordDao keywordDao;
	
	@Override
	public Set<String> selctAllKey() {
		return keywordDao.selectAllKey();
	}
	
}
