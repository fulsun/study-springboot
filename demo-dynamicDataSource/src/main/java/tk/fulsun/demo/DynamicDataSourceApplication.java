package tk.fulsun.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import tk.fulsun.demo.databases.DynamicDataSourceRegister;

/**
 * @Description 主启动类
 * @Date 2021/6/14
 * @Created by 凉月-文
 */
@SpringBootApplication
@Import({DynamicDataSourceRegister.class})
@MapperScan(basePackages = "tk.fulsun.demo.mapper")
public class DynamicDataSourceApplication{
    public static void main(String[] args) {
        SpringApplication.run(DynamicDataSourceApplication.class, args);
    }
}
