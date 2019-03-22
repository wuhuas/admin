package com.knowledge.admin.business.news.web;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.feilong.core.Validator;
import com.knowledge.admin.base.login.BaseAdminController;
import com.knowledge.common.base.enums.EnumResult;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.business.news.entity.TbNewsInfo;
import com.knowledge.common.business.news.service.ITbNewsCategoryService;
import com.knowledge.common.business.news.service.ITbNewsInfoService;

/**
 * <p>
 * 资讯信息详情  前端控制器
 * </p>
 *
 * @author francis
 * @since 2018-11-09
 */
@Controller
@RequestMapping("/newsinfo/")
public class TbNewsInfoController extends BaseAdminController {

	@Autowired
	private ITbNewsInfoService newsService;
	
	@Autowired
	private ITbNewsCategoryService newsCategoryService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("listUI")
    public String listUI(@RequestParam Map<String,Object> params,Map<String,Object> result,Integer page) {
		Page<TbNewsInfo> list = newsService.selectByPage(new Page<TbNewsInfo>(null==page?0:page, 10),params);
		//资讯类别列表
		params.put("categories", newsCategoryService.selectList(Condition.create().eq("status", 1).orderBy("create_time", false)));
		params.put("page", list);
		result.putAll(params);
		System.err.println(result.toString());
		return "newsinfo/list";
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping("form")
    public String form(Map<String,Object> map) {
		map.put("categories", newsCategoryService.selectList(Condition.create().eq("status", 1).orderBy("create_time", false)));
		map.put("createUser", getUserEntity().getAccountName());
		return "newsinfo/form";
    }
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Result add(TbNewsInfo tbnewsinfo){
		
		
		//每個分類只能有三個置頂
		Condition create = Condition.create();
		create.eq("set_top", 1);
		create.eq("category_id", tbnewsinfo.getCategoryId());

		
		
		boolean result = false;
		Date now = new Date();
		if(tbnewsinfo.getSetTop() == null) {
			tbnewsinfo.setSetTop(2);
			tbnewsinfo.setTopTime(null);
		}else {
			tbnewsinfo.setSetTop(1);
			tbnewsinfo.setTopTime(now);
		}
		
		if("".equals(tbnewsinfo.getCoverUrl())){
			tbnewsinfo.setCoverUrl(null);
		}
		if("".equals(tbnewsinfo.getEnclosureUrl())){
			tbnewsinfo.setEnclosureUrl(null);
		}
		if("".equals(tbnewsinfo.getPdfUrl())){
			tbnewsinfo.setPdfUrl(null);
		}
		
		
		
		tbnewsinfo.setStatus(tbnewsinfo.getStatus() == null ? 2 : tbnewsinfo.getStatus());
		tbnewsinfo.setOpenComment(tbnewsinfo.getOpenComment() == null ? 2 : tbnewsinfo.getOpenComment());
		if(Validator.isNotNullOrEmpty(tbnewsinfo .getId())){
			tbnewsinfo.setUpdateUser(getUserEntity().getAccountName());
			tbnewsinfo.setUpdateTime(new Date());

			//每個分類只能有三個置頂
			if(tbnewsinfo.getSetTop() == 1) {
				create.ne("id", tbnewsinfo.getId());
				Integer total=newsService.selectCount(create);
				System.out.println("total="+total);
				if(total>=3 && tbnewsinfo.getSetTop()!=null){
					return fail(EnumResult.ERROR_NEWS_TOP_MAX);
				}
			}
			
			result=newsService.updateById(tbnewsinfo);

		}else{
			tbnewsinfo.setCreateUser(getUserEntity().getAccountName());
			
			//每個分類只能有三個置頂
			if(tbnewsinfo.getSetTop() == 1) {
				Integer total=newsService.selectCount(create);
				System.out.println("total="+total);
				if(total>=3 && tbnewsinfo.getSetTop()!=null){
					return fail(EnumResult.ERROR_NEWS_TOP_MAX);
				}
			}
			result = newsService.insert(tbnewsinfo);
		}
		if(result){
			return success();
		}else{
			return error();
		}
	}
	
	@RequestMapping(value="{id}/delete",method=RequestMethod.DELETE)
	@ResponseBody
    public Result delete(@PathVariable(required=true) Integer id) {	
		if(!newsService.deleteById(id)){
			return error();
		}
		return success();
    }	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="{id}/select",method=RequestMethod.GET)
    public String select(Map<String,Object> map,@PathVariable(required=true) Integer id) {	
		TbNewsInfo tbnewsinfo = newsService.selectById(id);
		map.put("record", tbnewsinfo);
		map.put("categories", newsCategoryService.selectList(Condition.create().eq("status", 1).orderBy("create_time", false)));
		return "newsinfo/edit";
    }
	
	@RequestMapping(value="changeStatus/{id}/{status}",method=RequestMethod.GET)
	@ResponseBody
	public Result changeStatus(@PathVariable("id") Integer id,@PathVariable("status") Integer status) {
		TbNewsInfo newsInfo = newsService.selectById(id);
		
		
		if (Validator.isNullOrEmpty(newsInfo)) {
			return fail(EnumResult.ERROR_PARAMS);
		}
		newsInfo.setStatus(status);
		if (status == 2 && newsInfo.getSetTop() == 1) {
			newsInfo.setSetTop(2);
			newsInfo.setTopTime(null);
		}
		if(newsInfo.updateById()) {
			return success();
		}else {
			return error();
		}
	}
	
	@RequestMapping(value="setTop/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Result setTop(@PathVariable("id") Integer id) {
		TbNewsInfo newsInfo = newsService.selectById(id);
		
		
		//每個分類只能有三個置頂
		Condition create = Condition.create();
		create.eq("set_top", 1);
		create.eq("category_id", newsInfo.getCategoryId());
		Integer total=newsService.selectCount(create);
		System.out.println("total="+total);
		if(total>=3 && newsInfo.getSetTop()==2){
			return fail(EnumResult.ERROR_NEWS_TOP_MAX);
		}
		
		
		
		
		if (Validator.isNullOrEmpty(newsInfo)) {
			return fail(EnumResult.ERROR_PARAMS);
		}
		if (newsInfo.getStatus() == 2) {
			return fail(EnumResult.ERROR_NEWS_CANT_TOP);
		}
		
		if (newsInfo.getSetTop() == 1) {
			newsInfo.setSetTop(2);
			newsInfo.setTopTime(null);
		}else if (newsInfo.getSetTop() == 2) {
			newsInfo.setSetTop(1);
			newsInfo.setTopTime(new Date());
		}
		if(newsInfo.updateById()) {
			return success();
		}else {
			return error();
		}
	}
}
