package tk.fulsun.gateway.mq.producer;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.fulsun.gateway.common.config.MQNamesrvConfig;
import tk.fulsun.gateway.common.utils.LogExceptionWapper;
import tk.fulsun.gateway.mq.msg.MessageProtocolConst;

/**
 * @author fulsun
 * @description: 秒杀订单生产者初始化
 * @date 6/16/2021 11:33 AM
 */
@Component
@Slf4j
public class SecKillChargeOrderProducer {
  @Autowired private MQNamesrvConfig mqNamesrvConfig;

  // 示例生产者
  private DefaultMQProducer producer;

  @PostConstruct
  public void init() {
    producer =
        new DefaultMQProducer(MessageProtocolConst.SECKILL_CHARGE_ORDER_TOPIC.getProducerGroup());
    // 不开启vip通道 开通口端口会减2
    producer.setVipChannelEnabled(false);
    // 指定nameserver地址，多个地址使用,分隔
    producer.setNamesrvAddr(mqNamesrvConfig.getNamesrv());
    // 发送失败重试次数
    producer.setRetryTimesWhenSendFailed(3);
    start();
  }

  public DefaultMQProducer getProducer() {
    return this.producer;
  }

  public void start() {
    try {
      this.producer.start();
    } catch (MQClientException e) {
      log.error(
          "[秒杀订单生产者]--SecKillChargeOrderProducer加载异常!e={}", LogExceptionWapper.getStackTrace(e));
      throw new RuntimeException("[秒杀订单生产者]--SecKillChargeOrderProducer加载异常!", e);
    }
    log.info("[秒杀订单生产者]--SecKillChargeOrderProducer加载完成!");
  }

  public void shutdown() {
    this.producer.shutdown();
  }
}
