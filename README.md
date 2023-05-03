# 远程工地扬尘检测系统

#### Description
本项目是一个基于 Spring Boot 的空气质量监控系统，可以实时监测空气中的 CO2、CH2O、TVOC、PM2.5、PM10、温度和湿度等指标，并在网页上展示实时数据。

#### Software Architecture
本项目使用 Java 编程语言，基于 Spring Boot 框架。项目中使用了 MyBatis 和 MySQL 数据库来存储数据。前端采用了 ECharts 库来展示折线图。

#### Installation

1. 确保您已经安装了 Java 8 或更高版本。
2. 确保您已经安装了 MySQL 数据库，我使用的版本为 5.7.33。

#### Configuration

##### 数据库

1. 创建一个名为 `air_quality_monitor` 的数据库，字符集选择 `utf8mb4`，排序规则选择 `utf8mb4_general_ci`。
2. 在 `src/main/resources/application.properties` 文件中，配置数据库连接信息，例如：
   spring.datasource.url=jdbc:mysql://localhost:3306/air_quality_monitor?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8
   spring.datasource.username=root
   spring.datasource.password=123456

   根据实际情况修改数据库名、用户名和密码。

#### Instructions

1. 克隆或下载本项目到本地。
2. 使用 IntelliJ IDEA 或 Eclipse 等 IDE 打开项目。
3. 运行项目。
4. 在浏览器中访问 `http://127.0.0.1:8080/index` ，查看实时数据。