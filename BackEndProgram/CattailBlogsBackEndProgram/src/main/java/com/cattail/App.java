package com.cattail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/12 19:03
 * @description
 */

@SpringBootApplication
public class App {
    
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("Spring boot jar empty project");
    }
}
