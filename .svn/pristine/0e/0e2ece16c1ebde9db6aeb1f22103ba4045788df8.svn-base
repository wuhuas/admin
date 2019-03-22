package com.knowledge.common.business.sendEmailRecord.entity;


import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 活动/所有人 发送邮件记录
 * </p>
 *
 * @author xiong
 * @since 2019-02-22
 */
@TableName("tb_send_email_record")
public class TbSendEmailRecord extends Model<TbSendEmailRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 标题
     */
	private String title;
    /**
     * 发送对象ID，活动ID，0=所有人
     */
	@TableField("send_id")
	private String sendId;
    /**
     * 发送对象名字
     */
	@TableField("send_name")
	private String sendName;
    /**
     * 发送内容
     */
	@TableField("send_context")
	private String sendContext;
    /**
     * 创建用户
     */
	@TableField("create_user")
	private String createUser;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getSendContext() {
		return sendContext;
	}

	public void setSendContext(String sendContext) {
		this.sendContext = sendContext;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
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

	@Override
	public String toString() {
		return "TbSendEmailRecord{" +
			", id=" + id +
			", title=" + title +
			", sendId=" + sendId +
			", sendName=" + sendName +
			", sendContext=" + sendContext +
			", createUser=" + createUser +
			", createTime=" + createTime +
			"}";
	}
}
