package com.knowledge.api.base.config.mybatis;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;

@Component
public class MyMetaObjectHandler extends MetaObjectHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyMetaObjectHandler.class);

	@Override
	public void insertFill(MetaObject metaObject) {
		LOGGER.info("start insert fill ....");
		if(metaObject.hasGetter("createTime")) {
			Object createTime = getFieldValByName("createTime", metaObject);
			if (createTime == null) {
				this.setFieldValByName("createTime", new Date(), metaObject);
			}
		}
		
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		LOGGER.info("start update fill ....");
	if(metaObject.hasGetter("updateTime")) {
		Object updateTime = getFieldValByName("updateTime", metaObject);
		if (updateTime == null) {
			this.setFieldValByName("updateTime", new Date(), metaObject);
		}
			}
		
	}

}