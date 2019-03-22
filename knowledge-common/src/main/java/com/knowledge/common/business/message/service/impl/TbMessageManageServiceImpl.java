package com.knowledge.common.business.message.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.feilong.core.Validator;
import com.knowledge.common.base.util.SensitiveWordUtil;
import com.knowledge.common.business.message.entity.TbMessageManage;
import com.knowledge.common.business.message.mapper.TbMessageManageDao;
import com.knowledge.common.business.message.service.ITbMessageKeywordService;
import com.knowledge.common.business.message.service.ITbMessageManageService;
import com.knowledge.common.business.news.entity.TbNewsInfo;
import com.knowledge.common.business.news.service.ITbNewsInfoService;

/**
 * <p>
 * 留言管理 服务实现类
 * </p>
 *
 * @author francis
 * @since 2018-12-03
 */
@Service
public class TbMessageManageServiceImpl extends ServiceImpl<TbMessageManageDao, TbMessageManage> implements ITbMessageManageService {

	@Autowired
	private TbMessageManageDao messageManageDao;
	
	@Autowired
	private ITbNewsInfoService newsService;
	
	@Autowired
	private ITbMessageKeywordService  keyWordService;
	
	@Override
	public Page<TbMessageManage> selectByPage(Page<TbMessageManage> page, Map<String, Object> map) {
		page.setRecords(messageManageDao.selectByPage(page, map));
		return page;
	}

	@Override
	public boolean insertMessage(TbMessageManage tbmessagemanage) {
	    // 敏感词替换
		
		
		SensitiveWordUtil.init(keyWordService.selctAllKey());
	  // String content= filter.replaceSensitiveWord(tbmessagemanage.getContent(), SensitivewordFilter.minMatchType, "*");
		  String content = SensitiveWordUtil.replaceSensitiveWord(tbmessagemanage.getContent(), '*',1);
		tbmessagemanage.setContent(content);
	   tbmessagemanage.setMessageTime(new Date());
	  if( tbmessagemanage.insert()) {
		  TbNewsInfo info=  newsService.selectById(tbmessagemanage.getInfoId());
		  if(Validator.isNotNullOrEmpty(info)) {
			  info.setCommentTotal(info.getCommentTotal()+1);
			  info.updateById();
		  }
        return true;
	  };
	  return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(Integer id) {
		TbMessageManage manage = selectById(id);
		if(Validator.isNotNullOrEmpty(manage)) {
			deleteById(id);
			//刪除留言后 重新統計留言量
			TbNewsInfo newsInfo = newsService.selectById(manage.getInfoId());
			if(Validator.isNotNullOrEmpty(newsInfo)) {
				newsInfo.setCommentTotal(selectCount(Condition.create().eq("info_id", manage.getInfoId())));
				newsService.updateById(newsInfo);
			}
		}
	}
	
}
