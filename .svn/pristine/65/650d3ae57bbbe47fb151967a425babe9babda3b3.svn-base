package com.knowledge.admin.base.vo;

import java.io.Serializable;
import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class MemberVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Excel(name="用戶名",orderNum="0",width=30)
	private String nickname;

	/**
	 * 性别（0=男 1=女 2=保密）
	 */
	@Excel(name="性別",replace= {"男_0","女_1","保密_2"},orderNum="1")
	private Integer sex;
	/**
	 * 所属学校
	 */
	@Excel(name="學校",orderNum="2",width=30)
	private String school;
	/**
	 * 年班
	 */
	@Excel(name="年班",orderNum="3")
	private String grade;
	/**
	 * Email
	 */
	@Excel(name="Email",orderNum="4",width=35)
	private String email;
	/**
	 * 联系电话
	 */
	@Excel(name="聯繫電話",orderNum="5",width=20)
	private String phone;
	/**
	 * whatsapp
	 */
	@Excel(name="WhatsApp",orderNum="6",width=20)
	private String whatsapp;
	/**
	 * wechat
	 */
	@Excel(name="Wechat",orderNum="7",width=20)
	private String wechat;
	/**
	 * 注册时间
	 */
	@Excel(name="註冊時間",orderNum="8",width=30,format="yyyy-MM-dd HH:mm:ss")
	private Date registerTime;
	
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWhatsapp() {
		return whatsapp;
	}
	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	@Override
	public String toString() {
		return "MemberVo [nickname=" + nickname + ", sex=" + sex + ", school=" + school + ", grade=" + grade
				+ ", email=" + email + ", phone=" + phone + ", whatsapp=" + whatsapp + ", wechat=" + wechat
				+ ", registerTime=" + registerTime + "]";
	}
	
}
