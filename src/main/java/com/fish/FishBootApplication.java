package com.fish;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.fish.dao")
public class FishBootApplication  extends SpringBootServletInitializer{

	/**
	 * 重写configure方法
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FishBootApplication.class);
	}
	
	/**
	 * 程序主函数
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(FishBootApplication.class, args);
	}
}
