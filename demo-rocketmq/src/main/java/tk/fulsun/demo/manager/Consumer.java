package tk.fulsun.demo.manager;

import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.fulsun.demo.config.RocketMQProperties;

/**
 * <pre>
 * 消费者服务
 * <pre>
 * @author fulsun
 * @date 2021/6/7 6:28
 */
@Component
public class Consumer {
  DefaultMQPushConsumer defaultMQPushConsumer;
  @Autowired private RocketMQProperties rocketMQProperties;

  // pull 拉取消息  push 推送消息 本质 拉取模式  好处 主动的拉取消息避免消费者的堆积
  @PostConstruct
  public void init() throws MQClientException {

    defaultMQPushConsumer = new DefaultMQPushConsumer(rocketMQProperties.getConsumerGroupName());
    // 订阅主题和 标签（ * 代表所有标签)下信息
    defaultMQPushConsumer.subscribe(rocketMQProperties.getSubscribe().get(0), "*");
    // 消费模式:一个新的订阅组第一次启动从队列的最后位置开始消费 后续再启动接着上次消费的进度开始消费
    defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
    defaultMQPushConsumer.setNamesrvAddr(rocketMQProperties.getNamesrvAddr());
    defaultMQPushConsumer.setConsumeMessageBatchMaxSize(10);
    // 注册消费的监听 并在此监听中消费信息，并返回消费的状态信息
    defaultMQPushConsumer.registerMessageListener(
        new MessageListenerConcurrently() {

          @Override
          public ConsumeConcurrentlyStatus consumeMessage(
              // msgs中只收集同一个topic，同一个tag，并且key相同的message
              // 会把不同的消息分别放置到不同的队列中
              List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
            int size = msgs.size();
            System.out.println("打印的数据是多少" + size);
            for (MessageExt msg : msgs) {
              try {
                // String topic = msg.getTopic();
                // String tags = msg.getTags();
                // String keys = msg.getKeys();
                // String body = new String(msg.getBody(), "utf-8");
                // 再次修改数据库转态
                System.out.println(
                    "consumeThread="
                        + Thread.currentThread().getName()
                        + "queueId="
                        + msg.getQueueId()
                        + ", content:"
                        + new String(msg.getBody(), "utf-8"));
              } catch (Exception e) {
                e.printStackTrace();
                // ACK应答：再次下发消息 消息失败
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
              }
            }
            // ACK应答：消费成功 删除消息  commitlog日志 进度 删除
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
          }
        });
    defaultMQPushConsumer.start();
    System.out.println("消费者启动了~~~");
  }
}
