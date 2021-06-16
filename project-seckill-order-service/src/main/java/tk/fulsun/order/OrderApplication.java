package tk.fulsun.order;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;

/**
 * @Description 订单服务启动类
 * @Date 2021/6/16
 * @Created by 凉月-文
 */
@SpringBootApplication
@MapperScan("tk.fulsun.order.dao")
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
