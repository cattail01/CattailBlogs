package com.cattail.controller.projectcontroller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cattail.common.dto.LoginDto;
import com.cattail.dao.entity.BlogUser;
import com.cattail.service.service.BlogUsersService;
import com.cattail.utility.JwtUtilities;
import com.cattail.utility.Result;
import com.cattail.utility.ResultCodeType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/9/24 22:03
 * @description 用户登录接口
 */
@RestController
public class AccountController {
	
	/**
	 * 用户服务
	 */
	@Autowired
	BlogUsersService usersService;
	
	/**
	 * jwt工具类
	 */
	@Autowired
	JwtUtilities jwtUtilities;
	
	/**
	 * 根据输入数据获取获取数据库user对象
	 *
	 * @param loginDto
	 * @return
	 */
	private BlogUser getBlogUser(LoginDto loginDto) {
		// 创建查询对象
		QueryWrapper<BlogUser> queryWrapper = new QueryWrapper<>();
		// 查询对象包含查询条件
		// 查询条件：在username中查询目标用户名
		QueryWrapper<BlogUser> queryWrapperUserName = queryWrapper.eq("username", loginDto.getUsername());
		// 使用查询对象在userservice中查询，返回
		BlogUser blogUser = usersService.getOne(queryWrapperUserName);
		return blogUser;
	}
	
	/**
	 * 获得用户输入的密码，返回加密后的密码
	 * 加密采用md5方式
	 *
	 * @param loginDto 登录数据传输对象
	 * @return 用户输入的密码
	 */
	private String getUserInputPassword(LoginDto loginDto) {
		// 从loginDto登录数据传输对象中获取
		String userInputPassword = loginDto.getPassword();
		System.out.println(userInputPassword);
		// md5加密，使用hutool工具
		userInputPassword = SecureUtil.md5(userInputPassword);
		// 返回密码
		return userInputPassword;
	}
	
	/**
	 * 提取token
	 *
	 * @param response 响应数据
	 * @param blogUser 用户数据
	 * @description 通过用户数据提取user id，然后根据该id获取相应token，并将其提交给相应数据的头
	 */
	private void extracted(HttpServletResponse response, BlogUser blogUser) {
		// 获取userid，并通过userid获取相应token数据
		String jwt = jwtUtilities.generateToken(blogUser.getId());
		// 将token提交给响应数据的头
		response.setHeader("Authorization", jwt);
		response.setHeader("Access-control-Expose-Headers", "Authorization");
	}
	
	/**
	 * 返回相应数据result
	 *
	 * @param blogUser user数据信息
	 * @return result，内部包含一个键值对的map
	 * @description 传入user数据，将id、username、avater、email用hutool的工具组成map并封装进result类中
	 */
	private static Result getResult(BlogUser blogUser) {
		return Result.succ(MapUtil.builder()
		                          .put("id", blogUser.getId())
		                          .put("username", blogUser.getUsername())
		                          .put("avater", blogUser.getAvatar())
		                          .put("email", blogUser.getEmail())
		                  );
	}
	
	/**
	 * 登录接口
	 *
	 * @param loginDto 登录信息传输对象
	 * @param response 响应信息
	 * @return 返回用户信息
	 * @description
	 */
	@PostMapping("/login")
	public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
		
		// 获取用户信息
		BlogUser blogUser = getBlogUser(loginDto);
		// 用户检查是否存在
		if(blogUser == null){
			throw new RuntimeException("传输错误");
		}
		Assert.notNull(blogUser, "用户不存在");
		
		// 获取接收到的用户密码
		String userInputPassword = getUserInputPassword(loginDto);
		// 获取数据库的用户密码
		String blogUserPassword = blogUser.getPassword();
		
		System.out.println(userInputPassword);
		System.out.println(blogUserPassword);
		
		// 密码比较
		if (!blogUserPassword.equals(userInputPassword)) {
			return Result.fail(ResultCodeType.notFound, "密码不正确");
		}
		
		// 组装返回值
		extracted(response, blogUser);
		// 返回用户数据
		return getResult(blogUser);
	}
	
	/**
	 * 登出接口
	 *
	 * @return 返回登出是否成功
	 * @description 首先进行token身份验证
	 * 尝试进行登出账号
	 * 返回成功与否
	 */
	@RequiresAuthentication  // 身份验证
	@GetMapping("/logout")  // 映射路径
	public Result logout() {
		SecurityUtils.getSubject().logout();
		return Result.succ(null);
	}
}
