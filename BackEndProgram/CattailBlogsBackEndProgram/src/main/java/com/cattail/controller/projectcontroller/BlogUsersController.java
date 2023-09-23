package com.cattail.controller.projectcontroller;

import com.cattail.service.service.BlogUsersService;
import com.cattail.utility.Result;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/19 23:01
 * @description users controller 层
 */

@RestController  // controller层
@RequestMapping("/BlogUsers")  // 映射路径
public class BlogUsersController {
	
	@Autowired
	private BlogUsersService usersService;
	
	//	@RequestMapping("/GetAllUser")
	@GetMapping("/GetAllUser")  // 映射路径
	public Result getAllUser() {
		return Result.succ(usersService.selectAll());
	}
	
	@GetMapping("/GetUserByIndex/{id}")  // 映射路径
	public Object getUserById(@PathVariable("id") Long id) {
		return usersService.getById(id);
	}
	
	@PostMapping("/Save")  // 映射路径
	public Object save(@Validated @RequestBody User user) {
		return Result.succ(user);
	}
}
