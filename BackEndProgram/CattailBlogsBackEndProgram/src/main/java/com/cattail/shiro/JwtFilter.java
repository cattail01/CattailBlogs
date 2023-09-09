package com.cattail.shiro;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.stereotype.Repository;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/28 23:36
 * @description Jwt 过滤器
 */
@Repository
public class JwtFilter extends AuthenticatingFilter {
	@Override
	protected AuthenticationToken createToken(ServletRequest servletRequest,
	                                          ServletResponse servletResponse) throws Exception {
		return null;
	}
	
	@Override
	protected boolean onAccessDenied(ServletRequest servletRequest,
	                                 ServletResponse servletResponse) throws Exception {
		return false;
	}
}
