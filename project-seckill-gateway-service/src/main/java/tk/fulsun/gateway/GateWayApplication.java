package tk.fulsun.gateway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fsun7
 * @description: 网关服务启动类
 * @date 6/15/2021 3:55 PM
 */
@SpringBootApplication
@MapperScan("tk.fulsun.gateway.common.dao")
public class GateWayApplication {
  public static void main(String[] args) {
    SpringApplication.run(GateWayApplication.class, args);
  }
}
