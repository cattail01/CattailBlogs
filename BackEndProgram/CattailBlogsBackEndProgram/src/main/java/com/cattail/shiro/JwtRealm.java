package com.cattail.shiro;

import com.cattail.service.service.BlogUsersService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/26 15:58
 * @description shiro realm class。身份验证和授权规则封装类
 */

@Repository // 注入spring 容器，可以通过realm类型直接获取
public class JwtRealm extends AuthorizingRealm {
	
	@Autowired
	BlogUsersService blogUsersService;
	
	/**
	 * 授权
	 * 获取权限信息，得到信息后封装，并发出返回值给shiro
	 *
	 * @param principalCollection 权限信息
	 * @return 权限封装
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
	}
	
	/**
	 * 身份验证
	 * 拿到token之后进行验证
	 * 然后返回基本信息
	 *
	 * @param authenticationToken token
	 * @return 基本信息
	 * @throws AuthenticationException 验证失败抛出异常
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
	throws AuthenticationException {
		return null;
	}
}
