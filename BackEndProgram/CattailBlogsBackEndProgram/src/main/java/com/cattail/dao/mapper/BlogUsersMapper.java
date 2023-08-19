package com.cattail.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cattail.dao.entity.BlogUsers;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/16 12:52
 * @description users 映射接口
 */
@Mapper
@Repository
public interface BlogUsersMapper extends BaseMapper<BlogUsers> {
}
