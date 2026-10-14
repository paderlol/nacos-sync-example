# Nacos Sync 示例

用真实的 Spring Cloud 和 Dubbo 应用演示 [nacos-sync](https://github.com/nacos-group/nacos-sync) 在不同注册中心之间同步服务。

## 分支与版本

| 示例仓库分支 | 对应 nacos-sync | JDK | 示例使用的框架 |
|---|---|---|---|
| `2.0`（本分支） | 2.x | 17 | Spring Boot 3.4.0、Spring Cloud 2024.0.0、Spring Cloud Alibaba 2023.0.3.2、Dubbo 3.3.2、nacos-client 2.4.3 |
| `master` | 0.x | 11 | Spring Boot 2.7 |

本分支的示例已在 Nacos 2.5.4 和 3.2.4（均开启鉴权）、Consul 1.20、ZooKeeper 3.7.2、MySQL 8.4 上验证过。Eureka 示例还可以用
Spring Cloud 2021.0 到 2025.0 的任一版本系列构建，见 [cloud-eureka](cloud-eureka/README.MD)。

## 示例目录

| 示例 | 同步方向 | 应用 |
|---|---|---|
| [Eureka ↔ Nacos](cloud-eureka/README.MD) | 双向 | eureka-server、eureka-provider、nacos-cloud-consumer |
| [Consul ↔ Nacos](cloud-consul/README.MD) | 双向 | consul-provider-cloud、nacos-provider-cloud |
| [ZooKeeper ↔ Nacos（Dubbo）](dubbo-zookeeper/README.MD) | 双向 | zookeeper-provider、zookeeper-consumer、nacos-provider、nacos-consumer |

## 公共环境准备

下面的命令都在本机运行，端口和示例里的默认配置一致。尖括号里的值请换成你自己的。

### 1. MySQL（nacos-sync 的数据库）

```shell
docker run -d --name nacos-sync-db -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=<root 密码> -e MYSQL_DATABASE=nacos_sync mysql:8.4
```

### 2. Nacos

```shell
docker run -d --name nacos -p 8848:8848 -p 9848:9848 -p 8080:8080 \
  -e MODE=standalone \
  -e NACOS_AUTH_ENABLE=true \
  -e NACOS_AUTH_TOKEN=<长度 32 以上的字符串的 Base64> \
  -e NACOS_AUTH_IDENTITY_KEY=<自定义 key> -e NACOS_AUTH_IDENTITY_VALUE=<自定义 value> \
  nacos/nacos-server:v3.2.4
```

第一次启动后设置 `nacos` 用户的密码：

```shell
curl -X POST 'http://127.0.0.1:8848/nacos/v3/auth/user/admin' --data-urlencode 'password=<nacos 密码>'
```

用 Nacos 2.x 时把镜像换成 `nacos/nacos-server:v2.5.4`，设置密码的接口是 `/nacos/v1/auth/users/admin`。Nacos 3.x 的控制台在
http://127.0.0.1:8080，2.x 的在 http://127.0.0.1:8848/nacos。

### 3. nacos-sync 2.x

下载 [nacos-sync](https://github.com/nacos-group/nacos-sync) 的发布包，或者在 nacos-sync 仓库里执行 `mvn clean package -U`，得到
`nacossync-distribution/target/nacos-sync-2.0.0.tar.gz`。解压后修改 `conf/application.properties` 里的数据库配置：

```properties
server.port=8083
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/nacos_sync?characterEncoding=utf8
spring.datasource.username=root
spring.datasource.password=<root 密码>
```

nacos-sync 2.x 使用 Hibernate 6，方言必须是 `MySQLDialect`，旧文档里的 `MySQL5Dialect` 会导致启动失败。然后启动：

```shell
sh bin/startup.sh start
```

控制台地址是 http://127.0.0.1:8083 。在「集群配置」里添加 Nacos 集群时，集群 IP 填 `127.0.0.1:8848`（是服务端口，不是控制台端口
8080），开启鉴权时同时填写用户名和密码。

## 构建和运行示例

```shell
mvn clean package
```

每个应用都是一个可执行 jar，用 `java -jar <目录>/<模块>/target/<模块>-*.jar` 启动。

示例应用的 main 方法没有把命令行参数传给 Spring，所以 `--key=value` 不会生效，要用环境变量覆盖配置：

- Spring Cloud 应用连接开启鉴权的 Nacos：
  ```shell
  SPRING_CLOUD_NACOS_DISCOVERY_USERNAME=nacos SPRING_CLOUD_NACOS_DISCOVERY_PASSWORD=<nacos 密码> java -jar ...
  ```
- Dubbo 应用要覆盖 `dubbo.*` 配置，请用 `SPRING_APPLICATION_JSON`，例如：
  ```shell
  SPRING_APPLICATION_JSON='{"dubbo":{"registry":{"address":"nacos://127.0.0.1:8848?username=nacos&password=<nacos 密码>"}}}' java -jar ...
  ```

## 自动化测试

nacos-sync 仓库 sync-2.x 分支的 `e2e/` 目录会自动启动上面所有组件，用本仓库固定的
commit 构建这些示例，并检查同步、下线、批量、中断恢复和升级等场景。
