package tk.fulsun.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.fulsun.demo.entity.User;
import tk.fulsun.demo.entity.UserRowMapper;

/**
 * @author fulsun
 * @title: TestController
 * @projectName springboot-study
 * @description: 控制层代码，操作数据库
 * @date 5/26/2021 1:57 PM
 */
@RestController
public class TestController {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @GetMapping("/users")
  public List<User> getAllUser() {
    return jdbcTemplate.query("select id, name ,age from user", new UserRowMapper());
  }
}
