package com.cattail.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/19 22:14
 * @description 文章实体类
 */

@Data  // lombok注解
@TableName(value = "blog_blogs")
public class BlogBlogs {
	
	/**
	 * 表格id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	
	/**
	 * 用户id
	 */
	@NotBlank(message = "用户id不能为空")
	private Long userId;
	
	/**
	 * 标题
	 */
	@NotBlank(message = "标题不能为空")
	private String title;
	
	/**
	 * 名称
	 */
	private String description;
	
	/**
	 * 内容
	 */
	@NotBlank(message = "内容不能为空")
	private String context;
	
	/**
	 * 创建时间
	 */
	@NotBlank(message = "创建时间不能为空")
	private Timestamp created;
	
	/**
	 * 状态
	 */
	private Integer status;
}
