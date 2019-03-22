package com.knowledge.common.base.config.exception;

import com.knowledge.common.base.enums.EnumResult;

/**
 * @author leo.zhu
 *
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private EnumResult result;
	
	private String code;
	
	private String msg;
	
	public EnumResult getResult() {
		return result;
	}
	
	public void setResult(EnumResult result) {
		this.result = result;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public BusinessException(EnumResult result) {
		this.result = result;
	}
	
	public BusinessException(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
