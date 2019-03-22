package com.knowledge.api.business.google;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feilong.core.Validator;
import com.knowledge.api.base.service.GoogleService;
import com.knowledge.common.base.index.BaseController;
import com.knowledge.common.base.model.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author jide
 * @date 2018-12-03
 */
@Controller
@Api(value="googleAPI",tags={"google 登录"})
public class OAuth2Controller extends BaseController {

	private Logger log = LogManager.getLogger(OAuth2Controller.class);

	@Autowired
	private GoogleService googleService;

	@GetMapping(value = "/index")
	public String index() {
		return "index";

	}
	@GetMapping(value = "/facebook")
	public String face() {
		return "facebook";

	}

	@PostMapping("/verifyToken")
	@ResponseBody
	@ApiOperation(value = "google登录验证")
	public Result verifyToken(String idtokenstr) {
		Integer userId = googleService.verifyToken(idtokenstr);
		if (Validator.isNotNullOrEmpty(userId)) {
			return json(userId);
		}
		return error();

	}

}
