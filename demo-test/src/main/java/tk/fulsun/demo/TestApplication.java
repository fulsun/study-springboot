package tk.fulsun.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description 主启动类
 * @Date 2021/6/13
 * @Created by 凉月-文
 */
@SpringBootApplication
@MapperScan({"tk.fulsun.demo.mapper", "tk.fulsun.demo.*.mapper"})
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
