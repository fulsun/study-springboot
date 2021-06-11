package tk.fulsun.demo.jpa;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author fsun7
 * @description: 测试JPA方式下的多数据源操作
 * @date 6/11/2021 4:23 PM
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class JPATests {
  // @Autowired private UserRepository userRepository;
  // @Autowired private MessageRepository messageRepository;
  //
  // @Test
  // public void test() throws Exception {
  //   userRepository.save(new User("aaa", 10));
  //   userRepository.save(new User("bbb", 20));
  //   userRepository.save(new User("ccc", 30));
  //   userRepository.save(new User("ddd", 40));
  //   userRepository.save(new User("eee", 50));
  //
  //   Assert.assertEquals(5, userRepository.findAll().size());
  //
  //   messageRepository.save(new Message("o1", "aaaaaaaaaa"));
  //   messageRepository.save(new Message("o2", "bbbbbbbbbb"));
  //   messageRepository.save(new Message("o3", "cccccccccc"));
  //
  //   Assert.assertEquals(3, messageRepository.findAll().size());
  // }
}
