package com.cattail.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/16 12:44
 * @description
 */
@Data  // lombok注解
public class BlogUser {
    private Long id;
    private String username;
    private String avatar;
    private String email;
    private String password;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private String created;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private String lastLogin;
}
