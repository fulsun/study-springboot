package tk.fulsun.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.fulsun.demo.service.UserService;

/**
 * @author fsun7
 * @description: TODO
 * @date 6/18/2021 1:41 PM
 */
@Service
public class UserService2 {
  private  JdbcTemplate jdbcTemplate;
  @Autowired private UserService userService;


  UserService2( @Qualifier("primaryJdbcTemplate")JdbcTemplate jdbcTemplate){
    this.jdbcTemplate = jdbcTemplate;
  }


  @Transactional
  public void inserBatch() {
    for (int i = 1; i < 10; i++) {
      if (i == 9) {
        throw new RuntimeException();
      }
      userService.create("user" + i, i);
    }
  }

  @Transactional
  public void inserBatch2() {
    jdbcTemplate.update("insert into USER(NAME, AGE) values(?, ?)", "service1", 11);

    for (int i = 1; i < 10; i++) {
      if (i == 9) {
        throw new RuntimeException();
      }
      userService.create2("user" + i, i);
    }
  }
}
