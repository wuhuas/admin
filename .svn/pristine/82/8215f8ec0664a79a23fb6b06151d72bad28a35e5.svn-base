package com.knowledge.common.business.periodical.mapper;

import com.knowledge.common.business.periodical.entity.TbSelectedPeriodical;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  * 精选期刊 Mapper 接口
 * </p>
 *
 * @author francis
 * @since 2018-11-28
 */
public interface TbSelectedPeriodicalDao extends BaseMapper<TbSelectedPeriodical> {

	List<TbSelectedPeriodical> selectAll(Page<TbSelectedPeriodical> page, Map<String, Object> map);

	@Select("select * from tb_selected_periodical order by periods_number desc limit 0,6")
	List<TbSelectedPeriodical> selectTop();

	@Update("update tb_selected_periodical set state=0")
	void updateState();

}