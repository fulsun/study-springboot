package tk.fulsun.demo.utils;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import tk.fulsun.demo.config.BeanContext;

/**
 * @author fsun7
 * @description: 多线程无法@Autowired注入，手动获取
 * @date 6/1/2021 3:31 PM
 */
public class ThreadUtils extends Thread {
  private RedissonClient redissonClient = BeanContext.getBean(RedissonClient.class);

  public static int num = 0;

  public ThreadUtils() {}

  @Override
  public void run() {
    for (int i = 0; i < 100; i++) {
      RLock lock = redissonClient.getLock("lock");
      lock.lock();
      try {
        Thread.sleep(100); // 休眠1s
        // synchronized (this) {
        num = num + 1;
        System.out.println(Thread.currentThread().getName() + " nextIntUuid = " + num);
        // }
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
    }
  }
}
