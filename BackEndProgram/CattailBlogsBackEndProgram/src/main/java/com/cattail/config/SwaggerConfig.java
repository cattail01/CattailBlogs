package com.cattail.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/24 12:40
 * @description swagger 配置类
 */
@Configuration  // 配置文件
public class SwaggerConfig {
	
	private final String title = "迎春博客系统";
	
	private final String description = "winter jasmine （welcome spring in chinese） system swagger";
	
	private final String version = "1.0.0";
	
	/**
	 * swagger 许可证
	 *
	 * @return 许可证（License）
	 */
	private License swaggerLicense() {
		return new License()
				       // license 名称
				       .name("Apache 2.0")
				       // license 地址
				       .url("http://springdoc.org")
				;
	}
	
	/**
	 * swagger 信息
	 *
	 * @return swagger info
	 */
	private Info swaggerInfo() {
		return new Info()
				       .title(title)
				       .description(description)
				       .version(version)
				       .license(swaggerLicense())
				;
	}
	
	/**
	 * swagger 生成外部文档
	 *
	 * @return ExternalDocumentation
	 */
	private ExternalDocumentation swaggerExternalDocumentation() {
		return new ExternalDocumentation();
	}
	
	/**
	 * swagger 配置bean方法
	 */
	@Bean
	public OpenAPI springShopOpenApi() {
		return new OpenAPI()
				       .info(swaggerInfo())
				       .externalDocs(swaggerExternalDocumentation())
				;
	}
}
