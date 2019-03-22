package com.knowledge.api.base.login;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.knowledge.common.business.news.service.ITbNewsInfoService;

@Controller
@RequestMapping("/share/")
public class ShareController {
	
	@Autowired
	private ITbNewsInfoService newsService;
	
	@GetMapping(value="{id}")
    public String select(Map<String,Object> map,@PathVariable(required=true) Integer id) {
		
		
		map.put("news", newsService.selectById(id));
		//System.err.println(map.toString());
		return "shareDetail";
    }

}
