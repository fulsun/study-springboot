package tk.fulsun.demo.service.impl;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.fulsun.demo.entity.User;

/**
 * @author fsun7
 * @description: TODO
 * @date 6/18/2021 4:10 PM
 */
@Service
public class AtomikosTestService {
    private JdbcTemplate primaryJdbcTemplate;
    private JdbcTemplate secondaryJdbcTemplate;

    public AtomikosTestService(JdbcTemplate primaryJdbcTemplate, JdbcTemplate secondaryJdbcTemplate) {
        this.primaryJdbcTemplate = primaryJdbcTemplate;
        this.secondaryJdbcTemplate = secondaryJdbcTemplate;
    }
    @Transactional
    public void tx() {
        // 修改test1库中的数据
        primaryJdbcTemplate.update("update user set age = ? where name = ?", 30, "ccc");
        // 修改test2库中的数据
        secondaryJdbcTemplate.update("update user set age = ? where name = ?", 30, "ccc");
    }

    @Transactional
    public void tx2() {
        // 修改test1库中的数据
        primaryJdbcTemplate.update("update user set age = ? where name = ?", 40, "ccc");
        // 模拟：修改test2库之前抛出异常
        throw new RuntimeException();
    }

    public List<User> getByNameByPrimary(String name) {
        List<User> users =
                primaryJdbcTemplate.query(
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

    public List<User> getByNameBySecondary(String name) {
        List<User> users =
                secondaryJdbcTemplate.query(
                        "select * from user where NAME = ?",
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
}
