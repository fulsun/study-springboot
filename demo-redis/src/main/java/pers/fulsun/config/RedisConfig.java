package pers.fulsun.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

  /**
   * 自定义RedisTemplate - 配置序列化配置，默认是jdk
   */
  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
    // 为了开发的方便，一般直接使用 <String, Object>
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(factory); // redis连接的线程安全工厂，源码也是这样配置的

    // Json序列化配置
    Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(
        Object.class);
    ObjectMapper om = new ObjectMapper();
    // 关闭自动检测。使用两个属性ALL(getter和setter)和字段来序列化和反序列化为json。
    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    // 指定jackson只使用字段
    // om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
    // om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    // 指定序列化输入类型,不指定那存储到redis里的数据将是没有类型的纯json，解析后是一个LinkHashMap类型
    // 指定序列化输入类型,java获取到数据后，将会将数据自动转化为转换前的类型
    // NON_FINAL:整个类、除final外的的属性信息都需要被序列化和反序列化
    // om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
        ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY);
    //解决jackson2无法序列化LocalDateTime的问题，这里扩展一个LocalDateTime类型，它是日期类型对象 jdk1.8出的（并且这个类是不可变的和线程安全的，可以研究一下它的API），当然还需要对这个对象进行json格式的转换，如下图：
    om.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
    om.registerModule(new JavaTimeModule());

    jackson2JsonRedisSerializer.setObjectMapper(om);
    // String 的序列化
    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

    // key采用String的序列化方式
    template.setKeySerializer(stringRedisSerializer);
    // value序列化方式采用jackson
    template.setValueSerializer(stringRedisSerializer);

    // hash的key也采用String的序列化方式
    template.setHashKeySerializer(stringRedisSerializer);
    // hash的value序列化方式采用jackson
    template.setHashValueSerializer(jackson2JsonRedisSerializer);
    // 非spring容器必须执行
    template.afterPropertiesSet();

    return template;
  }
}
