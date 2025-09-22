package com.example.debug;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * 最小化测试
 */
@SpringBootTest(classes = DebugApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource(properties = {
    "spring.main.web-application-type=none"
})
class MinimalTest {

    @Test
    void contextLoads() {
        System.out.println("=== 最小化测试成功 ===");
    }

}
