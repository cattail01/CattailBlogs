package com.cattail.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cattail.dao.entity.BlogBlogs;
import com.cattail.dao.mapper.BlogBlogsMapper;
import com.cattail.service.service.BlogBlogsService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/19 23:04
 * @description
 */
@Service
public class BlogBlogsServiceImpl extends ServiceImpl<BlogBlogsMapper, BlogBlogs> implements BlogBlogsService {
}
