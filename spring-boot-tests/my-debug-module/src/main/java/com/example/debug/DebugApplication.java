package com.example.debug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot 调试模块主应用类 用于学习和调试 Spring Boot 源码
 */
@SpringBootApplication
@RestController
public class DebugApplication {

	public static void main(String[] args) {
		System.out.println("=== 开始启动 Spring Boot Debug Module ===");
		try {
			SpringApplication app = new SpringApplication(DebugApplication.class);
			app.setLogStartupInfo(true);
			app.run(args);
		} catch (Exception e) {
			System.err.println("启动失败: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {
		System.out.println("=== Spring Boot Debug Module 启动完成 ===");
		System.out.println("访问地址: http://localhost:8080");
		System.out.println("调试地址: http://localhost:8080/debug");
	}

	@GetMapping("/")
	public String home() {
		return "Hello, Spring Boot Debug Module!";
	}

	@GetMapping("/debug")
	public String debug() {
		return "Debug endpoint for Spring Boot source code learning";
	}

}
