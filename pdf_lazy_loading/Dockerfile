# 使用官方的 OpenJDK 基础镜像
FROM openjdk:17-jdk-slim

# 设置工作目录
WORKDIR /app

COPY target/classes/application.yml prod.yml
# 复制项目的 JAR 文件到容器中
COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar","--spring.config.location=/data/prod.yml"]
