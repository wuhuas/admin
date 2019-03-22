package com.knowledge.api.business.home;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.feilong.core.DatePattern;
import com.feilong.core.Validator;
import com.feilong.core.date.DateUtil;
import com.feilong.core.util.CollectionsUtil;
import com.knowledge.common.base.index.BaseController;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.business.ad.entity.TbAd;
import com.knowledge.common.business.ad.service.ITbAdService;
import com.knowledge.common.business.creation.entity.TbCreationManage;
import com.knowledge.common.business.creation.service.ITbCreationManageService;
import com.knowledge.common.business.member.entity.TbMember;
import com.knowledge.common.business.member.service.ITbMemberService;
import com.knowledge.common.business.news.entity.TbNewsCategory;
import com.knowledge.common.business.news.entity.TbNewsInfo;
import com.knowledge.common.business.news.service.ITbNewsCategoryService;
import com.knowledge.common.business.news.service.ITbNewsInfoService;
import com.knowledge.common.business.periodical.entity.TbSelectedPeriodical;
import com.knowledge.common.business.periodical.service.ITbSelectedPeriodicalService;
import com.knowledge.common.business.periodicaltips.entity.TbPeriodicalTips;
import com.knowledge.common.business.periodicaltips.service.ITbPeriodicalTipsService;
import com.knowledge.common.business.school.entity.TbSchoolCalendarRecommend;
import com.knowledge.common.business.school.service.ITbSchoolCalendarRecommendService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/home/")
@Api(tags = { "首页" })
public class HomeController extends BaseController {

	@Autowired
	private ITbAdService adService;

	@Autowired
	private ITbMemberService memberService;

	@Autowired
	private ITbNewsInfoService newsService;

	@Autowired
	private ITbNewsCategoryService newsCategoryService;

	@Autowired
	private ITbSelectedPeriodicalService periodicalService;

	@Autowired
	private ITbPeriodicalTipsService periodicalTipsService;

	@Autowired
	private ITbSchoolCalendarRecommendService schoolCalendarRecommendService;

	@Autowired
	private ITbCreationManageService creationService;

	@SuppressWarnings("unchecked")
	@GetMapping("getHome")
	@ApiOperation("首頁")
	public Result getHome() {
		Map<String, Object> map = new HashMap<>();
		List<TbAd> topAds = adService.selectList(
				Condition.create().eq("type_id", 1).orderBy("weight", false).orderBy("id", false).last("limit 4"));
		map.put("topAds", topAds);

		List<TbNewsInfo> hotNews = newsService.selectByTop(Condition.create().eq("i.status", 1)
				.orderBy("i.weight", false).orderBy("i.create_time", false).last("limit 3"));
		map.put("hotNews", hotNews);

		List<TbNewsCategory> categorys = newsCategoryService.selectList(Condition.create().eq("status", 1)
				.orderBy("weight", false).orderBy("create_time", false).last("limit 6"));
		for (int i = 0; i < categorys.size(); i++) {
			TbNewsCategory tbNewsCategory = categorys.get(i);
			List<TbNewsInfo> list = newsService.selectList(Condition.create().eq("category_id", tbNewsCategory.getId())
					.eq("status", 1).orderBy("weight", false).orderBy("create_time", false).last("limit 3"));
			List<TbNewsInfo> latest = newsService
					.selectList(Condition.create().eq("category_id", tbNewsCategory.getId()).eq("status", 1)
							.orderBy("create_time", false).orderBy("weight", false).last("limit 5"));
			tbNewsCategory.setNewsInfos(list);
			List<Integer> ids = CollectionsUtil.getPropertyValueList(latest, "id");
			tbNewsCategory.setIds(ids);
		}
		map.put("columns", categorys);
		List<TbAd> midAds = adService.selectList(
				Condition.create().eq("type_id", 2).orderBy("weight", false).orderBy("id", false).last("limit 1"));
		map.put("midAds", midAds);

		List<TbSelectedPeriodical> selectedPeriodicals = null;
		Wrapper<TbSelectedPeriodical> wrapper = Condition.create();
		List<TbSelectedPeriodical> list = periodicalService
				.selectList(Condition.create().orderBy("periods_number", false).last("limit 1,5"));

		String token = request.getHeader("token");
		if (Validator.isNotNullOrEmpty(token)) {
			String userId = JWT.decode(token).getAudience().get(0);
			TbMember user = memberService.selectById(Integer.parseInt(userId));
			// 验证 token
			JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
			jwtVerifier.verify(token);
			Date readDateStart = user.getReadDateStart();
			Date readDateEnd = user.getReadDateEnd();
			if (readDateStart == null || readDateEnd == null
					|| !DateUtil.isInTime(new Date(), readDateStart, readDateEnd)) {
				wrapper.notIn("id", CollectionsUtil.getPropertyValueList(list, "id"));
			}
		}
		if (Validator.isNullOrEmpty(token)) {
			wrapper.notIn("id", CollectionsUtil.getPropertyValueList(list, "id"));
		}
		selectedPeriodicals = periodicalService
				.selectList(wrapper.orderBy("periods_number", false).orderBy("id", false).last("limit 3"));
		map.put("selectedPeriodicals", selectedPeriodicals);

		List<TbAd> downAds = adService.selectList(
				Condition.create().eq("type_id", 3).orderBy("weight", false).orderBy("id", false).last("limit 1"));
		map.put("downAds", downAds);
		List<TbPeriodicalTips> periodicalTips = periodicalTipsService.selectList(Condition.create().eq("status", 1)
				.eq("is_home", 1).eq("type", 1).orderBy("weight", false).orderBy("id", false).last("limit 3"));
		map.put("periodicalTips", periodicalTips);

		List<TbPeriodicalTips> schoolmasters = periodicalTipsService.selectList(Condition.create().eq("status", 1)
				.eq("is_home", 1).eq("type", 2).orderBy("weight", false).orderBy("id", false).last("limit 3"));
		map.put("schoolmasters", schoolmasters);
		List<TbAd> rightUpAds = adService.selectList(
				Condition.create().eq("type_id", 4).orderBy("weight", false).orderBy("id", false).last("limit 1"));
		map.put("rightUpAds", rightUpAds);
		List<TbAd> rightDownAds = adService.selectList(
				Condition.create().eq("type_id", 5).orderBy("weight", false).orderBy("id", false).last("limit 3"));
		map.put("rightDownAds", rightDownAds);

		List<TbSchoolCalendarRecommend> schoolCalendarRecommends = schoolCalendarRecommendService.selectList(Condition
				.create().eq("status", 1).ge("activity_date", DateUtil.toString(new Date(), DatePattern.COMMON_DATE))
				.orderBy("activity_date", true).last("limit 6"));
		map.put("schoolCalendarRecommends", schoolCalendarRecommends);
		List<TbCreationManage> leftCreationManages = creationService
				.selectList(Condition.create().eq("status", 1).eq("is_home", 1).orderBy("id", false).last("limit 4"));
		map.put("leftCreationManages", leftCreationManages);

		List<TbCreationManage> rightCreationManages = creationService
				.selectList(Condition.create().eq("status", 1).orderBy("create_time", false).last("limit 8"));
		map.put("rightCreationManages", rightCreationManages);
		return json(map);
	}
}
