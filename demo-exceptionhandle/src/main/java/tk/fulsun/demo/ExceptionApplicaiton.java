package tk.fulsun.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fulsun
 * @description: TODO
 * @date 6/30/2021 11:16 AM
 */
@SpringBootApplication
@MapperScan({"tk.fulsun.demo.mapper", "tk.fulsun.demo.*.mapper"})
public class ExceptionApplicaiton {
  public static void main(String[] args) {
    SpringApplication.run(ExceptionApplicaiton.class, args);
  }
}
