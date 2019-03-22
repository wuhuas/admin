package com.knowledge.common.base.model;

import java.io.Serializable;

/**
 * 報表返回vo
 * @author francis
 *
 */
public class ReportVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5748750040173796605L;

	private Integer total;
	
	private String date;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ReportVo(Integer total, String date) {
		super();
		this.total = total;
		this.date = date;
	}

	public ReportVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ReportVo [total=" + total + ", date=" + date + "]";
	}
	
}
