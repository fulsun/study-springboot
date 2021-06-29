package tk.fulsun.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.fulsun.demo.entity.User;
import tk.fulsun.demo.service.UserService;

/**
 * @author fulsun
 * @description: 用户操作接口实现类
 * @date 6/18/2021 10:22 AM
 */
@Service
public class UserServiceImpl implements UserService {

  private JdbcTemplate jdbcTemplate;

  UserServiceImpl(@Qualifier("primaryJdbcTemplate") JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public int create(String name, Integer age) {
    if (age <= 0) {
      throw new RuntimeException("年龄不能小于0");
    }
    return jdbcTemplate.update("insert into USER(NAME, AGE) values(?, ?)", name, age);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public int create2(String name, Integer age) {
    if (age <= 0) {
      throw new RuntimeException("年龄不能小于0");
    }
    return jdbcTemplate.update("insert into USER(NAME, AGE) values(?, ?)", name, age);
  }

  @Override
  public List<User> getByName(String name) {
    List<User> users =
        jdbcTemplate.query(
            "select * from USER where NAME = ?",
            (resultSet, i) -> {
              User user = new User();
              user.setId(resultSet.getLong("ID"));
              user.setName(resultSet.getString("NAME"));
              user.setAge(resultSet.getInt("AGE"));
              return user;
            },
            name);
    return users;
  }

  @Override
  public int deleteByName(String name) {
    return jdbcTemplate.update("delete from USER where NAME = ?", name);
  }

  @Override
  public int getAllUsers() {
    return jdbcTemplate.queryForObject("select count(1) from USER", Integer.class);
  }

  @Override
  public int deleteAllUsers() {
    return jdbcTemplate.update("delete from USER");
  }
}
