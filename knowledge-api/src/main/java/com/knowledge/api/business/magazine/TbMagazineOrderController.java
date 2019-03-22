package com.knowledge.api.business.magazine;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.knowledge.api.base.config.jwt.annotation.UserLoginToken;
import com.knowledge.common.base.index.BaseController;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.business.notice.entity.TbNotice;
import com.knowledge.common.business.notice.service.ITbNoticeService;
import com.knowledge.common.business.periodical.entity.TbMagazineOrder;
import com.knowledge.common.business.periodical.service.ITbMagazineOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 杂志订单  前端控制器
 * </p>
 *
 * @author jide
 * @since 2018-12-04
 */
@RestController
@Api(value="订阅杂志",tags={"订阅杂志"})
public class TbMagazineOrderController extends BaseController {

	@Autowired
	private ITbMagazineOrderService magazineService;
	
	@Autowired
	private ITbNoticeService noticeService;
	

	@UserLoginToken
	@PostMapping("/magazineorder/save")
	@ApiOperation(value = "订阅杂志保存",notes="订阅杂志保存")
	public Result add(@RequestBody TbMagazineOrder tbmagazineorder){
		tbmagazineorder.setReserveTime(new Date());
		magazineService.insert(tbmagazineorder);
		
		TbNotice notice=new TbNotice();
		notice.setTitle("系統消息");
		String a="";
		if(tbmagazineorder.getType().equals(1)) {
			a="付費訂閱";
		}
		if(tbmagazineorder.getType().equals(2)) {
			a="索取訂閱";
		}
		notice.setContent("您提交的"+a+"的香港《知識》雜誌信息正在審核中，審核結果將會發送到提交訂閱信息所填的郵箱中，請注意查收！");
		notice.setCreateTime(new Date());
		notice.setStatus(2);
		notice.setToMemId(getuserId());
		noticeService.insert(notice);
		return success();
	}
	
	
	

}
