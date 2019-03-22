package com.knowledge.common.base.model;

import java.io.Serializable;

import com.knowledge.common.base.enums.EnumResult;
/**
 * 通用返回结果
 * @ClassName:  Result
 * @Description:TODO
 * @author francis
 */
public class Result implements Serializable{

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1144863532326797108L;


    // 响应业务代码
    private String code = EnumResult.SUCCESS.getCode();

    // 响应消息
    private String msg = EnumResult.SUCCESS.getMsg();

    // 响应中的数据
    private Object data;

	
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Result(){
		super();
	}
	
	public Result(EnumResult enumResult){
		this.code = enumResult.getCode();
		this.msg = enumResult.getMsg();
	}
    
}
