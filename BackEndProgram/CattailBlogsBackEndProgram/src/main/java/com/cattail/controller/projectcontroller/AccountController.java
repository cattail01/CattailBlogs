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
 * @description 登录接口
 */
@RestController()
public class AccountController {
	
	@Autowired
	BlogUsersService usersService;
	
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
	
	private String getUserInputPassword(LoginDto loginDto) {
		String userInputPassword = loginDto.getPassword();
		userInputPassword = SecureUtil.md5(userInputPassword);
		return userInputPassword;
	}
	
	private void extracted(HttpServletResponse response, BlogUser blogUser) {
		String jwt = jwtUtilities.generateToken(blogUser.getId());
		response.setHeader("Authorization", jwt);
		response.setHeader("Access-control-Expose-Headers", "Authorization");
	}
	
	private static Result getResult(BlogUser blogUser) {
		return Result.succ(MapUtil.builder()
		                          .put("id", blogUser.getId())
		                          .put("username", blogUser.getUsername())
		                          .put("avater", blogUser.getAvatar())
		                          .put("email", blogUser.getEmail())
		                  );
	}
	
	@PostMapping("/login")
	public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
		
		BlogUser blogUser = getBlogUser(loginDto);
		Assert.notNull(blogUser, "用户不存在");
		
		String userInputPassword = getUserInputPassword(loginDto);
		String blogUserPassword = blogUser.getPassword();
		if (!blogUserPassword.equals(userInputPassword)) {
			return Result.fail(ResultCodeType.notFound, "密码不正确");
		}
		
		extracted(response, blogUser);
		return getResult(blogUser);
	}
	
	@RequiresAuthentication  // 身份验证
	@GetMapping("/logout")  // 映射路径
	public Result logout() {
		SecurityUtils.getSubject().logout();
		return Result.succ(null);
	}
}
