package com.knowledge.common.business.message.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 留言管理
 * </p>
 *
 * @author francis
 * @since 2018-12-03
 */
@TableName("tb_message_manage")
public class TbMessageManage extends Model<TbMessageManage> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 资讯ID
     */
	@TableField("info_id")
	private Integer infoId;
    /**
     * 留言内容
     */
	private String content;
    /**
     * 留言时间
     */
	@TableField("message_time")
	private Date messageTime;

	@TableField("mem_id")
	private Integer memId;
	/**
	 * 资讯标题
	 */
	@TableField(exist = false)
	private String infoTitle;
	/**
	 * 留言人
	 */
	@TableField(exist = false)
	private String messageName;
	
	@TableField(exist = false)
	private String messageImage;
	
	@TableField(exist = false)
	private Integer messageSex;
	
	

	

	public Integer getMessageSex() {
		return messageSex;
	}

	public void setMessageSex(Integer messageSex) {
		this.messageSex = messageSex;
	}

	public String getMessageImage() {
		return messageImage;
	}

	public void setMessageImage(String messageImage) {
		this.messageImage = messageImage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInfoId() {
		return infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	public String getInfoTitle() {
		return infoTitle;
	}

	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMessageName() {
		return messageName;
	}

	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}

	public Date getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(Date messageTime) {
		this.messageTime = messageTime;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbMessageManage{" +
			", id=" + id +
			", infoId=" + infoId +
			", infoTitle=" + infoTitle +
			", content=" + content +
			", messageName=" + messageName +
			", messageTime=" + messageTime +
			"}";
	}
}
