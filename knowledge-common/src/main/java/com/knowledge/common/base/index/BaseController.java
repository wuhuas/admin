package com.knowledge.common.base.index;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knowledge.common.base.config.exception.ParamsException;
import com.knowledge.common.base.enums.EnumResult;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.business.member.entity.TbMember;
import com.knowledge.common.business.member.service.ITbMemberService;


/**
 * BaseController
 * @author francis
 *
 */
public abstract class BaseController {
	
	@Autowired
	private ITbMemberService memberService;

	
	@Autowired
    public  HttpServletRequest request;
	
	public Result success() {
		return new Result();
	}
	
	/**
	 * 返回枚举错误信息
	 * @param erm
	 * @return
	 */
	public Result fail(EnumResult erm) {
		Result bean = new Result();
		bean.setCode(erm.getCode());
		bean.setMsg(erm.getMsg());
		return bean;
	}
	
	/**
	 * 返回自定义错误信息
	 * @param msg
	 * @param status
	 * @return
	 */
	public Result fail(String msg,String code) {
		Result bean = new Result();
		bean.setCode(code);
		bean.setMsg(msg);
		return bean;
	}
	
	/**
	 * 返回服务器错误
	 * @return
	 */
	public Result error() {
		Result bean = new Result();
		bean.setCode(EnumResult.ERROR.getCode());
		bean.setMsg(EnumResult.ERROR.getMsg());
		return bean;
	}
	
	
	/**
	 * 成功，并返回json数据
	 * @param message 消息
	 * @return
	 */
	public Result json(Object data) {
		Result bean = new Result();
		bean.setData(data);
		return bean;
	}
	
	public String toJson(Object object) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
	
	public <T> T getJson(String json,Class<T> valueType) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES , false);
		return mapper.readValue(json,valueType);
	}
    
	public static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	public static final Validator validator = factory.getValidator();

	/**
	 * 根据是否有注解判断属性值是否为空
	 * 
	 * @param t
	 * @throws AddException
	 */
	public static void checkParams(Object entity) {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(entity);
		for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
			throw new ParamsException(constraintViolation.getMessage());

		}
	}
	
	public TbMember getMemberInfo() {
		String userIdStr= request.getAttribute("userId").toString();
		Integer userId=Integer.valueOf(userIdStr);
		TbMember member=memberService.selectById(userId);
		return member;
	}
	
	public Integer getuserId() {
		String userIdStr= request.getAttribute("userId").toString();
		Integer userId=Integer.valueOf(userIdStr);
		return userId;
	}

}

