package com.fish;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fish.dao")
public class FishBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishBootApplication.class, args);
	}
}
