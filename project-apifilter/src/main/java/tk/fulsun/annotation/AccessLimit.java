package tk.fulsun.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author fulsun
 * @description: 访问限制注解类
 * @date 6/9/2021 2:25 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AccessLimit {
  int seconds();

  int maxCount();

  boolean needLogin() default true;
}
