package com.knowledge.common.business.message.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 留言 关键字管理
 * </p>
 *
 * @author francis
 * @since 2018-12-03
 */
@TableName("tb_message_keyword")
public class TbMessageKeyword extends Model<TbMessageKeyword> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 关键词
     */
	private String keyword;
    /**
     * 创建时间
     */
	@TableField(value = "create_time",fill = FieldFill.INSERT)
	private Date createTime;
    /**
     * 创建人
     */
	@TableField("create_user")
	private String createUser;
    /**
     * 修改时间
     */
	@TableField(value = "update_time",fill = FieldFill.UPDATE)
	private Date updateTime;
    /**
     * 修改人
     */
	@TableField("update_user")
	private String updateUser;
    /**
     * 状态（0=下架  1=上架）
     */
	private Integer status;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbMessageKeyword{" +
			", id=" + id +
			", keyword=" + keyword +
			", createTime=" + createTime +
			", createUser=" + createUser +
			", updateTime=" + updateTime +
			", updateUser=" + updateUser +
			", status=" + status +
			"}";
	}
}
