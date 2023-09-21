package com.cattail.controller.text;

import com.cattail.dao.entity.BlogUser;
import com.cattail.dao.mapper.BlogUsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/12 20:56
 * @description 测试my batis plus 是否可用
 */

@RestController
@RequestMapping("/TestMyBatisPlus")
public class HelloMyBatisPlusController {
	
	@Autowired
	private BlogUsersMapper blogUsersMapper;
	
	@GetMapping("/SelectAll")
	public List<BlogUser> selectAll(){
		return blogUsersMapper.selectList(null);
	}
	
}
