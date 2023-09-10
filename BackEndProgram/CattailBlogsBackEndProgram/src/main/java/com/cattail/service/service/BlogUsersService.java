package com.cattail.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cattail.dao.entity.BlogUser;

import java.util.List;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/19 23:02
 * @description users 业务层设计
 */
public interface BlogUsersService extends IService<BlogUser> {
	
	/**
	 * 查询所有的user
	 * @return user list
	 */
	List<BlogUser> selectAll();
}
