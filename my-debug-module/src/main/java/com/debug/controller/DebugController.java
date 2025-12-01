package com.debug.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单的调试 Controller
 * 用于测试和调试 Spring Boot Web 功能
 */
@RestController
@RequestMapping("/api/debug")
public class DebugController {

    /**
     * 健康检查接口
     * GET /api/debug/health
     */
    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public Map<String, Object> health() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", "UP");
        result.put("message", "Spring Boot Debug Module is running!");
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }

    /**
     * 简单的 Hello World 接口
     * GET /api/debug/hello
     */
    @RequestMapping(value = "/hello", method =  RequestMethod.GET)
    public Map<String, String> hello() {
        Map<String, String> result = new HashMap<String, String>();
        result.put("message", "Hello from Spring Boot Debug Module!");
        return result;
    }

    /**
     * 获取应用信息
     * GET /api/debug/info
     */
    @RequestMapping(value = "/info", method =   RequestMethod.GET)
    public Map<String, Object> info() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("application", "my-debug-module");
        result.put("version", "1.0.0.RELEASE");
        result.put("description", "Spring Boot Debug Module for reading source code");
        return result;
    }
}

