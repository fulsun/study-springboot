package tk.fulsun.demo.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.fulsun.demo.mapper.UserMapper;
import tk.fulsun.demo.model.User;

import java.util.List;

/**
 * @Description Service层单元测试
 * @Date 2021/6/13
 * @Created by 凉月-文
 */
//@RunWith(SpringRunner.class)
@SpringBootTest
class ServiceATest {
    @Autowired
    private ServiceA serviceA;

    @Autowired
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        System.out.println("start=============");
    }

    @AfterEach
    void tearDown() {
        System.out.println("end=============");

    }

    @Test
    public void test1() {
        serviceA.doSomething();
        List<User> users = userMapper.selectByExample(null);
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("--------------测试----------");
    }
}