package tk.fulsun.demo;

import javax.sql.DataSource;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author fulsun
 * @title: ApplicationTest
 * @projectName springboot-study
 * @description: 测试类
 * @date 5/26/202111:47 AM
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTest {

  @Autowired private DataSource dataSource;
  private MockMvc mvc;

  /** web项目上下文 */
  @Autowired private WebApplicationContext webApplicationContext;

  /** 所有测试方法执行之前执行该方法 */
  @Before
  public void before() {
    // 获取mockmvc对象实例
    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void testDataSource() throws Exception {
    // 启用的数据源：class com.alibaba.druid.pool.DruidDataSource
    System.out.println("启用的数据源：" + dataSource.getClass());
    System.out.println(dataSource.getConnection());
  }


}
