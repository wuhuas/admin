package com.knowledge.admin.base.resourcerole.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 角色权限映射表
 * </p>
 *
 * @author francis
 */
@TableName("tb_resources_role")
public class TbResourcesRole extends Model<TbResourcesRole> {

    private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type=IdType.AUTO)
	private Integer id;
	/**
	 * 资源id
	 */
	@TableField(value="s_id")
	private Integer sid;
	/**
	 * 角色id
	 */
	@TableField(value="r_id")
	private Integer rid;
	/**
	 * 创建时间
	 */
	@TableField(value="t_create_time")
	private Date createTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
