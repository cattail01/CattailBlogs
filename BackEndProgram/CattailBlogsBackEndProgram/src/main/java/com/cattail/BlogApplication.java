package com.cattail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/12 19:03
 * @description spring boot 启动类
 */

@SpringBootApplication  // 启动注解
public class BlogApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
		System.out.println("Spring boot jar empty project");
	}
	
}
