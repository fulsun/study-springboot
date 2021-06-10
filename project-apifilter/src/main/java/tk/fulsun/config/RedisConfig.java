package tk.fulsun.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

  /** 自定义RedisTemplate - 配置序列化配置，默认是jdk */
  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
    // 为了开发的方便，一般直接使用 <String, Object>
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(factory); // redis连接的线程安全工厂，源码也是这样配置的

    // Json序列化配置
    GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
    // String 的序列化
    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    // key采用String的序列化方式
    template.setKeySerializer(stringRedisSerializer);
    // value序列化方式采用jackson
    template.setValueSerializer(stringRedisSerializer);
    // hash的key也采用String的序列化方式
    template.setHashKeySerializer(stringRedisSerializer);
    // hash的value序列化方式采用jackson
    template.setHashValueSerializer(fastJsonRedisSerializer);
    // 非spring容器必须执行
    template.afterPropertiesSet();
    // 设置其他默认的序列化方式为fastjson
    template.setDefaultSerializer(fastJsonRedisSerializer);
    return template;
  }
}
