package com.knowledge.admin.base.config.mybatis;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;

/**
 * 自定义公共字段自动填充
 * 
 * @author francis
 *
 */
public class MyMetaObjectHandler extends MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		if(metaObject.hasGetter("createTime")) {
			System.out.println("*************************");
			System.out.println("insert fill");
			System.out.println("*************************");
			if(getFieldValByName("createTime",metaObject) == null) {
				setFieldValByName("createTime", new Date(), metaObject);
			}
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		if(metaObject.hasGetter("updateTime")) {
			System.out.println("*************************");
		    System.out.println("update fill");
		    System.out.println("*************************");
		    if(getFieldValByName("updateTime",metaObject) == null) {
				setFieldValByName("updateTime", new Date(), metaObject);
			}
		}
	}

}
