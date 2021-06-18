package tk.fulsun.demo.service.impl;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import tk.fulsun.demo.entity.User;
import tk.fulsun.demo.service.UserService;

/**
 * @author fsun7
 * @description: 用户操作接口实现类
 * @date 6/18/2021 10:22 AM
 */
@Service
public class UserServiceImpl implements UserService {

  private JdbcTemplate jdbcTemplate;

  UserServiceImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public int create(String name, Integer age) {
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
