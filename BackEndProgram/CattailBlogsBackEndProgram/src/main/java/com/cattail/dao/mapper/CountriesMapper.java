package com.cattail.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cattail.dao.entity.Countries;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/16 12:52
 * @description
 */
@Mapper
@Repository
public interface CountriesMapper extends BaseMapper<Countries> {
}
