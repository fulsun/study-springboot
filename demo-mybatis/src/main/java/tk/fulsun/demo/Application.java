package tk.fulsun.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 无数据库时可以添加exclude属性 表示不加载对应的自动配置类 * @SpringBootApplication(exclude =
 * {DataSourceAutoConfiguration.class})
 *
 * @author fulsun
 * @description: 主启动类
 * @date 5/26/202111:45 AM
 */
@SpringBootApplication
@MapperScan({"tk.fulsun.demo.mapper", "tk.fulsun.demo.*.mapper"})
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
