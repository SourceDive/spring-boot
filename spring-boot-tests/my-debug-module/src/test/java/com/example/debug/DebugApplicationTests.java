package com.example.debug;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.example.debug.service.DebugService;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 调试应用测试类
 */
@SpringBootTest
@TestPropertySource(
		properties = { "logging.level.org.springframework.boot=DEBUG", "logging.level.com.example.debug=DEBUG" })
class DebugApplicationTests {

	@Autowired
	private DebugService debugService;

	@Test
	void contextLoads() {
		// 测试 Spring 上下文是否正常加载
	}

	@Test
	void debugServiceShouldBeInjected() {
		assertThat(debugService).isNotNull();
		assertThat(debugService.getDebugInfo()).isEqualTo("Debug Service is working!");
	}

	@Test
	void shouldGetSpringBootVersion() {
		String version = debugService.getSpringBootVersion();
		assertThat(version).isNotNull();
		assertThat(version).startsWith("Spring Boot Version:");
	}

}
