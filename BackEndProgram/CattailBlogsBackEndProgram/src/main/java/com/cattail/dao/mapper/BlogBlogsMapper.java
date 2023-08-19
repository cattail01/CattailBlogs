package com.cattail.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cattail.dao.entity.BlogBlogs;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/19 22:27
 * @description blogs 映射接口
 */
@Mapper
@Repository
public interface BlogBlogsMapper extends BaseMapper<BlogBlogs> {
}
