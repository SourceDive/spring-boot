package com.debug;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot 调试入口应用
 * 用于阅读和理解 Spring Boot 源码
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class DebugApplication implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("========================================");
        System.out.println("Spring Boot Debug Module Started!");
        System.out.println("This is your entry point for reading Spring Boot source code.");
        System.out.println("========================================");
        
        // 在这里添加你的调试代码
        // 可以设置断点，逐步跟踪 Spring Boot 的启动流程
    }

    public static void main(String[] args) {
        // Spring Boot 应用启动入口
        // 可以在这里设置断点，跟踪 SpringApplication.run() 的执行流程
        SpringApplication.run(DebugApplication.class, args);
    }
}

