package tk.fulsun.demo;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fsun7
 * @title: Application
 * @projectName springboot-study
 * @description: 主启动类 ,无数据库时可以添加exclude属性 表示不加载对应的自动配置类
 * @date 5/26/202111:45 AM
 */

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class Application {

  @Autowired
  private DataSource dataSource;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
