package tk.fulsun.demo.controller;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.fulsun.demo.utils.ThreadUtils;

/**
 * @author fulsun
 * @description: RedissonController
 * @date 6/1/2021 10:28 AM
 */
@RestController
@RequestMapping("/redisson")
public class RedissonController {

  @Autowired private RedissonClient redisson;
  @Autowired private RedisTemplate redisTemplate;

  @GetMapping("/save")
  public String save() {
    redisTemplate.opsForValue().set("key", "redisson");
    return "save ok";
  }

  @GetMapping("/get")
  public String get() {
    return redisTemplate.opsForValue().get("key").toString();
  }

  @GetMapping("/getlock")
  public int getlock() {
    int num = 100;
    for (int i = 1; i <= 10; i++) {
      ThreadUtils tu = new ThreadUtils();
      // Thread thread = new Thread(tu);
      tu.start();
    }
    return ThreadUtils.num;
  }

  public static void main(String[] args) {}
}
