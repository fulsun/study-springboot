package tk.fulsun.demo.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tk.fulsun.demo.mapper.UserMapper;
import tk.fulsun.demo.model.User;
import tk.fulsun.demo.service.ServiceA;

/**
 * @Description Mock测试
 * @Date 2021/6/13
 * @Created by 凉月-文
 */
@SpringBootTest
class ControllerAMockTest {
    @Autowired
    private ServiceA serviceA;

    @MockBean
    private UserMapper userDao;

    @Test
    public void getUserById() throws Exception {
        // 定义当调用mock userDao的getUserById()方法，并且参数为3时，就返回id为200、name为I'm mock3的user对象
        Mockito.when(userDao.selectByPrimaryKey(1)).thenReturn(new User("凉月文", 18));

        //普通的使用userService，他里面会再去调用userDao取得数据库的数据
        //返回的会是定义的 user("凉月文", 18)对象
        User user = serviceA.getUserById(1);
        System.out.println(user);
        //检查结果
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getId(), new Integer(1));
        Assertions.assertEquals(user.getName(), "凉月文");
    }

}
