package com.knowledge.admin.base.role.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.knowledge.admin.base.user.entity.TbUser;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author francis
 */
@TableName("tb_role")
public class TbRole extends Model<TbRole> {

    private static final long serialVersionUID = 1L;

	/**
	 * 角色id
	 */
	@TableId(value="r_id",type=IdType.AUTO)
	private Integer id;
	/**
	 * 角色名称
	 */
	@TableField(value="r_name")
	private String name;
	/**
	 * 角色key
	 */
	@TableField(value="r_key")
	private String key;
	/**
	 * 角色状态,0：正常；1：删除
	 */
	@TableField(value="r_status")
	private Integer status;
	/**
	 * 角色描述
	 */
	@TableField(value="r_description")
	private String description;
	/**
	 * 创建时间
	 */
	@TableField(value="r_create_time")
	private Date createTime;
	/**
	 * 更新时间
	 */
	@TableField(value="r_update_time")
	private Date updateTime;

	@TableField(exist=false)
	private List<TbUser> userList;
	
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




	public String getKey() {
		return key;
	}




	public void setKey(String key) {
		this.key = key;
	}




	public Integer getStatus() {
		return status;
	}




	public void setStatus(Integer status) {
		this.status = status;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public Date getCreateTime() {
		return createTime;
	}




	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}




	public Date getUpdateTime() {
		return updateTime;
	}




	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<TbUser> getUserList() {
		return userList;
	}



	public void setUserList(List<TbUser> userList) {
		this.userList = userList;
	}



	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
