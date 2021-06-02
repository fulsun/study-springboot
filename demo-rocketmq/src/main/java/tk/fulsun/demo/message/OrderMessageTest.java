package tk.fulsun.demo.message;

import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author fsun7
 * @description: 保证发送消息的顺序: 默认保证的是局部消息（broker类的队列中是有序的）
 * @date 6/3/2021 1:49 PM
 */
public class OrderMessageTest {
  private final String TOPIC = "OrderTopicTest";

  public static void main(String[] args) throws Exception {
    OrderMessageTest instance = new OrderMessageTest();
    instance.orderProducer();
    instance.orderConsumer();

    // orderProducer
  }

  /** 顺序消息： 每次发送到同一个队列 */
  public void orderProducer() throws Exception {
    DefaultMQProducer producer = new DefaultMQProducer("order_producer1");
    producer.setNamesrvAddr("192.168.56.101:9876");
    producer.start();

    for (int i = 0; i < 5; i++) {
      Message msg =
          new Message(
              TOPIC, // topic 创建主题
              "orderMessage", // tag 用于过滤
              // "keys 1", // 消息的唯一值 定位消息
              // body：RemotingHelper指定编码格式
              ("Hello " + i + ", RocketMQ send order message")
                  .getBytes(RemotingHelper.DEFAULT_CHARSET));

      // 调用producer的send()方法发送消息
      // 一个参数调用的是同步的方式，所以会有返回结果
      // 第二个参数指定消息队列对象(将所有的消息对内传进来)
      // 第三个参数： 指定队列的序号
      SendResult sendResult =
          producer.send(
              msg,
              new MessageQueueSelector() {
                @Override
                /** list；队列数，集群模式下为 集群数*队列数 message： 消息对象 O: 接受第三个参数 */
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                  // 获取指定队列的下标
                  Integer index = (Integer) o;
                  // 返回对应下标队列
                  return list.get(index);
                }
              },
              0);
      System.out.println(sendResult);
    }
    // 发送完消息之后，调用shutdown()方法关闭producer
    producer.shutdown();
  }

  public void orderConsumer() throws Exception {
    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("order_consumer1");
    consumer.setNamesrvAddr("192.168.56.101:9876");
    // 这里设置的是一个consumer的消费策略
    // CONSUME_FROM_LAST_OFFSET 默认策略，从该队列最尾开始消费，即跳过历史消息
    // CONSUME_FROM_FIRST_OFFSET 从队列最开始开始消费，即历史消息（还储存在broker的）全部消费一遍
    // CONSUME_FROM_TIMESTAMP 从某个时间点开始消费，和setConsumeTimestamp()配合使用，默认是半个小时以前
    consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

    // 设置消息最大拉取的最大数
    consumer.setConsumeMessageBatchMaxSize(2);

    consumer.subscribe(TOPIC, "*");

    // 设置一个Listener，进行有序消费
    consumer.registerMessageListener(
        new MessageListenerOrderly() {
          @Override
          public ConsumeOrderlyStatus consumeMessage(
              List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
            printConsumerMsg(list);
            return ConsumeOrderlyStatus.SUCCESS;
          }
        });

    // 调用start()方法启动consumer
    consumer.start();

    System.out.println("Consumer Started.");
  }

  private void printConsumerMsg(List<MessageExt> msgs) {
    // 迭代消息
    for (MessageExt msg : msgs) {

      String topic = msg.getTopic();
      String tags = msg.getTags();
      try {
        String body = new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
        System.out.println(
            Thread.currentThread().getName()
                + " Receive New Messages: "
                + topic
                + " "
                + tags
                + " "
                + body);
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }

      System.out.println("=======================================");
    }
  }
}
