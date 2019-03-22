package com.knowledge.admin.base.resource.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author francis
 */
@TableName("tb_resource")
public class TbResource extends Model<TbResource> {

    private static final long serialVersionUID = 1L;

	/**
	 * 资源id
	 */
	@TableId(value="s_id",type=IdType.AUTO)
	private Integer id;
	/**
	 * 资源父id
	 */
	@TableField(value="s_parent_id")
	private Integer parentId;
	/**
	 * 资源名称
	 */
	@TableField(value="s_name")
	private String name;
	/**
	 * 资源唯一标识
	 */
	@TableField(value="s_source_key")
	private String sourceKey;
	/**
	 * 资源类型,0:目录;1:菜单;2:按钮
	 */
	@TableField(value="s_type")
	private Integer type;
	/**
	 * 资源url
	 */
	@TableField(value="s_source_url")
	private String sourceUrl;
	/**
	 * 图标
	 */
	@TableField(value="s_icon")
	private String icon;
	/**
	 * 是否隐藏
	 */
	@TableField(value="s_is_hide")
	private Integer isHide;
	/**
	 * 描述
	 */
	@TableField(value="s_description")
	private String description;
	/**
	 * 创建时间
	 */
	@TableField(value="s_create_time")
	private Date createTime;
	/**
	 * 更新时间
	 */
	@TableField(value="s_update_time")
	private Date updateTime;
	
	@TableField(exist=false)
	private boolean selected;
	
	@TableField(exist=false)
	private String parentName;
	
	@TableField(exist=false)
	private List<TbResource> children = new ArrayList<>();
	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getParentId() {
		return parentId;
	}


	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSourceKey() {
		return sourceKey;
	}


	public void setSourceKey(String sourceKey) {
		this.sourceKey = sourceKey;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public String getSourceUrl() {
		return sourceUrl;
	}


	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}


	public Integer getIsHide() {
		return isHide;
	}


	public void setIsHide(Integer isHide) {
		this.isHide = isHide;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
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

	public List<TbResource> getChildren() {
		return children;
	}

	public void setChildren(List<TbResource> children) {
		this.children = children;
	}
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getParentName() {
		return parentName;
	}
	
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public TbResource() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TbResource(Integer id, Integer parentId, String name) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
	}


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
