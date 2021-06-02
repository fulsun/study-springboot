package tk.fulsun.demo.manager;

import javax.annotation.PostConstruct;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.fulsun.demo.config.RocketMQProperties;

/**
 *
 * <pre>
 * 生产者服务
 * <pre>
 * @author fulsun
 * @date 2021/6/76:28
 */
@Component
public class Producer {

  @Autowired private RocketMQProperties rocketMQProperties;

  // 示例生产者
  private DefaultMQProducer producer;

  @PostConstruct
  public void init() {
    producer = new DefaultMQProducer(rocketMQProperties.getProducerGroupName());
    // 不开启vip通道 开通口端口会减2
    producer.setVipChannelEnabled(false);
    // 指定nameserver地址，多个地址使用,分隔
    producer.setNamesrvAddr(rocketMQProperties.getNamesrvAddr());
    start();
  }

  public DefaultMQProducer getProducer() {
    return this.producer;
  }

  public void start() {
    try {
      this.producer.start();
    } catch (MQClientException e) {
      e.printStackTrace();
    }
  }

  public void shutdown() {
    this.producer.shutdown();
  }
}
