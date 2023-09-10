package com.cattail.shiro;

import com.cattail.dao.entity.BlogUser;
import com.cattail.service.service.BlogUsersService;
import com.cattail.utility.JwtUtilities;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
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
	
	@Autowired
	JwtUtilities jwtUtilities;
	
	@Override
	public boolean supports(AuthenticationToken token) {
//		return super.supports(token);
		return token instanceof JwtToken;
	}
	
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
	 * 对传入的token进行验证
	 * 然后返回基本信息
	 *
	 * @param authenticationToken token
	 * @return 基本信息
	 * @throws AuthenticationException 验证失败抛出异常
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
	throws AuthenticationException {
		
		// 分析传入参数信息
		
		// 将传入的token转换为jwttoken
		JwtToken jwtToken = (JwtToken) authenticationToken;
		String userId = jwtUtilities.getClaimsByToken((String) jwtToken.getPrincipal()).getSubject();
		BlogUser user = blogUsersService.getById(Long.valueOf(userId));
		
		if(user == null){
			throw new UnknownAccountException("账户不存在");
		}
		if(user.getStatus() == -1){
			throw new LockedAccountException("账户已被锁定");
		}
		
		AccountProfile profile = new AccountProfile();
		BeanUtils.copyProperties(user, profile);
		
		return new SimpleAuthenticationInfo(profile, jwtToken.getCredentials(), getName());
	}
}
