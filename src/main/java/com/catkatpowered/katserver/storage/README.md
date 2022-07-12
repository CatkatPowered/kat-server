# KatStorage

KatServer 的储存模块，负责储存各种资源文件，例如从 IM 服务器缓存下来的语音、文件、图片等

## 替换能力

**注意：储存模块不可以混合使用。若使用本地储存模块应当一直使用本地储存模块。**

本模块允许被替换，可以通过实现 `KatStorageProvider` 接口最终通过 `KatServer.KatStorageAPI.registerStorageProvider()` 方法完成注册

本模块通过配置配置文件中 `resource.storage_provider` 配置节点写入 `KatStorage` 实现者名称切换储存提供者

## 内置与默认

目前内置以下储存提供者，带`*`则为默认

- \*local

## 实现细节

### `StorageProvider` 管理与切换

使用 Map 数据结构管理 `StorageProvider` ，Key 为实现的 `StorageProvider` 的名称，`KatStorage` 会根据在配置文件中填写的储存提供者名称在 Map 数据结构中查找对应实现 `KatStorageProvider` 的储存提供者

### 默认约定

- 模块对文件名不敏感，不会用到任何文件名相关的内容
- 模块使用 Hash 实现对文件的储存等，例如文件 Hash 为 `6f8483c9942d8368a301879b49935111a9b7278f79890d49f91391ec765cb129` 则其储存路径为`{DATA_DIRECTORY}/6f/84/6f8483c9942d8368a301879b49935111a9b7278f79890d49f91391ec765cb129`
- `fetch()` 方法返回值若 `Optional` 为空则无法获取文件
