package com.knowledge.common.business.complimentary.entity;


import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 赠阅期刊
 * </p>
 *
 * @author xiong
 * @since 2019-01-15
 */
@TableName("tb_complimentary_periodical")
public class TbComplimentaryPeriodical extends Model<TbComplimentaryPeriodical> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 杂志名称
     */
	private String name;
    /**
     * 编者的话
     */
	private String content;
	
	/**
	 * 出版期數
	 */
	@TableField("periods_number")
	private Integer periodsNumber;
	/**
	 * 發行月
	 */
	@TableField("release_month")
	private String releaseMonth;
	/**
	 * 發佈人
	 */
	@TableField("release_name")
	private String releaseName;
	/**
	 * 發佈時間
	 */
	@TableField("release_time")
	private Date releaseTime;
	
    /**
     * 封面链接
     */
	@TableField("cover_url")
	private String coverUrl;
    /**
     * 杂志链接
     */
	@TableField("magazine_url")
	private String magazineUrl;
    /**
     * 查阅密码
     */
	private String password;
	@TableField("create_user")
	private String createUser;
	@TableField("create_time")
	private Date createTime;
	@TableField("update_user")
	private String updateUser;
	@TableField("update_time")
	private Date updateTime;

	
	/**
	 * 閱讀量
	 */
	@TableField("reading_volume")
	private Integer readingVolume;
	
	/**
	 * 狀態 0=下架 1=上架
	 */
	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getMagazineUrl() {
		return magazineUrl;
	}

	public void setMagazineUrl(String magazineUrl) {
		this.magazineUrl = magazineUrl;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public Integer getPeriodsNumber() {
		return periodsNumber;
	}

	public void setPeriodsNumber(Integer periodsNumber) {
		this.periodsNumber = periodsNumber;
	}

	public String getReleaseMonth() {
		return releaseMonth;
	}

	public void setReleaseMonth(String releaseMonth) {
		this.releaseMonth = releaseMonth;
	}

	public String getReleaseName() {
		return releaseName;
	}

	public void setReleaseName(String releaseName) {
		this.releaseName = releaseName;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public Integer getReadingVolume() {
		return readingVolume;
	}

	public void setReadingVolume(Integer readingVolume) {
		this.readingVolume = readingVolume;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TbComplimentaryPeriodical [id=" + id + ", name=" + name + ", content=" + content + ", periodsNumber="
				+ periodsNumber + ", releaseMonth=" + releaseMonth + ", releaseName=" + releaseName + ", releaseTime="
				+ releaseTime + ", coverUrl=" + coverUrl + ", magazineUrl=" + magazineUrl + ", password=" + password
				+ ", createUser=" + createUser + ", createTime=" + createTime + ", updateUser=" + updateUser
				+ ", updateTime=" + updateTime + ", readingVolume=" + readingVolume + ", status=" + status + "]";
	}





	
	
	
	
}
