package tk.fulsun.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tk.fulsun.interceptor.ApiAccessInterceptor;
import tk.fulsun.interceptor.AutoCheckTokenInterceptor;

/**
 * @author fulsun
 * @description: 自定义web的配置
 * @date 6/9/2021 3:17 PM
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Autowired private ApiAccessInterceptor apiAccessInteaceptor;
  @Autowired private AutoCheckTokenInterceptor autoCheckTokenInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(apiAccessInteaceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/css/**", "/images/**", "/js/**", "/login.html");
    registry
        .addInterceptor(autoCheckTokenInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/css/**", "/images/**", "/js/**", "/login.html");
  }
}
