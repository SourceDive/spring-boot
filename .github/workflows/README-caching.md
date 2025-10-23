# GitHub Actions 缓存优化说明

## 当前缓存配置

### 1. 基础 Gradle 缓存
通过 `actions/setup-java@v3` 的 `cache: 'gradle'` 参数自动缓存：
- Gradle Wrapper（`~/.gradle/wrapper`）
- Gradle 依赖缓存（`~/.gradle/caches`）

### 2. 新增缓存优化

#### Gradle Build Cache
```yaml
- name: Cache Gradle Build Cache
  uses: actions/cache@v3
  with:
    path: |
      ~/.gradle/caches/build-cache-*
    key: ${{ runner.os }}-gradle-build-cache-${{ hashFiles('**/*.gradle*', '**/gradle-wrapper.properties') }}
```
- 缓存构建过程中的中间产物
- 可以显著加速增量构建

#### 编译输出缓存
```yaml
- name: Cache Build Outputs
  uses: actions/cache@v3
  with:
    path: |
      **/build/classes
      **/build/generated
      **/build/resources
      **/build/tmp
      buildSrc/build
```
- 缓存已编译的类文件
- 缓存生成的资源文件
- 对于无代码变更的构建特别有效

## 优化建议

### 1. 启用 Gradle 构建缓存
在构建命令中添加 `--build-cache` 参数：
```bash
./gradlew --build-cache classes testClasses
```

### 2. 配置并发策略
避免多个工作流同时运行造成缓存冲突：
```yaml
concurrency:
  group: ${{ github.workflow }}-${{ github.ref }}
  cancel-in-progress: true
```

### 3. 使用配置缓存
Gradle 6.6+ 支持配置缓存，可以进一步加速构建：
```bash
./gradlew --configuration-cache classes
```

### 4. 并行构建
利用多核处理器加速构建：
```bash
./gradlew --parallel --max-workers=4 classes
```

## 缓存键策略

### 主键组成
- 操作系统：`${{ runner.os }}`
- JDK 版本：`${{ matrix.java }}`（如果使用矩阵构建）
- 文件哈希：`${{ hashFiles('**/*.gradle*', '**/gradle-wrapper.properties', 'buildSrc/**') }}`

### 恢复键（restore-keys）
按优先级排序，从最具体到最通用：
1. 完全匹配的缓存键
2. 相同 JDK 版本的缓存
3. 任何可用的构建缓存

## 监控和调试

### 查看缓存统计
```yaml
- name: Gradle Build Cache Stats
  if: always()
  run: |
    echo "### Gradle Build Cache Statistics ###"
    find ~/.gradle/caches/build-cache-* -type f | wc -l | xargs echo "Build cache entries:"
    du -sh ~/.gradle/caches/build-cache-* 2>/dev/null || echo "Build cache not found"
```

### 清理缓存
如果缓存出现问题，可以通过以下方式清理：
1. 在 GitHub UI 中删除缓存
2. 更改缓存键（例如添加版本后缀）
3. 使用 GitHub API 删除缓存

## 注意事项

1. **缓存大小限制**：GitHub Actions 每个仓库最多 10GB 缓存
2. **缓存过期**：7 天未使用的缓存会被自动删除
3. **缓存作用域**：缓存在同一仓库的工作流之间共享，但分支之间有限制
4. **安全性**：不要缓存敏感信息（如密钥、令牌等）

## 性能提升预期

通过实施这些缓存优化，预计可以：
- 减少 30-50% 的构建时间（对于无代码变更的构建）
- 减少 20-30% 的网络流量（避免重复下载依赖）
- 提高 CI/CD 流水线的整体效率