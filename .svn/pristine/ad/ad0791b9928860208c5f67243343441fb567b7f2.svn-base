package com.knowledge.common.business.periodical.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 电子杂志订单
 * </p>
 *
 * @author francis
 * @since 2018-11-28
 */
@TableName("tb_electronics_magazine_order")
public class TbElectronicsMagazineOrder extends Model<TbElectronicsMagazineOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 预订人
     */
	@TableField("order_name")
	private String orderName;
    /**
     * 学生姓名
     */
	@TableField("student_name")
	private String studentName;
    /**
     * 年级
     */
	private String grade;
    /**
     * 学校
     */
	private String school;
    /**
     * 注册邮箱
     */
	@TableField("register_mail")
	private String registerMail;
    /**
     * 注册时间
     */
	@TableField("register_time")
	private Date registerTime;
    /**
     * 订阅邮箱
     */
	@TableField("order_mail")
	private String orderMail;
    /**
     * 订阅时间
     */
	@TableField("order_time")
	private Date orderTime;
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
	private String adress;
    /**
     * 联系电话
     */
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


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getRegisterMail() {
		return registerMail;
	}

	public void setRegisterMail(String registerMail) {
		this.registerMail = registerMail;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getOrderMail() {
		return orderMail;
	}

	public void setOrderMail(String orderMail) {
		this.orderMail = orderMail;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
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

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbElectronicsMagazineOrder{" +
			", id=" + id +
			", orderName=" + orderName +
			", studentName=" + studentName +
			", grade=" + grade +
			", school=" + school +
			", registerMail=" + registerMail +
			", registerTime=" + registerTime +
			", orderMail=" + orderMail +
			", orderTime=" + orderTime +
			", whatsapp=" + whatsapp +
			", wechat=" + wechat +
			", adress=" + adress +
			", phone=" + phone +
			", subscribeYear=" + subscribeYear +
			", startTime=" + startTime +
			", endTime=" + endTime +
			"}";
	}
}
