package com.SpringBoot.MBlog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync//开启异步
@EnableCaching//开启缓存
@SpringBootApplication
@ServletComponentScan//开启servlet配置
@EnableScheduling//开启定时器
@MapperScan("com.SpringBoot.MBlog.Admin.mapper")
public class MBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MBlogApplication.class, args);
	}

}
