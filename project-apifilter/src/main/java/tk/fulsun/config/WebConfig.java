package tk.fulsun.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author fsun7
 * @description: 自定义web的配置
 * @date 6/9/2021 3:17 PM
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Autowired private ApiAccessInterceptor apiAccessInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(apiAccessInterceptor);
  }
}
