package com.cattail.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/30 22:44
 * @description jwt token 数据
 */
public class JwtToken implements AuthenticationToken {
	
	/**
	 * token数据
	 */
	private String token;
	
	public JwtToken(String token) {
		this.token = token;
	}
	
	@Override
	public Object getPrincipal() {
		return token;
	}
	
	@Override
	public Object getCredentials() {
		return token;
	}
}
