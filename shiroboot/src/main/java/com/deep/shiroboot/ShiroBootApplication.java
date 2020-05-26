package com.deep.shiroboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan
@SpringBootApplication
@MapperScan(basePackages = {"com.deep.shiroboot.mapper"})
public class ShiroBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShiroBootApplication.class, args);
	}

}
