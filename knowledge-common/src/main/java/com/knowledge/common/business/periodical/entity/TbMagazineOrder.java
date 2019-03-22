package com.knowledge.common.business.periodical.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * <p>
 * 杂志订单
 * </p>
 *
 * @author francis
 * @since 2018-11-28
 */
@TableName("tb_magazine_order")
public class TbMagazineOrder extends Model<TbMagazineOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 姓名
     */
	@Excel(name="用戶名",orderNum="0")
	private String name;
    /**
     * 学校
     */
	@Excel(name="學校",orderNum="1",width=25)
	private String school;
    /**
     * 年级
     */
	@Excel(name="年班",orderNum="2")
	private String grade;
    /**
     * Email
     */
	@Excel(name="Email",orderNum="3")
	private String email;
    /**
     * 联系电话
     */
	@Excel(name="聯繫電話",orderNum="4")
	private String phone;
    /**
     * 订阅年限（0=自定义时间 1=1年 2=2年 3=3年）
     */
	@TableField("subscribe_year")
	private Integer subscribeYear;
    /**
     * 自定义订阅年限的开始时间
     */
	@TableField("start_time")
	private Date startTime;
    /**
     * 自定义订阅年限的结束时间
     */
	@TableField("end_time")
	private Date endTime;
    /**
     * 预定时间
     */
	@Excel(name = "預定時間",orderNum = "6",format="yyyy-MM-dd HH:mm:ss",width=30)
	@TableField("reserve_time")
	private Date reserveTime;
    /**
     * 状态（1=已确认，2=未确认）
     */
	@Excel(name = "審核狀態",replace= {"已確認_1","未確認_2","\"\"_null"},orderNum = "7")
	private Integer status;
    /**
     * Whatsapp
     */
	private String whatsapp;
    /**
     * 微信
     */
	private String wechat;
    /**
     * 通信地址
     */
	@Excel(name = "地址",orderNum = "8",width=30)
	private String address;
    /**
     * 付款凭证链接
     */
	@TableField("pay_voucher_url")
	private String payVoucherUrl;
    /**
     * 备注
     */
	private String remark;
    /**
     * 审核人
     */
	@TableField("examine_name")
	private String examineName;
    /**
     * 审核时间
     */
	@TableField("examine_time")
	private Date examineTime;

	/**
	 * 类型 1订阅2赠阅
	 */
	private Integer type;

	@Excel(name = "訂購年限",orderNum = "5",width=25)
	@TableField(exist = false)
	private String subYears;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getSubscribeYear() {
		return subscribeYear;
	}

	public void setSubscribeYear(Integer subscribeYear) {
		this.subscribeYear = subscribeYear;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(Date reserveTime) {
		this.reserveTime = reserveTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPayVoucherUrl() {
		return payVoucherUrl;
	}

	public void setPayVoucherUrl(String payVoucherUrl) {
		this.payVoucherUrl = payVoucherUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getExamineName() {
		return examineName;
	}

	public void setExamineName(String examineName) {
		this.examineName = examineName;
	}

	public Date getExamineTime() {
		return examineTime;
	}

	public void setExamineTime(Date examineTime) {
		this.examineTime = examineTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public String getSubYears() {
		return subYears;
	}

	public void setSubYears(String subYears) {
		this.subYears = subYears;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "TbMagazineOrder{" +
			", id=" + id +
			", name=" + name +
			", school=" + school +
			", grade=" + grade +
			", email=" + email +
			", phone=" + phone +
			", subscribeYear=" + subscribeYear +
			", startTime=" + startTime +
			", endTime=" + endTime +
			", reserveTime=" + reserveTime +
			", status=" + status +
			", whatsapp=" + whatsapp +
			", wechat=" + wechat +
			", address=" + address +
			", payVoucherUrl=" + payVoucherUrl +
			", remark=" + remark +
			", examineName=" + examineName +
			", examineTime=" + examineTime +
			", type=" + type +
			"}";
	}
}
