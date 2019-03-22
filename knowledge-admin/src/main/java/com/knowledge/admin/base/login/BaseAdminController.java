package com.knowledge.admin.base.login;

import org.apache.shiro.SecurityUtils;

import com.feilong.core.Validator;
import com.knowledge.admin.base.user.entity.TbUser;
import com.knowledge.common.base.config.exception.BusinessException;
import com.knowledge.common.base.enums.EnumResult;
import com.knowledge.common.base.index.BaseController;


/**
 * BaseAdminController
 * @author francis
 *
 */
public abstract class BaseAdminController extends BaseController {
	
	/**
	 * 获取登录用户信息
	 * @return
	 */
	public TbUser getUserEntity() {
		return (TbUser)SecurityUtils.getSubject().getPrincipal();
	}

	/**
	 * 获取登录用户名稱
	 * @return
	 */
	public String getAccountName() {
		TbUser user = (TbUser)SecurityUtils.getSubject().getPrincipal();
		if (Validator.isNotNullOrEmpty(user)) {
			return user.getAccountName();
		}
		throw new BusinessException(EnumResult.ERROR);
	}
}

