package com.debug;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring Boot 调试模块测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DebugApplication.class)
public class DebugApplicationTests {

    @Test
    public void contextLoads() {
        // 测试 Spring 上下文是否能正常加载
        // 可以在这里设置断点，查看 Spring Boot 的初始化过程
    }
}

