package com.knowledge.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.knowledge"})
@MapperScan(value = "com.knowledge.**.mapper*")
@EnableAsync
public class Application {
	
	public static void main(String[] args) {
		// 程序启动入口
		SpringApplication.run(Application.class, args);
	}
}
