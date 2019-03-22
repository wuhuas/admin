package com.knowledge.common.business.notice.entity;


import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 消息通知
 * </p>
 *
 * @author jide
 * @since 2018-12-05
 */
@TableName("tb_notice")
public class TbNotice extends Model<TbNotice> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("to_mem_id")
	private Integer toMemId;
    /**
     * 标题
     */
	private String title;
    /**
     * 内容
     */
	private String content;
    /**
     * 1已读 2未读
     */
	private Integer status;
	@TableField("create_time")
	private Date createTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getToMemId() {
		return toMemId;
	}

	public void setToMemId(Integer toMemId) {
		this.toMemId = toMemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
		return "TbNotice{" +
			", id=" + id +
			", toMemId=" + toMemId +
			", title=" + title +
			", content=" + content +
			", status=" + status +
			", createTime=" + createTime +
			"}";
	}
}
