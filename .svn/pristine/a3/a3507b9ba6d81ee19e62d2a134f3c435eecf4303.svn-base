package com.knowledge.common.base.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.MapUtils;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.feilong.core.Validator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "pager实体对象", description = "用作分页，排序，条件模糊查询")
public class Pager<T> {

	/**
	 * 当前页数
	 */
	@ApiModelProperty(value = "当前页数", example = "1")
	private int nowPage = 1;

	/**
	 * 一页10条数据
	 */
	@ApiModelProperty(value = "显示数据条数", example = "10")
	private int pageSize = 10;

	/**
	 * 排序字段
	 */
	@ApiModelProperty(value = "排序字段", example = " ")
	private String sort;

	/**
	 * 升序或降序（默认升序）
	 */
	@ApiModelProperty(value = "是否升序(默认降序)", example = "true")
	private boolean asc = false;

	/**
	 * 条件查询
	 */
	private Map<String, Object> map;

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	// 获取mybatisPlus封装的Page对象
	@JsonIgnore
	public Page<T> getPagePlus() {
		Page<T> pagePlus = new Page<T>();
		pagePlus.setCurrent(this.nowPage);
		pagePlus.setSize(this.pageSize);
		pagePlus.setAsc(this.asc);
		pagePlus.setOrderByField(this.sort);
		return pagePlus;
	}

	/**
	 * 为true时开启所有条件模糊查询
	 * 
	 * @param condition
	 *            传入wrapper对象
	 * @param wrapper
	 *            传入所要模糊查询的map
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Wrapper<T> allLike(boolean condition, Wrapper<T> wrapper) {
		if (condition && MapUtils.isNotEmpty(map)) {
			Iterator iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator.next();
				Object value = entry.getValue();
				if (StringUtils.checkValNotNull(value)) {
					wrapper.like(entry.getKey(), (String) value);
				}
			}

		}
		return wrapper;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Wrapper<T> allLike(Wrapper<T> wrapper, Map params) {
		if (MapUtils.isNotEmpty(params)) {
			Iterator iterator = params.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator.next();
				Object value = entry.getValue();
				if (StringUtils.checkValNotNull(value)) {
					wrapper.like("upper(" + entry.getKey() + ")", ((String) value).toUpperCase());
				}
			}
		}
		return wrapper;
	}

	/**
	 * 将Map中的key由驼峰转换为下划线
	 *
	 * @param map
	 * @return
	 */
	@JsonIgnore
	public Map<String, Object> getFormatMap() {
		Map<String, Object> newMap = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = toFormatCol(key);
			newMap.put(newKey, entry.getValue());
		}
		return newMap;
	}

	public static String toFormatCol(String value) {
		if (Validator.isNullOrEmpty(value)) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			boolean nextUpperCase = true;
			if (i < (value.length() - 1)) {
				nextUpperCase = Character.isUpperCase(value.charAt(i + 1));
			}
			if ((i >= 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					if (i > 0)
						sb.append("_");
				}
				upperCase = true;
			} else {
				upperCase = false;
			}

			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}
}
