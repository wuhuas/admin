package com.knowledge.common.business.member.mapper;

import com.knowledge.common.base.model.ReportVo;
import com.knowledge.common.business.member.entity.TbMember;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 用户信息管理 Mapper 接口
 * </p>
 *
 * @author francis
 * @since 2018-11-30
 */
public interface TbMemberDao extends BaseMapper<TbMember> {

	@Select("SELECT COUNT(*) as total,DATE_FORMAT(register_time, '%Y-%m-%d') as date FROM tb_member WHERE ( DATE_FORMAT(register_time, '%Y-%m')=#{time}) GROUP BY DATE_FORMAT(register_time, '%Y-%m-%d')")
	List<ReportVo> selectCountByDate(@Param("time") String time);
	
	@Select("SELECT COUNT(*) as total,DATE_FORMAT(register_time, '%Y-%m-%d') as date FROM tb_member WHERE (sub_email is not null and sub_email <> '') and ( DATE_FORMAT(register_time, '%Y-%m')=#{time}) GROUP BY DATE_FORMAT(register_time, '%Y-%m-%d')")
	List<ReportVo> selectSubCountByDate(@Param("time") String time);
	
}