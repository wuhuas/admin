package com.knowledge.common.business.principalEnroll.entity;


import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 校長報名表  小組成員信息
 * </p>
 *
 * @author xiong
 * @since 2019-03-12
 */
@TableName("tb_principal_enroll_group")
public class TbPrincipalEnrollGroup extends Model<TbPrincipalEnrollGroup> {

    private static final long serialVersionUID = 1L;

    /**
     * 主鍵
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 关联校长报名ID
     */
	@TableField("enroll_id")
	private Integer enrollId;
    /**
     * 成员姓名
     */
	private String name;
    /**
     * 成员职衔
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
     * 成员介绍
     */
	private String introduce;
	
	/**
	 * 房间住宿 0=单人间  1=双人间
	 */
	private Integer room;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEnrollId() {
		return enrollId;
	}

	public void setEnrollId(Integer enrollId) {
		this.enrollId = enrollId;
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

	public Integer getRoom() {
		return room;
	}

	public void setRoom(Integer room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "TbPrincipalEnrollGroup [id=" + id + ", enrollId=" + enrollId + ", name=" + name + ", occupation="
				+ occupation + ", region=" + region + ", provinces=" + provinces + ", school=" + school + ", address="
				+ address + ", phone=" + phone + ", mail=" + mail + ", certificates=" + certificates + ", passcheck="
				+ passcheck + ", pictureUrl=" + pictureUrl + ", introduce=" + introduce + ", room=" + room + "]";
	}



	
	
	
}
