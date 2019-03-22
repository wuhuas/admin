package com.knowledge.common.business.draft.entity;


import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 征稿启示管理
 * </p>
 *
 * @author aron
 * @since 2018-12-03
 */
@TableName("tb_collection_draft_manage")
public class TbCollectionDraftManage extends Model<TbCollectionDraftManage> {

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
     * 内容
     */
	private String content;
    /**
     * 发布人
     */
	@TableField("release_user")
	private String releaseUser;
    /**
     * 发布时间
     */
	@TableField("release_time")
	private Date releaseTime;
    /**
     * 修改人
     */
	@TableField("update_user")
	private String updateUser;
    /**
     * 修改时间
     */
	@TableField(value = "update_time",fill = FieldFill.UPDATE)
	private Date updateTime;
    /**
     * 状态（2=下架  1=上架）
     */
	private Integer status;
    /**
     * 宣传图url
     */
	@TableField(value="cover_url",strategy = FieldStrategy.IGNORED)
	private String coverUrl;
    /**
     * 附件url
     */
	@TableField(value="enclosure_url",strategy = FieldStrategy.IGNORED)
	private String enclosureUrl;


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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}


	public String getReleaseUser() {
		return releaseUser;
	}

	public void setReleaseUser(String releaseUser) {
		this.releaseUser = releaseUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getEnclosureUrl() {
		return enclosureUrl;
	}

	public void setEnclosureUrl(String enclosureUrl) {
		this.enclosureUrl = enclosureUrl;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbCollectionDraftManage{" +
			", id=" + id +
			", title=" + title +
			", content=" + content +
			", releaseUser=" + releaseUser +
			", releaseTime=" + releaseTime +
			", updateUser=" + updateUser +
			", updateTime=" + updateTime +
			", status=" + status +
			", coverUrl=" + coverUrl +
			", enclosureUrl=" + enclosureUrl +
			"}";
	}
}
