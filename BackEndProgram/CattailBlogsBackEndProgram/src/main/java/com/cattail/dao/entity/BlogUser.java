package com.cattail.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/16 12:44
 * @description 用户实体类
 */
@Data  // lombok注解
@TableName("blog_users")  // 表格名称
public class BlogUser {
    /**
     * user id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * user name
     */
    @NotBlank(message = "昵称不能为空")
    private String username;
    
    /**
     * 头像
     */
    private String avatar;
    
    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    private String email;
    
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
    
    /**
     * 账号状态
     */
    private Integer status;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
//    private String created;
    
    /**
     * 创建时间
     */
    private Timestamp created;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
//    private String lastLogin;
    
    /**
     * 最后登录时间
     */
    private Timestamp lastLogin;
}
