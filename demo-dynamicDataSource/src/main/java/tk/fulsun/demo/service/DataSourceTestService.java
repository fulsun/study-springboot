package tk.fulsun.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.fulsun.demo.config.DataSourceNames;
import tk.fulsun.demo.databases.annotation.DataSource;
import tk.fulsun.demo.entity.User;

/**
 * 测试多数据源
 */
@Service
public class DataSourceTestService {

    @Autowired
    private UserService userService;

    @DataSource(name = DataSourceNames.MASTER)
    public User queryUser(Integer userId) {
        return userService.selectByPrimaryKey(userId);
    }

    @DataSource(name = DataSourceNames.SLAVE)
    public User queryUser2(Integer userId) {
        return userService.selectByPrimaryKey(userId);
    }

    public User queryUser3(Integer userId) {
        return userService.selectByPrimaryKey(userId);
    }

}
