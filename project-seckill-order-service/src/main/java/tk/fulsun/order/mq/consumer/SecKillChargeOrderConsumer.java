package tk.fulsun.order.mq.consumer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.fulsun.order.config.MQNamesrvConfig;
import tk.fulsun.order.mq.msg.MessageProtocolConst;
import tk.fulsun.order.utils.LogExceptionWapper;

/**
 * @author fsun7
 * @description: 秒杀下单消费者
 * @date 6/16/2021 3:13 PM
 */
@Slf4j
@Component
public class SecKillChargeOrderConsumer {
  @Autowired MQNamesrvConfig namesrvConfig;

  @Resource(name = "secKillChargeOrderListenerImpl")
  private MessageListenerConcurrently messageListener;

  private DefaultMQPushConsumer defaultMQPushConsumer;

  @PostConstruct
  public void init() {
    // pull 拉取消息,push 推送消息。push本质:拉取模式,主动的拉取消息避免消费者的堆积
    defaultMQPushConsumer =
        new DefaultMQPushConsumer(
            MessageProtocolConst.SECKILL_CHARGE_ORDER_TOPIC.getConsumerGroup(),
            null,
            // 平均分配队列算法，hash
            new AllocateMessageQueueAveragely());
    defaultMQPushConsumer.setNamesrvAddr(namesrvConfig.getNamesrv());
    // 消费模式:一个新的订阅组第一次启动从队列的最后位置开始消费 后续再启动接着上次消费的进度开始消费
    // defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
    // 从头开始消费
    defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
    // 消费模式:集群模式
    // 集群 CLUSTERING：同一条消息 只会被一个消费者节点消费到
    // 广播 BROADCASTING：同一条消息 每个消费者都会消费到
    defaultMQPushConsumer.setMessageModel(MessageModel.CLUSTERING);
    // 设置每次拉取的消息量，默认为1
    defaultMQPushConsumer.setConsumeMessageBatchMaxSize(1);
    // 注册消费的监听 并在此监听中消费信息，并返回消费的状态信息
    defaultMQPushConsumer.registerMessageListener(messageListener);
    // 订阅所有消息
    try {
      defaultMQPushConsumer.subscribe(
          MessageProtocolConst.SECKILL_CHARGE_ORDER_TOPIC.getTopic(), "*");
      // 启动消费者
      defaultMQPushConsumer.start();
    } catch (MQClientException e) {
      log.error(
          "[秒杀下单消费者]--SecKillChargeOrderConsumer加载异常!e={}", LogExceptionWapper.getStackTrace(e));
      throw new RuntimeException("[秒杀下单消费者]--SecKillChargeOrderConsumer加载异常!", e);
    }
    log.info("[秒杀下单消费者]--SecKillChargeOrderConsumer加载完成!");
  }
}
