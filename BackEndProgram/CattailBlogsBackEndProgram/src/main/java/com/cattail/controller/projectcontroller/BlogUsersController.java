package com.cattail.controller.projectcontroller;

import com.cattail.service.service.BlogUsersService;
import com.cattail.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/GetAllUser")  // 映射路径
	public Result index(){
		return Result.succ(usersService.selectAll());
	}
	
	@GetMapping("/GetUserByIndex/{id}")  // 映射路径
	public Object getUserById(@PathVariable("id") Long id){
		return usersService.getById(id);
	}
}
