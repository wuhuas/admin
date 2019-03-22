package com.knowledge.common.business.activity.entity;

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
 * 活动管理
 * </p>
 *
 * @author xiong
 * @since 2018-11-14
 */
@TableName("tb_activity_manage")
public class TbActivityManage extends Model<TbActivityManage> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 活动名称
     */
	@TableField("activity_name")
	private String activityName;
    /**
     * 报名开始时间
     */
	@TableField("enroll_start_time")
	private Date enrollStartTime;
    /**
     * 报名结束时间
     */
	@TableField("enroll_end_time")
	private Date enrollEndTime;
    /**
     * 活动开始时间
     */
	@TableField("activity_start_time")
	private Date activityStartTime;
    /**
     * 活动结束时间
     */
	@TableField("activity_end_time")
	private Date activityEndTime;
    /**
     * 活动内容
     */
	@TableField("activity_content")
	private String activityContent;
    /**
     * 状态（1=开放 0=关闭）
     */
	private Integer status;
	
	/**
     * 收费活动(1=收费 0=免费)
     */
	private Integer isCost;
    /**
     * 費用
     */
	private Double cost;
    /**
     * 附件链接
     */
	@TableField(value="enclosure_url",strategy = FieldStrategy.IGNORED)
	private String enclosureUrl;
    /**
     * 创建人
     */
	@TableField(value = "create_user")
	private String createUser;
    /**
     * 创建时间
     */
	@TableField(value = "create_time",fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 修改世間
	 */
	@TableField(value = "update_time",fill = FieldFill.UPDATE)
	private Date updateTime;
	/**
	 * 修改人
	 */
	@TableField(value = "update_user")
	private String updateUser;
	
	@TableField(value = "cover_url",strategy = FieldStrategy.IGNORED)
	private String coverUrl;
	
	private Integer type;
	
	@TableField(value = "activity_url",strategy = FieldStrategy.IGNORED)
	private String activityUrl;

	/**
	 * 活动流程(0=活动未开始 1=活动报名中 2=活动进行中 3=活动已结束)
	 */
	private Integer step;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Date getEnrollStartTime() {
		return enrollStartTime;
	}

	public void setEnrollStartTime(Date enrollStartTime) {
		this.enrollStartTime = enrollStartTime;
	}

	public Date getEnrollEndTime() {
		return enrollEndTime;
	}

	public void setEnrollEndTime(Date enrollEndTime) {
		this.enrollEndTime = enrollEndTime;
	}

	public Date getActivityStartTime() {
		return activityStartTime;
	}

	public void setActivityStartTime(Date activityStartTime) {
		this.activityStartTime = activityStartTime;
	}

	public Date getActivityEndTime() {
		return activityEndTime;
	}

	public void setActivityEndTime(Date activityEndTime) {
		this.activityEndTime = activityEndTime;
	}

	public String getActivityContent() {
		return activityContent;
	}

	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getEnclosureUrl() {
		return enclosureUrl;
	}

	public void setEnclosureUrl(String enclosureUrl) {
		this.enclosureUrl = enclosureUrl;
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
	
	public Integer getIsCost() {
		return isCost;
	}

	public void setIsCost(Integer isCost) {
		this.isCost = isCost;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getActivityUrl() {
		return activityUrl;
	}

	public void setActivityUrl(String activityUrl) {
		this.activityUrl = activityUrl;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	@Override
	public String toString() {
		return "TbActivityManage [id=" + id + ", activityName=" + activityName + ", enrollStartTime=" + enrollStartTime
				+ ", enrollEndTime=" + enrollEndTime + ", activityStartTime=" + activityStartTime + ", activityEndTime="
				+ activityEndTime + ", activityContent=" + activityContent + ", status=" + status + ", isCost=" + isCost
				+ ", cost=" + cost + ", enclosureUrl=" + enclosureUrl + ", createUser=" + createUser + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", updateUser=" + updateUser + ", coverUrl=" + coverUrl
				+ ", type=" + type + ", activityUrl=" + activityUrl + ", step=" + step + "]";
	}


	
	
	
	
	
	
}
