package com.cattail.controller.helloworld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/15 22:37
 * @description 测试 spring boot 是否可用
 */
@RestController
@RequestMapping("/hello")
public class HelloSpringBootController {
    
    @RequestMapping("/say")
    public String say(){
        return "hello world";
    }
    
    
}
