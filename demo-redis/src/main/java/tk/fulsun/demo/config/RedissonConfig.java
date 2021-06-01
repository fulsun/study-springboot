package tk.fulsun.demo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fsun7
 * @description: RedissonConfig
 * @date 6/1/2021 10:25 AM
 */
@Configuration
public class RedissonConfig {

  @Autowired private RedisProperties redisProperties;
  /** redisson 连接地址格式 */
  private static final String REDISSON_FORMAT = "redis://%s:%s";

  private String format(String hostname, int port) {
    return String.format(REDISSON_FORMAT, hostname, port);
  }

  private String format(String url) {
    return String.format("redis://%s", url);
  }

  @Bean
  public RedissonClient redissonClient() {
    Config config = new Config();
    String[] addSentinelAddress =
        redisProperties.getSentinel().getNodes().stream()
            .map(i -> format(i))
            .toArray(String[]::new);
    config
        .useSentinelServers()
        .setMasterName(redisProperties.getSentinel().getMaster())
        .setPassword(redisProperties.getPassword())
        .setTimeout(30000)
        .addSentinelAddress(addSentinelAddress);

    // single 模式
    // config
    //     .useSingleServer()
    //     .setAddress(format(factory.getHostName(), factory.getPort()))
    //     .setPassword(factory.getPassword());
    return Redisson.create(config);
  }
}
