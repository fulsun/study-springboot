package tk.fulsun.demo.service.impl;

import org.aspectj.lang.annotation.DeclareParents;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.fulsun.demo.config.DataSourceNames;
import tk.fulsun.demo.databases.annotation.DataSource;
import tk.fulsun.demo.entity.User;
import tk.fulsun.demo.service.DataSourceTestService;
import tk.fulsun.demo.service.UserService;

/**
 * @Description TODO
 * @Date 2021/6/14
 * @Created by 凉月-文
 */
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private DataSourceTestService dataSourceTestService;

    @Test
    @DataSource(name = DataSourceNames.MASTER)
    public void queryUser() {
        System.out.println(dataSourceTestService.queryUser(1));
    }

    @Test
    @DataSource(name = DataSourceNames.SLAVE)
    public void queryUser2() {
        System.out.println(dataSourceTestService.queryUser2(1));
    }

    @Test
    public void queryUser3() {
        System.out.println(dataSourceTestService.queryUser3(1));
    }

}