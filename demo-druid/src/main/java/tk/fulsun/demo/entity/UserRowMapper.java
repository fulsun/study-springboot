package tk.fulsun.demo.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author fulsun
 * @title: UserRowMapper
 * @projectName springboot-study
 * @description: UserRowMapper映射数据库字段
 * @date 5/26/2021 1:56 PM
 */
public class UserRowMapper implements RowMapper<User> {

  @Override
  public User mapRow(ResultSet resultSet, int i) throws SQLException {
    User user = new User();
    user.setId(resultSet.getInt("id"));
    user.setName(resultSet.getString("name"));
    user.setAge(resultSet.getInt("age"));
    return user;
  }
}
