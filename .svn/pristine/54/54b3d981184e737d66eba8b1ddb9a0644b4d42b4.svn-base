package com.knowledge.common.business.member.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.knowledge.common.base.model.ReportVo;
import com.knowledge.common.business.member.entity.TbMember;

/**
 * <p>
 * 用户信息管理 服务类
 * </p>
 *
 * @author francis
 * @since 2018-11-30
 */
public interface ITbMemberService extends IService<TbMember> {

	boolean insertUser(TbMember user);

	String login(TbMember member);

	boolean checkTimeOut(TbMember member);

	boolean forgetPass(TbMember tbmember);

	boolean checkPassTimeOut(TbMember member);
	
	/**
	 * 統計用戶量
	 * @param time
	 * @return
	 */
	List<ReportVo> selectCountByDate(String time);
	
	/**
	 * 統計訂閱量
	 * @param time
	 * @return
	 */
	List<ReportVo> selectSubCountByDate(String time);

	void sendMail(TbMember member, Integer type);
	
}
