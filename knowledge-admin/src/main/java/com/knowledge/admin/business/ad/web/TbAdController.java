package com.knowledge.admin.business.ad.web;

import java.util.Date;
import java.util.List;
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
import com.knowledge.common.base.model.Result;
import com.knowledge.common.business.ad.entity.TbAd;
import com.knowledge.common.business.ad.entity.TbAdType;
import com.knowledge.common.business.ad.service.ITbAdService;
import com.knowledge.common.business.ad.service.ITbAdTypeService;


/**
 * 广告管理
 * @author wb
 *
 */
@Controller
@RequestMapping("/ad/")
public class TbAdController extends BaseAdminController {
	
	//广告管理
	@Autowired
	private ITbAdService iTbAdService;
	
	//广告类型管理
	@Autowired
	private ITbAdTypeService iTbAdTypeService;
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("listUI")
    public String listUI(@RequestParam Map<String,Object> map,Map<String, Object> result,Integer page) {
		Condition create = Condition.create();
		if(Validator.isNotNullOrEmpty(map.get("typeId")) && !map.get("typeId").equals("0")) {
			create.eq("type_id", map.get("typeId").toString());
		}
		if (Validator.isNotNullOrEmpty(map.get("from")) && Validator.isNotNullOrEmpty(map.get("to"))) {
			create.between("DATE_FORMAT(create_time, '%Y-%m-%d')", map.get("from"), map.get("to"));
		}
		if(Validator.isNotNullOrEmpty(map.get("createUser"))) {
			create.like("create_user", map.get("createUser").toString());
		}
		Page<TbAd> list = iTbAdService.selectPage(new Page<TbAd>(null==page?0:page, 10),create);
		for (TbAd ad : list.getRecords()) {
			TbAdType adType = iTbAdTypeService.selectById(ad.getTypeId());
			if (Validator.isNotNullOrEmpty(adType)) {
				ad.setTypeName(adType.getName());
			}
		}
		map.put("page", list);
		List<TbAdType> adTypes =iTbAdTypeService.selectList(null);
		map.put("adTypes",adTypes);
		result.putAll(map);
		return "ad/list";
    }
	
	
	
	
	@RequestMapping("form")
    public String form(Map<String,Object> map,Map<String, Object> result) {
		
		//查詢所有分類  返回
		List<TbAdType> list =iTbAdTypeService.selectList(null);
		map.put("adTypes", list);
		result.putAll(map);
		
		return "ad/form";
    }
	
	
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Result add(TbAd tbAd){
		boolean result = false;
		tbAd.setWeight(tbAd.getWeight() == null ? 0 : tbAd.getWeight());
		
		if(Validator.isNotNullOrEmpty(tbAd.getId())){
			tbAd.setUpdateUser(getAccountName());
			tbAd.setUpdateTime(new Date());
			result = iTbAdService.updateById(tbAd);
		}else{
			tbAd.setCreateUser(getAccountName());
			tbAd.setCreateTime(new Date());
			result = iTbAdService.insert(tbAd);
		}
		
		if(result){
			return success();
		}else{
			return error();
		}
		
		
	}
	
	@RequestMapping(value="{id}/select",method=RequestMethod.GET)
    public String select(Map<String,Object> map,@PathVariable(required=true) Integer id) {	
		TbAd tbAd = iTbAdService.selectById(id);
		map.put("record", tbAd);
		List<TbAdType> adTypes =iTbAdTypeService.selectList(null);
		map.put("adTypes",adTypes);
		return "ad/edit";
    }
	
	
	@RequestMapping(value="{id}/delete",method=RequestMethod.DELETE)
	@ResponseBody
    public Result delete(@PathVariable(required=true) Integer id) {	
		if(!iTbAdTypeService.deleteById(id)) {
			return error();
		}
		return success();
    }
	
	
	@RequestMapping(value = "saveList", method = RequestMethod.POST)
	@ResponseBody
	public Result addList(@RequestParam Map<String,Object> map){
		boolean result = false;
		
		//总共数量
		Integer size=Integer.valueOf(map.get("size").toString());
		
		TbAd tbAd;
		
		//循环更新
		for(int i=1;i<size+1;i++){
			tbAd=new TbAd();
			tbAd.setId(Integer.valueOf(map.get("contentId"+i).toString()));
			tbAd.setJumpUrl(map.get("jumpUrl"+i).toString());
			tbAd.setPictureUrl(map.get("pictureUrl"+i).toString());
			tbAd.setTitle(map.get("title"+i).toString());
			tbAd.setTypeId(Integer.valueOf(map.get("typeId").toString()));
			tbAd.setWeight(Integer.valueOf(map.get("weight"+i).toString()));
			result = iTbAdService.updateById(tbAd);
			System.out.println("=========================================");
			System.out.println(tbAd.toString());
			System.out.println(result);
		}
		
		
		if(result){
			return success();
		}else{
			return error();
		}
		
		
	}
	
	
	
	
	
	/*@RequestMapping(value="{typeId}/select",method=RequestMethod.GET)
    public String select(Map<String,Object> map,@PathVariable(required=true) Integer typeId,Map<String, Object> result) {	
		
		//查找所有廣告
		Condition create = Condition.create();
		create.like("type_id", typeId.toString());
		create.orderBy("weight",false);
		Page<TbAd> list = iTbAdService.selectPage(new Page<TbAd>(0, 100),create);
		map.put("page", list);
		
		//查找類型
		TbAdType tbAdType =iTbAdTypeService.selectById(typeId);

		map.put("size", list.getTotal());
		map.put("typeName", tbAdType.getName());
		result.putAll(map);

		return "ad/edit";
    }*/
	
	
	
	
	@RequestMapping(value="{typeId}/see",method=RequestMethod.GET)
    public String see(Map<String,Object> map,@PathVariable(required=true) Integer typeId,Map<String, Object> result) {	
		
		//查找所有廣告
		Condition create = Condition.create();
		create.like("type_id", typeId.toString());
		create.orderBy("weight",false);
		Page<TbAd> list = iTbAdService.selectPage(new Page<TbAd>(0, 100),create);
		map.put("page", list);
		
		//查找類型
		TbAdType tbAdType =iTbAdTypeService.selectById(typeId);

		map.put("size", list.getTotal());
		map.put("typeName", tbAdType.getName());
		result.putAll(map);

		return "ad/see";
    }
	
	

}
