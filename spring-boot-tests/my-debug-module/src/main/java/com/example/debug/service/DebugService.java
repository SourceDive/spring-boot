package com.example.debug.service;

import org.springframework.stereotype.Service;

/**
 * 调试服务类 用于测试 Spring Boot 的各种功能
 */
@Service
public class DebugService {

	public String getDebugInfo() {
		return "Debug Service is working!";
	}

	public String getSpringBootVersion() {
		return "Spring Boot Version: " + org.springframework.boot.SpringBootVersion.getVersion();
	}

}
