package com.knowledge.common.business.principalEnroll.entity;


import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 校长报名表 顶部选项
 * </p>
 *
 * @author xiong
 * @since 2019-03-12
 */
@TableName("tb_principal_enroll_option")
public class TbPrincipalEnrollOption extends Model<TbPrincipalEnrollOption> {

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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbPrincipalEnrollOption{" +
			", id=" + id +
			", title=" + title +
			"}";
	}
}
