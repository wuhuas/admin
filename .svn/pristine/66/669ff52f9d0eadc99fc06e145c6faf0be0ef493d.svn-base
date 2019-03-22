package com.knowledge.api.business.draft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.Condition;
import com.knowledge.api.base.config.jwt.annotation.UserLoginToken;
import com.knowledge.common.base.index.BaseController;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.business.draft.entity.TbCollectionDraftUser;
import com.knowledge.common.business.draft.service.ITbCollectionDraftManageService;
import com.knowledge.common.business.draft.service.ITbCollectionDraftUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/draft/")
@Api(tags = { "知识征稿" })
public class DraftController extends BaseController {

	@Autowired
	private ITbCollectionDraftManageService collectionDraftManageService;

	@Autowired
	private ITbCollectionDraftUserService collectionDraftUserService;

	@SuppressWarnings("unchecked")
	@GetMapping("getDraftManage")
	@ApiOperation("征稿启示")
	public Result getDraftManage() {
		return json(collectionDraftManageService.selectList(Condition.create().eq("status", 1)));
	}

	@UserLoginToken
	@PostMapping("addDraftUser")
	@ApiOperation(value = "投稿", notes = "type作品类型（0=书法 1=绘画 2=摄影 3=多媒体 4=作文 5=其他）<br/>{\r\n"
			+ "  \"className\": \"string\",\r\n" + "  \"draftId\": 1,\r\n" + "  \"email\": \"string\",\r\n"
			+ "  \"enclosureUrl\": \"string\",\r\n" + "  \"explain\": \"string\",\r\n" + "  \"name\": \"string\",\r\n"
			+ "  \"phone\": \"string\",\r\n" + "  \"schoolName\": \"string\",\r\n"
			+ "  \"studentName\": \"string\",\r\n" + "  \"teacher\": \"string\",\r\n" + "  \"type\": 0,\r\n"
			+ "  \"whatsapp\": \"string\"\r\n" + "}	")
	public Result addDraftUser(@RequestBody TbCollectionDraftUser draftUser) {
		draftUser.setCreateUser(getMemberInfo().getNickname());
		collectionDraftUserService.insert(draftUser);
		return success();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("getDraftUserByUserId")
	@ApiOperation("查询用户投稿列表")
	@UserLoginToken
	public Result getDraftUserByUserId() {
		return json(collectionDraftUserService
				.selectList(Condition.create().eq("create_user", getMemberInfo().getNickname())));
	}

	@GetMapping("getDraftUserById")
	@ApiOperation("查询用户投稿详情")
	public Result getDraftUserById(@RequestParam Integer id) {
		return json(collectionDraftUserService.selectById(id));
	}
}
