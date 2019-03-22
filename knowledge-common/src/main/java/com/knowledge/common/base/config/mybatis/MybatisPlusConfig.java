package com.knowledge.common.base.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;

@Configuration
@MapperScan(value = "com.knowledge.common.**.dao")
public class MybatisPlusConfig {

	/**
	 * 配置mybatisPlus分页插件
	 * 
	 * @param bean
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor page = new PaginationInterceptor();
		page.setDialectType("mysql");
		return page;
	}
	
}
