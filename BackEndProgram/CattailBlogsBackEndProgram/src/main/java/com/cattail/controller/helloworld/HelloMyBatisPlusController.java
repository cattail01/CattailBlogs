package com.cattail.controller.helloworld;

import com.cattail.dao.entity.BlogUser;
import com.cattail.dao.mapper.BlogUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/12 20:56
 * @description
 */

@RestController
@RequestMapping("/TestMyBatisPlus")
public class HelloMyBatisPlusController {
	
	@Autowired
	private BlogUserMapper blogUserMapper;
	
	@RequestMapping("/SelectAll")
	public List<BlogUser> selectAll(){
		return blogUserMapper.selectList(null);
	}
	
}
