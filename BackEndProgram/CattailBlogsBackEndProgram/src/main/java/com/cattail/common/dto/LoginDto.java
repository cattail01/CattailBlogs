package com.cattail.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/9/24 23:12
 * @description
 * 登录数据传输对象
 * 用于在登录时传输登录的数据
 */
@Data
public class LoginDto {
	
	/**
	 * 用户名称
	 */
	@NotBlank(message = "昵称不能为空")
	private String username;
	
	/**
	 * 密码
	 */
	@NotBlank(message = "密码不能为空")
	private String password;
	
}
