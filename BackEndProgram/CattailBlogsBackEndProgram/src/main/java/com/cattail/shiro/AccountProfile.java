package com.cattail.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/9/10 16:07
 * @description
 */

@Data
public class AccountProfile implements Serializable {
	private Long id;
	private String username;
	private String avatar;
	private String email;
}
