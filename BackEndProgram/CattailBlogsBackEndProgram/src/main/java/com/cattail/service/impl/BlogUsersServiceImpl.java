package com.cattail.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cattail.dao.entity.BlogUsers;
import com.cattail.dao.mapper.BlogUsersMapper;
import com.cattail.service.service.BlogUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/19 23:05
 * @description blog user service 层实现
 */
@Service
@Repository
public class BlogUsersServiceImpl extends ServiceImpl<BlogUsersMapper, BlogUsers> implements BlogUsersService {
	
	@Autowired
	private BlogUsersMapper usersMapper;
	
	public List<BlogUsers> selectAll(){
		return usersMapper.selectList(null);
	}
}
