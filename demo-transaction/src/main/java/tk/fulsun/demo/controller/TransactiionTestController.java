package tk.fulsun.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.fulsun.demo.service.UserService;

/**
 * @author fulsun
 * @description: 测试事务控制层
 * @date 6/18/2021 11:47 AM
 */
@RestController
@RequestMapping("/test")
public class TransactiionTestController {
  @Autowired private UserService userService;
  @Autowired
  @Qualifier("primaryJdbcTemplate")
  private JdbcTemplate jdbcTemplate;

  @GetMapping("/create")
  @Transactional
  public String test1() {
    userService.create("张三", 18);
    userService.create("李四", 0);
    return "OK";
  }
}
