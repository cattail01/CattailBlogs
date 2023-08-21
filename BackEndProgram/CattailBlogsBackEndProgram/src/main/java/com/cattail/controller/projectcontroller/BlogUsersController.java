package com.cattail.controller.projectcontroller;

import com.cattail.service.impl.BlogUsersServiceImpl;
import com.cattail.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/19 23:01
 * @description users controller 层
 */

@RestController
@RequestMapping("/BlogUsers")
public class BlogUsersController {
	
	@Autowired
	private BlogUsersServiceImpl usersService;
	
	@RequestMapping("GetIndex1User")
	public Result index(){
		return Result.succ(usersService.selectAll());
	}
}
