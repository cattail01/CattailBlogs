package com.cattail.config;

import com.cattail.shiro.JwtRealm;
import com.cattail.shiro.JwtFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/26 15:42
 * @description shiro config class
 * 整合了redis
 * 整合了jwt，并关闭了自带的session校验
 */
public class ShiroConfig {
	
	// 引入RedisSessionDAO和RedisCacheManager，为了解决shiro的权限数据和会话信息能保存到redis中，实现会话共享。
	
	/**
	 * redis 中 session 类型数据操作器
	 * 用于将管理 session 的功能移交给 redis
	 */
	@Autowired
	private RedisSessionDAO redisSessionDAO;
	
	/**
	 * redis 缓存管理器
	 * 用于将 cache 管理的功能移交给 redis
	 */
	@Autowired
	private RedisCacheManager redisCacheManager;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	
	/**
	 * 过滤器链生成方法
	 * 该方法需要经常更改
	 *
	 * @return 过滤器链map
	 */
	private Map<String, String> createFilterMap() {
		Map<String, String> filterMap = new HashMap<>();
		filterMap.put("/**", "jwt");
		return filterMap;
	}
	
	
	/**
	 * 配置Shiro过滤器链定义的方法
	 * DefaultShiroFilterChainDefinition 实现了 filterChainDefinitions接口
	 * 使用DefaultShiroFilterChainDefinition来配置过滤规则
	 *
	 * @return DefaultShiroFilterChainDefinition 配置规则
	 */
	@Bean
	public ShiroFilterChainDefinition shiroFilterChainDefinition() {
		DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
		Map<String, String> filterMap = createFilterMap();
		chainDefinition.addPathDefinitions(filterMap);
		return chainDefinition;
	}
	
	// region shiro redis starter config
	
	// 重写了SessionManager和DefaultWebSecurityManager，
	// 同时在DefaultWebSecurityManager中为了关闭shiro自带的session方式，
	// 我们需要设置为false，这样用户就不再能通过session方式登录shiro。后面将采用jwt凭证登录。
	
	/**
	 * 配置 shiro session 管理器
	 * 将 redis dao 替换到 shiro session dao 中
	 *
	 * @return SessionManager
	 */
	@Bean  // 表示spring boot 中已经存在一个bean，该方法可以对该bean进行替换
	public SessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		
		// inject redisSessionDAO
		// 设置 shiro 的 session 管理器（DAO）为 redis session dao
		sessionManager.setSessionDAO(redisSessionDAO);
		
		// other stuff...
		
		return sessionManager;
	}
	
	/**
	 * 安全管理器（核心配置）
	 *
	 * @param realm         身份验证和授权规则封装类
	 * @param sessionManager 绘画管理器（spring bean）
	 * @return SessionsSecurityManager
	 */
	@Bean
//	public SessionsSecurityManager securityManager(List<Realm> realm, SessionManager sessionManager) {
	public SessionsSecurityManager securityManager(
			@Qualifier("AccountRealm")
			JwtRealm realm, SessionManager sessionManager) {
		
		// 使用自定义的身份验证和身份授权方法创建DefaultWebSecurityManager对象
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(realm);
		
		// inject sessionManager
		// 将Session Manager bean进行注入
		// （就是前面配置好的）
		securityManager.setSessionManager(sessionManager);
		
		// inject redisCacheManager
		// 将缓存管理器替换为 redis 缓存管理器
		securityManager.setCacheManager(redisCacheManager);
		
		// other stuff...
		
		// 关闭shiro自带的session
		DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
		DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
		defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
		subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
		securityManager.setSubjectDAO(subjectDAO);
		
		return securityManager;
	}
	
	// endregion shiro redis starter config

	// 在ShiroFilterChainDefinition中，我们不再通过编码形式拦截Controller访问路径，
	// 而是所有的路由都需要经过JwtFilter这个过滤器，然后判断请求头中是否含有jwt的信息，
	// 有就登录，没有就跳过。
	// 跳过之后，有Controller中的shiro注解进行再次拦截，比如@RequiresAuthentication，这样控制权限访问。

	/**
	 *
	 * @param securityManager
	 * @param shiroFilterChainDefinition
	 * @return
	 */
	@Bean("shiroFilterFactoryBean")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager,
	                                                     ShiroFilterChainDefinition shiroFilterChainDefinition) {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);
		Map<String, Filter> filters = new HashMap<>();
		filters.put("jwt", jwtFilter);
		shiroFilter.setFilters(filters);
		Map<String, String> filterMap = shiroFilterChainDefinition.getFilterChainMap();
		shiroFilter.setFilterChainDefinitionMap(filterMap);
		return shiroFilter;
	}
	
	/**
	 * 开启注解代理
	 * （默认已经开启，可以不用写）
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
				new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
	
	@Bean
	public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		return creator;
	}
}
