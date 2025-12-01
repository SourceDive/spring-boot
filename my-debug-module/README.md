# My Debug Module

这是一个用于阅读和理解 Spring Boot 源码的调试模块。

## 模块结构

```
my-debug-module/
├── pom.xml                          # Maven 配置文件
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/debug/
│   │   │       ├── DebugApplication.java    # Spring Boot 主类
│   │   │       └── controller/
│   │   │           └── DebugController.java  # REST Controller
│   │   └── resources/
│   │       └── application.yml              # 应用配置文件（YAML格式）
│   └── test/
│       └── java/
│           └── com/debug/
│               └── DebugApplicationTests.java  # 测试类
└── README.md
```

## 使用方法

### 1. 编译模块

```bash
cd my-debug-module
mvn clean compile
```

### 2. 运行应用

```bash
mvn spring-boot:run
```

或者直接运行主类：

```bash
mvn exec:java -Dexec.mainClass="com.debug.DebugApplication"
```

### 3. 调试源码

在 IDE（如 IntelliJ IDEA 或 Eclipse）中：

1. 导入项目
2. 在 `DebugApplication.main()` 方法中设置断点
3. 在 `DebugApplication.run()` 方法中设置断点
4. 以 Debug 模式运行应用
5. 逐步跟踪 Spring Boot 的启动流程

### 4. 关键断点位置

- **`SpringApplication.run()`** - Spring Boot 应用启动入口
- **`DebugApplication.run()`** - CommandLineRunner 执行点
- **`@EnableAutoConfiguration`** - 自动配置入口
- **`@ComponentScan`** - 组件扫描入口

## 配置文件

配置文件使用 YAML 格式（`application.yml`），包含：

- 应用名称配置
- 日志级别配置（支持 DEBUG 模式查看详细日志）

## API 接口

启动应用后，可以通过以下接口进行测试：

- **健康检查**: `GET http://localhost:8080/api/debug/health`
- **Hello World**: `GET http://localhost:8080/api/debug/hello`
- **应用信息**: `GET http://localhost:8080/api/debug/info`

### 测试示例

```bash
# 启动应用
mvn spring-boot:run

# 在另一个终端测试接口
curl http://localhost:8080/api/debug/health
curl http://localhost:8080/api/debug/hello
curl http://localhost:8080/api/debug/info
```

## 依赖

- `spring-boot-starter-web` - Spring Boot Web 启动器（包含 Spring MVC）
- `spring-boot-starter-test` - Spring Boot 测试支持

## 阅读源码建议

1. **启动流程**：从 `SpringApplication.run()` 开始跟踪
2. **自动配置**：关注 `@EnableAutoConfiguration` 的实现
3. **上下文初始化**：查看 `ApplicationContext` 的创建过程
4. **Bean 注册**：了解 Spring Bean 的注册机制

## 注意事项

- 此模块继承自 `spring-boot-starter-parent`，依赖版本由 Spring Boot 管理
- 配置文件使用 YAML 格式，需要 Spring Boot 支持 YAML 解析
- 建议在 IDE 中打开源码调试功能，方便跟踪代码执行流程

