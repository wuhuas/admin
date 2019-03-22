package com.knowledge.common.business.ad.entity;


import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 广告列表
 * </p>
 *
 * @author aron
 * @since 2018-12-03
 */
@TableName("tb_ad")
public class TbAd extends Model<TbAd> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 类别ID
     */
	@TableField("type_id")
	private Integer typeId;
    /**
     * 标题
     */
	private String title;
    /**
     * 权重
     */
	private Integer weight;
    /**
     * 图片Url
     */
	@TableField("picture_url")
	private String pictureUrl;
	
	@TableField("url_type")
	private Integer urlType;
	
	@TableField("modular_type")
	private Integer modularType;
	
    /**
     * 跳转Url
     */
	@TableField("jump_url")
	private String jumpUrl;

	@TableField("create_user")
	private String createUser;
	
	@TableField(value = "create_time",fill = FieldFill.INSERT)
	private Date createTime;
	
	@TableField("update_user")
	private String updateUser;
	
	@TableField(value = "update_time",fill = FieldFill.UPDATE)
	private Date updateTime;
	
	@TableField(exist = false)
	private String typeName;
	
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUrlType() {
		return urlType;
	}

	public void setUrlType(Integer urlType) {
		this.urlType = urlType;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
	
	
	
	

	public Integer getModularType() {
		return modularType;
	}

	public void setModularType(Integer modularType) {
		this.modularType = modularType;
	}

	@Override
	public String toString() {
		return "TbAd [id=" + id + ", typeId=" + typeId + ", title=" + title + ", weight=" + weight + ", pictureUrl="
				+ pictureUrl + ", urlType=" + urlType + ", modularType=" + modularType + ", jumpUrl=" + jumpUrl
				+ ", createUser=" + createUser + ", createTime=" + createTime + ", updateUser=" + updateUser
				+ ", updateTime=" + updateTime + ", typeName=" + typeName + "]";
	}



	
	
	
}
