package com.knowledge.admin.base.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;


public class LoginVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "用户名不能为空!")
	private String username;
	
	@NotBlank(message = "密码不能为空!")
    private String password;
    
	@NotBlank(message = "验证码不能为空!")
    private String captcha;
    
	@NotBlank(message = "uuid不能为空!")
    private String uuid;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
