package tk.fulsun.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.fulsun.demo.config.DataSourceNames;
import tk.fulsun.demo.databases.annotation.DataSource;
import tk.fulsun.demo.entity.User;
import tk.fulsun.demo.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description TODO
 * @Date 2021/6/14
 * @Created by 凉月-文
 */
@SpringBootTest
class DynamicDataSourceApplicationTest {
    @Test
    void test1(){
        System.out.println("hello");
    }
}