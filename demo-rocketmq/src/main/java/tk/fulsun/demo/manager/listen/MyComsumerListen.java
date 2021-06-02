package tk.fulsun.demo.manager.listen;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * 创建消费者服务
 * <pre>
 * @author fulsun
 * @date 2021/6/87:50
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "topic_0", consumerGroup = "user_consumer_group")
public class MyComsumerListen implements RocketMQListener<String> {

  @Override
  public void onMessage(String message) {
    log.info("Receive message：" + message);
  }
}
