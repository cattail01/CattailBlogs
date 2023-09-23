package com.cattail.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/9/23 12:42
 * @description 解决跨域问题
 */

@Configuration
public class CorsConfig implements WebMvcConfigurer {
	@Override public void addCorsMappings(CorsRegistry registry) {
		WebMvcConfigurer.super.addCorsMappings(registry);
		registry.addMapping("/**")
				.allowedOriginPatterns("*")
				.allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
				.allowCredentials(true)
				.maxAge(3600)
				.allowedHeaders("*")
		;
	}
}
