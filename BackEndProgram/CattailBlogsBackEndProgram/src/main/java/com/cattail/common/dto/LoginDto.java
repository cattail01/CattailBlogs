package com.cattail.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/9/24 23:12
 * @description 登录数据传输对象
 */
@Data
public class LoginDto {
	
	@NotBlank(message = "昵称不能为空")
	private String username;
	
	@NotBlank(message = "密码不能为空")
	private String password;
	
}
