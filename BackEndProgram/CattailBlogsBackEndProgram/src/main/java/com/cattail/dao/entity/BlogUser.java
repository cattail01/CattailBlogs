package com.cattail.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/16 12:44
 * @description 用户实体类
 */
@Data  // lombok注解
@TableName("blog_users")
public class BlogUser {
    private Long id;
    private String username;
    private String avatar;
    private String email;
    private String password;
    private Integer status;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
//    private String created;
    private Timestamp created;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
//    private String lastLogin;
    private Timestamp lastLogin;
}
