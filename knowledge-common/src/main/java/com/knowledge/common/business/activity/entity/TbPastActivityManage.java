package com.knowledge.common.business.activity.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 以往活动管理
 * </p>
 *
 * @author xiong
 * @since 2018-11-14
 */
@TableName("tb_past_activity_manage")
public class TbPastActivityManage extends Model<TbPastActivityManage> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
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
	 * 状态(1=上架 0=下架)
	 */
	private Integer status;
	/**
	 * 关联往期活动ID，格式：1,2,3 最多关联三个活动
	 */
	@TableField(strategy = FieldStrategy.IGNORED)
	private String relation;
	
	@TableField(exist = false)
	private String activityNames;

	@TableField("cover_url")
	private String coverUrl;
	
	/**
	 * 视频链接
	 */
	@TableField("video_url")
	private String videoUrl;
	/**
	 * 附件链接
	 */
	@TableField("enclosure_url")
	private String enclosureUrl;
	/**
	 * 发布人
	 */
	@TableField("release_name")
	private String releaseName;
	/**
	 * 发布时间
	 */
	@TableField("release_time")
	private Date releaseTime;
	
	/**
	 * 活动时间（开始时间）
	 */
	@TableField(value="activity_time",strategy = FieldStrategy.IGNORED)
	private Date activityTime;
	
	/**
	 * 活动时间（结束时间）
	 */
	@TableField(value="activity_time_end",strategy = FieldStrategy.IGNORED)
	private Date activityTimeEnd;
	
	
	/**
	 * 回顾活动ID
	 */
	@TableField(exist=false)
	private List<String> activityPastId;
	
	/**
	 * 回顾活动名字
	 */
	@TableField(exist=false)
	private List<String> activityPastName;
	
	

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getEnclosureUrl() {
		return enclosureUrl;
	}

	public void setEnclosureUrl(String enclosureUrl) {
		this.enclosureUrl = enclosureUrl;
	}

	public String getReleaseName() {
		return releaseName;
	}

	public void setReleaseName(String releaseName) {
		this.releaseName = releaseName;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public List<String> getActivityPastId() {
		return activityPastId;
	}

	public void setActivityPastId(List<String> activityPastId) {
		this.activityPastId = activityPastId;
	}

	public List<String> getActivityPastName() {
		return activityPastName;
	}

	public void setActivityPastName(List<String> activityPastName) {
		this.activityPastName = activityPastName;
	}


	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getActivityNames() {
		return activityNames;
	}

	public void setActivityNames(String activityNames) {
		this.activityNames = activityNames;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public Date getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}

	public Date getActivityTimeEnd() {
		return activityTimeEnd;
	}

	public void setActivityTimeEnd(Date activityTimeEnd) {
		this.activityTimeEnd = activityTimeEnd;
	}

	@Override
	public String toString() {
		return "TbPastActivityManage [id=" + id + ", title=" + title + ", content=" + content + ", status=" + status
				+ ", relation=" + relation + ", activityNames=" + activityNames + ", coverUrl=" + coverUrl
				+ ", videoUrl=" + videoUrl + ", enclosureUrl=" + enclosureUrl + ", releaseName=" + releaseName
				+ ", releaseTime=" + releaseTime + ", activityTime=" + activityTime + ", activityTimeEnd="
				+ activityTimeEnd + ", activityPastId=" + activityPastId + ", activityPastName=" + activityPastName
				+ "]";
	}



	
	
}
