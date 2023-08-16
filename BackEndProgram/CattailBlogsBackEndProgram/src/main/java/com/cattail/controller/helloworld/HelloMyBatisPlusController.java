package com.cattail.controller.helloworld;

import com.cattail.dao.entity.Countries;
import com.cattail.dao.mapper.CountriesMapper;
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
	private CountriesMapper countriesMapper;
	
	@RequestMapping("/SelectAll")
	public List<Countries> selectAll(){
		return countriesMapper.selectList(null);
	}
	
}
