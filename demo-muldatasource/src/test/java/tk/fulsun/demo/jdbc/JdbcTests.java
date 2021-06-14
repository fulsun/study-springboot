package tk.fulsun.demo.jdbc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author fsun7
 * @description: 测试JDBC方式下的多数据源操作
 * @date 6/11/2021 4:46 PM
 */
public class JdbcTests {
  @Autowired protected JdbcTemplate primaryJdbcTemplate;

  @Autowired protected JdbcTemplate secondaryJdbcTemplate;

  @Before
  public void setUp() {
    primaryJdbcTemplate.update("DELETE  FROM  user ");
    secondaryJdbcTemplate.update("DELETE  FROM  user ");
  }

  @Test
  public void test() throws Exception {
    // 往第一个数据源中插入 2 条数据
    primaryJdbcTemplate.update("insert into user(name,age) values(?, ?)", "aaa", 20);
    primaryJdbcTemplate.update("insert into user(name,age) values(?, ?)", "bbb", 30);

    // 往第二个数据源中插入 1 条数据，若插入的是第一个数据源，则会主键冲突报错
    secondaryJdbcTemplate.update("insert into user(name,age) values(?, ?)", "ccc", 20);

    // 查一下第一个数据源中是否有 2 条数据，验证插入是否成功
    Assert.assertEquals(
        "2", primaryJdbcTemplate.queryForObject("select count(1) from user", String.class));

    // 查一下第一个数据源中是否有 1 条数据，验证插入是否成功
    Assert.assertEquals(
        "1", secondaryJdbcTemplate.queryForObject("select count(1) from user", String.class));
  }
}
