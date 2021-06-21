package tk.fulsun.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import tk.fulsun.demo.model.User;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description TODO
 * @Date 2021/6/20
 * @Created by 凉月-文
 */
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    CacheManager cacheManager;

    @Test
    public void test1() {
        System.out.println("CacheManager type : " + cacheManager.getClass());
        User user1 = userService.getUserByName("张三");
        System.out.println("-----------------");
        User user2 = userService.getUserByName("张三");

    }

}