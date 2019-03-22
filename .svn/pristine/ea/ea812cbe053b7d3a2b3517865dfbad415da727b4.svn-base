package com.knowledge.common.business.member.entity;

import java.io.Serializable;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author francis
 * @since 2018-11-30
 */
@TableName("tb_binding_user")
public class TbBindingUser extends Model<TbBindingUser> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 1 facebook 2google
     */
	private Integer platform;
	@TableField("user_id")
	private Integer userId;
	@TableField("platform_id")
	private String platformId;
	@TableField("platform_name")
	private String platformName;
	@TableField("about_me")
	private String aboutMe;
	
	private String email;
	
	@TableField(exist=false)
	private String password;
	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}



	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}


	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbBindingUser{" +
			", id=" + id +
			", platform=" + platform +
			", userId=" + userId +
			", platformId=" + platformId +
			", platformName=" + platformName +
			", aboutMe=" + aboutMe +
			"}";
	}
}
