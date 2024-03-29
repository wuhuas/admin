package com.knowledge.common.business.principalEnroll.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 校长报名表
 * </p>
 *
 * @author xiong
 * @since 2019-03-12
 */
@TableName("tb_principal_enroll")
public class TbPrincipalEnroll extends Model<TbPrincipalEnroll> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 分论坛研讨范畴，填ID逗号隔开
     */
	@TableField("discuss_range")
	private String discussRange;
    /**
     * 实践报告标题
     */
	private String title;
    /**
     * 论文报告url
     */
	@TableField("paper_url")
	private String paperUrl;
    /**
     * 校长姓名
     */
	private String name;
    /**
     * 校长职衔
     */
	private String occupation;
    /**
     * 地区 0=香港  1=内地
     */
	private Integer region;
    /**
     * 省市
     */
	private String provinces;
    /**
     * 工作学校全名
     */
	private String school;
    /**
     * 联络地址
     */
	private String address;
    /**
     * 联络电话
     */
	private String phone;
    /**
     * 电子邮箱
     */
	private String mail;
    /**
     * 身份证
     */
	private String certificates;
    /**
     * 港澳通行证
     */
	private String passcheck;
    /**
     * 照片url
     */
	@TableField("picture_url")
	private String pictureUrl;
    /**
     * 参评方式 0=个人 1=小组
     */
	private Integer participate;
    /**
     * 校长介绍
     */
	private String introduce;
	
	/**
	 * 报名时间
	 */
	@TableField("create_time")
	private Date createTime;
	
	/**
	 * 创建人
	 */
	@TableField("create_user")
	private Date createUser;

	
	/**
	 * 狀態  0=未確認   1=已確認   2=拒絕
	 */
	private Integer status;
	
	
	
	/**
	 * 確認時間
	 */
	@TableField("update_time")
	private Date updateTime;
	@TableField(exist=false)
	private List<TbPrincipalEnrollGroup> groups;
	
	/**
	 * 住宿房间  0=单人间   1=双人间
	 */
	private Integer room;
	
	


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getRoom() {
		return room;
	}

	public void setRoom(Integer room) {
		this.room = room;
	}

	public List<TbPrincipalEnrollGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<TbPrincipalEnrollGroup> groups) {
		this.groups = groups;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDiscussRange() {
		return discussRange;
	}

	public void setDiscussRange(String discussRange) {
		this.discussRange = discussRange;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPaperUrl() {
		return paperUrl;
	}

	public void setPaperUrl(String paperUrl) {
		this.paperUrl = paperUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Integer getRegion() {
		return region;
	}

	public void setRegion(Integer region) {
		this.region = region;
	}

	public String getProvinces() {
		return provinces;
	}

	public void setProvinces(String provinces) {
		this.provinces = provinces;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getCertificates() {
		return certificates;
	}

	public void setCertificates(String certificates) {
		this.certificates = certificates;
	}

	public String getPasscheck() {
		return passcheck;
	}

	public void setPasscheck(String passcheck) {
		this.passcheck = passcheck;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public Integer getParticipate() {
		return participate;
	}

	public void setParticipate(Integer participate) {
		this.participate = participate;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Date createUser) {
		this.createUser = createUser;
	}

	@Override
	public String toString() {
		return "TbPrincipalEnroll{" +
			", id=" + id +
			", discussRange=" + discussRange +
			", title=" + title +
			", paperUrl=" + paperUrl +
			", name=" + name +
			", occupation=" + occupation +
			", region=" + region +
			", provinces=" + provinces +
			", school=" + school +
			", address=" + address +
			", phone=" + phone +
			", mail=" + mail +
			", certificates=" + certificates +
			", passcheck=" + passcheck +
			", pictureUrl=" + pictureUrl +
			", participate=" + participate +
			", introduce=" + introduce +
			"}";
	}
}
