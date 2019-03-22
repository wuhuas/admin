package com.knowledge.admin.base.vo;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class ActivityEnrollVo implements Serializable {

	private static final long serialVersionUID = -4056302461692432337L;

	@Excel(name="活動名稱",width=30,orderNum="0")
	private String activityName;
	
	/**
	 * 姓名
	 */
	@Excel(name="用戶名",width=20,orderNum="1")
	private String name;
	/**
	 * 所属学校
	 */
	@Excel(name="學校",width=30,orderNum="2")
	private String school;
	/**
	 * 年班
	 */
	@Excel(name="年班",orderNum="3")
	private String annualClass;
	
	@Excel(name="Email",width=20,orderNum="4")
	private String email;
	
	@Excel(name="聯繫電話",orderNum="5")
	private String phone;
	
	@Excel(name="報名時間",width=20,orderNum="6")
	private String enrollTime;
	
	@Excel(name="活動時間",width=35,orderNum="7")
	private String activityTime;
	
	@Excel(name="狀態",replace= {"待確認_0","已確認_1","拒絕_2"},orderNum="8")
	private Integer status;
	
	@Excel(name="活動收費",replace= {"收費_1","免費_2"},orderNum="9")
	private Integer isCost;
	
	@Excel(name="收費憑證",orderNum="10")
	private String payVoucherUrl;

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getAnnualClass() {
		return annualClass;
	}

	public void setAnnualClass(String annualClass) {
		this.annualClass = annualClass;
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

	public String getEnrollTime() {
		return enrollTime;
	}

	public void setEnrollTime(String enrollTime) {
		this.enrollTime = enrollTime;
	}

	public String getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(String activityTime) {
		this.activityTime = activityTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsCost() {
		return isCost;
	}

	public void setIsCost(Integer isCost) {
		this.isCost = isCost;
	}

	public String getPayVoucherUrl() {
		return payVoucherUrl;
	}

	public void setPayVoucherUrl(String payVoucherUrl) {
		this.payVoucherUrl = payVoucherUrl;
	}

	@Override
	public String toString() {
		return "ActivityEnrollVo [activityName=" + activityName + ", name=" + name + ", school=" + school
				+ ", annualClass=" + annualClass + ", email=" + email + ", phone=" + phone + ", enrollTime="
				+ enrollTime + ", activityTime=" + activityTime + ", status=" + status + ", isCost=" + isCost
				+ ", payVoucherUrl=" + payVoucherUrl + "]";
	}
	
}
