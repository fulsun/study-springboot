package tk.fulsun.demo;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @author fulsun
 * @description: 主测试类
 * @date 5/27/2021 4:27 PM
 */
@SpringBootTest
class ApplicationTest {
  @Autowired private RedisTemplate redisTemplate;

  @Test
  public void testStringRedisTemplate() {
    ValueOperations<String, String> vo = redisTemplate.opsForValue();
    // 使用redis-cli查看key
    // 发现hello的key变成了 \xac\xed\x00\x05t\x00\x05hello
    vo.set("hello", "世界world_" + UUID.randomUUID().toString());
    String hello = vo.get("hello");
    System.out.println("之前保存的数据:" + hello);
  }
}
