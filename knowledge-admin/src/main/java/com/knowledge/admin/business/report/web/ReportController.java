package com.knowledge.admin.business.report.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.mapper.Condition;
import com.feilong.core.DatePattern;
import com.feilong.core.Validator;
import com.feilong.core.date.DateUtil;
import com.knowledge.admin.base.login.BaseAdminController;
import com.knowledge.common.base.model.ReportVo;
import com.knowledge.common.business.draft.service.ITbCollectionDraftUserService;
import com.knowledge.common.business.member.service.ITbMemberService;
import com.knowledge.common.business.message.service.ITbMessageManageService;
import com.knowledge.common.business.news.service.ITbNewsInfoService;

/**
 *
 *	報表管理
 * @author francis
 *
 */
@Controller
@RequestMapping("/report/")
public class ReportController extends BaseAdminController {

	@Autowired
	private ITbMemberService memberService;
	
	@Autowired
	private ITbMessageManageService messageService;
	
	@Autowired
	private ITbCollectionDraftUserService draftUserService;
	
	@Autowired
	private ITbNewsInfoService newsInfoService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("reportData")
	public String reportData(Map<String,Object> map , @RequestParam(required = false) String year,@RequestParam(required = false) String month) {
		String yearMonth = year +"-"+ month;
		if (Validator.isNullOrEmpty(year) && Validator.isNullOrEmpty(month) ) {
			yearMonth = DateUtil.toString(new Date(), DatePattern.YEAR_AND_MONTH);
		}
		List<ReportVo> users = memberService.selectCountByDate(yearMonth);
		List<ReportVo> subUsers = memberService.selectSubCountByDate(yearMonth);
		map.put("userReport", users);
		map.put("subReport", subUsers);
		
		Integer newUserTotal = 0;
		for (ReportVo reportVo : users) {
			newUserTotal += reportVo.getTotal();
		}
		
		Integer newSubTotal = 0;
		for (ReportVo reportVo : subUsers) {
			newSubTotal += reportVo.getTotal();
		}
		
		map.put("newUserTotal", newUserTotal);
		map.put("userTotal", memberService.selectCount(null));
		map.put("subTotal", memberService.selectCount(Condition.create().isNotNull("sub_email").ne("sub_email", "")));
		map.put("newSubTotal", newSubTotal);
		map.put("newDraftTotal", draftUserService.selectCount(Condition.create().andNew("DATE_FORMAT(draft_time,'%Y-%m') = '"+yearMonth+"'")));
		map.put("messageTotal", messageService.selectCount(Condition.create().andNew("DATE_FORMAT(message_time,'%Y-%m') = '"+yearMonth+"'")));
		
		map.put("browseTop10", newsInfoService.selectByTop(Condition.create().isWhere(true).andNew("DATE_FORMAT(i.create_time,'%Y-%m') = '"+yearMonth+"'").orderBy("browse_total", false).last("limit 10")));
		map.put("commentTop5", newsInfoService.selectByTop(Condition.create().isWhere(true).andNew("DATE_FORMAT(i.create_time,'%Y-%m') = '"+yearMonth+"'").orderBy("comment_total", false).last("limit 5")));
		map.put("year", year);
		map.put("month", month == null ? DateUtil.toString(new Date(), DatePattern.MM) : month);
		
		//类别的资讯总数
		map.put("categoryCount", newsInfoService.selectCategoryCount());
		System.err.println(map.get("categoryCount").toString());
		return "report/show";
	}
	
}
