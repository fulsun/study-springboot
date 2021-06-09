package tk.fulsun.demo.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author fsun7
 * @description: fastjson配置类
 * @date 6/9/2021 11:20 AM
 */
@Configuration
public class FastjsonConfig implements WebMvcConfigurer {
  @Bean
  public HttpMessageConverters fastJsonHttpMessageConverters() {
    // 1、定义一个convert转换消息的对象
    FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
    // 2、添加fastjson的配置信息
    FastJsonConfig fastJsonConfig = new FastJsonConfig();
    fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
    // 3、解决浏览器乱码
    List<MediaType> fastMediaTypes = new ArrayList<>();
    fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
    fastConverter.setSupportedMediaTypes(fastMediaTypes);
    // 4、在convert中添加配置信息
    fastConverter.setFastJsonConfig(fastJsonConfig);
    // 5、将convert添加到converters中
    HttpMessageConverter<?> converter = fastConverter;
    return new HttpMessageConverters(converter);
  }

  /**
   * ⾃定义JSON转换器
   *
   * @param converters
   */
  // @Override
  // public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
  //   FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
  //   FastJsonConfig fastJsonConfig = new FastJsonConfig();
  //   fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
  //   // ⽇期格式化
  //   fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
  //   // 处理中⽂乱码问题
  //   List<MediaType> fastMediaTypes = new ArrayList<>();
  //   fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
  //   converter.setSupportedMediaTypes(fastMediaTypes);
  //   converter.setFastJsonConfig(fastJsonConfig);
  //   converters.add(converter);
  // }
}
