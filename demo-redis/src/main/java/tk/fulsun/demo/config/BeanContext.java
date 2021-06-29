package tk.fulsun.demo.config;

/**
 * @author fulsun
 * @description: 获取Spring中的Bean
 * @date 6/1/2021 4:58 PM
 */
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 手动获取Bean https://blog.csdn.net/tiger0709/article/details/78270768
 * https://blog.csdn.net/u011493599/article/details/78522315
 */
@Component
public class BeanContext implements ApplicationContextAware {

  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    BeanContext.applicationContext = applicationContext;
  }

  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  @SuppressWarnings("unchecked")
  public static <T> T getBean(String name) throws BeansException {
    return (T) applicationContext.getBean(name);
  }

  public static <T> T getBean(Class<T> clz) throws BeansException {
    return (T) applicationContext.getBean(clz);
  }
}
