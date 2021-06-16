package tk.fulsun.order.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author fsun7
 * @description: RocketMQ NameServer配置
 * @date 6/15/2021 4:14 PM
 */
@Component
@Data
public class MQNamesrvConfig {
  @Value("${rocketmq.nameserver}")
  String namesrv;
}
