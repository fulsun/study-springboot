package tk.fulsun.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author fsun7
 * @description: 主启动类
 * @date 5/27/2021 4:29 PM
 */
// 开启Spring Boot基于注解的缓存管理支持
@EnableCaching
@MapperScan({"tk.fulsun.demo.mapper", "tk.fulsun.demo.*.mapper"})
@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
