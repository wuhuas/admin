package com.knowledge.admin.base.userrole.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 用户角色映射表
 * </p>
 *
 * @author xj
 * @since 2016-12-26
 */
@TableName("tb_role_user")
public class TbRoleUser extends Model<TbRoleUser> {

    private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type=IdType.AUTO)
	private Integer id;
	/**
	 * 角色id
	 */
	@TableField(value="r_id")
	private Integer rId;
	/**
	 * 用户id
	 */
	@TableField(value="u_id")
	private Integer uId;
	/**
	 * 创建时间
	 */
	@TableField(value="t_create_time")
	private Date tCreateTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRId() {
		return rId;
	}

	public void setRId(Integer rId) {
		this.rId = rId;
	}

	public Integer getUId() {
		return uId;
	}

	public void setUId(Integer uId) {
		this.uId = uId;
	}

	public Date getTCreateTime() {
		return tCreateTime;
	}

	public void setTCreateTime(Date tCreateTime) {
		this.tCreateTime = tCreateTime;
	}

	
	public TbRoleUser(Integer uId) {
		super();
		this.uId = uId;
	}

	
	public TbRoleUser() {
		super();
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
