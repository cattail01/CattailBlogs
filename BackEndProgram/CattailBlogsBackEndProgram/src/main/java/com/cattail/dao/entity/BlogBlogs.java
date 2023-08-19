package com.cattail.dao.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/19 22:14
 * @description 文章实体类
 */

@Data  // lombok注解
public class BlogBlogs {
	private Long id;
	private Long userId;
	private String title;
	private String description;
	private String context;
	private Timestamp created;
	private Integer status;
}
