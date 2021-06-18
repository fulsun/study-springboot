package tk.fulsun.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

/**
 * @author fsun7
 * @description: TODO
 * @date 6/18/2021 11:31 AM
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class UserServiceTest {
  @Autowired private UserService userService;

  @Test
  public void test1() {
    userService.create("张三", 18);
    userService.create("李四", 0);
  }
}
