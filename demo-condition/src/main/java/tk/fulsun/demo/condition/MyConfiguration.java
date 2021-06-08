package tk.fulsun.demo.condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {
  @Bean(name = "emailerService")
  @Conditional(WindowsCondition.class)
  public void windowsEmailerService() {
    System.out.println("send windows email");
  }

  @Bean(name = "emailerService")
  @Conditional(LinuxCondition.class)
  public void linuxEmailerService() {
    System.out.println("send linux email");
  }
}
