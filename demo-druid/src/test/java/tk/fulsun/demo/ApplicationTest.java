package tk.fulsun.demo;


import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author fsun7
 * @title: ApplicationTest
 * @projectName springboot-study
 * @description: 测试类
 * @date 5/26/202111:47 AM
 */
@SpringBootTest
public class ApplicationTest {

  @Autowired
  private DataSource dataSource;

  @Test
  public void testDataSource() throws SQLException {
    // 启用的数据源：class com.alibaba.druid.pool.DruidDataSource
    System.out.println("启用的数据源：" + dataSource.getClass());
    System.out.println(dataSource.getConnection());
  }
}