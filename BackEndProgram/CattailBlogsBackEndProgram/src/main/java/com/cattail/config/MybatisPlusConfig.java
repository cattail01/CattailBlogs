package com.cattail.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/16 23:12
 * @description mybatis plus config class
 */

@Configuration  // 配置
@EnableTransactionManagement  // 开启事务支持
@MapperScan("com.cattail.dao.mapper")  // 检查mapper路径
public class MybatisPlusConfig {
	
	/**
	 * 分页插件配置
	 * todo 后期配置多个分页插件，并学习相关知识点
	 */
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor(){
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		PaginationInnerInterceptor mysqlInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
		interceptor.addInnerInterceptor(mysqlInnerInterceptor);
		return interceptor;
	}
}
