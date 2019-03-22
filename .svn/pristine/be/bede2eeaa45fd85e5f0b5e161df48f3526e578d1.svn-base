package com.knowledge.common.business.school.entity;


import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 校历推荐
 * </p>
 *
 * @author aron
 * @since 2018-12-05
 */
@TableName("tb_school_calendar_recommend")
public class TbSchoolCalendarRecommend extends Model<TbSchoolCalendarRecommend> {

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
     * 学校
     */
	private String school;
    /**
     * 活动日期
     */
	@TableField("activity_date")
	private Date activityDate;
    /**
     * 创建人
     */
	@TableField("create_name")
	private String createName;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 状态（0=下架 1=上架）
     */
	private Integer status;
    /**
     * 内容
     */
	private String content;
    /**
     * 外链url跳转
     */
	@TableField("outer_chain_url")
	private String outerChainUrl;
	
	
	/**
	 * 校曆類型  1=內部校曆  2=外部校曆
	 * @return
	 */
	private Integer type;


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

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOuterChainUrl() {
		return outerChainUrl;
	}

	public void setOuterChainUrl(String outerChainUrl) {
		this.outerChainUrl = outerChainUrl;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "TbSchoolCalendarRecommend [id=" + id + ", title=" + title + ", school=" + school + ", activityDate="
				+ activityDate + ", createName=" + createName + ", createTime=" + createTime + ", status=" + status
				+ ", content=" + content + ", outerChainUrl=" + outerChainUrl + ", type=" + type + "]";
	}



	
	
	
	
	
}
