package tk.fulsun.demo.message.transaction;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 *
 *
 * <pre>
 * 主要是通过消息的异步处理，保证消息和本地事务同时执行成功或失败，保证数据的最终一致性
 * producer -> send prepareMsg ------>  halfTopic(半消息broker)
 * broker写入成功后 --> 回调执行本地事务
 *  - 本地事务执行成功 - commit/rollback --> 写入消息到RealTopic -- 下发消息-- > 消费者消费
 *  - 本地事务执行失败 ---> 返回超时或Unknow状态 -- broker进行消息检查-- > 处理错误
 * </pre>
 *
 * @author fulsun
 * @description: 事务消息：在4.3版本后开放事务消息的特性
 * @date 6/3/2021 1:49 PM
 */
public class TransactionMessageTest {
  private final String TOPIC = "TransactionMessage";

  public static void main(String[] args) throws Exception {
    TransactionMessageTest instance = new TransactionMessageTest();
    instance.producer();
    // instance.consumer();
  }

  public void producer() throws Exception {
    // 创建事务消息的producer
    // DefaultMQProducer producer = new DefaultMQProducer("order_producer1");
    TransactionMQProducer producer = new TransactionMQProducer("transction_producer1");
    //创建线程池
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    producer.setExecutorService(executorService);
    producer.setNamesrvAddr("192.168.56.101:9876");
    producer.start();

    // 指定事务消息监听对象,执行本地事务和消息回查
    producer.setTransactionListener(new TransactionListenerImpl());

    Message msg =
        new Message(
            TOPIC,
            "TagC",
            ("RocketMQ send Transaction message").getBytes(RemotingHelper.DEFAULT_CHARSET));

    // 调用producer的send()方法发送事务消息
    TransactionSendResult sendResult = producer.sendMessageInTransaction(msg, "hello-transaction");
    System.out.println(sendResult);
    // 发送完消息之后，调用shutdown()方法关闭producer
    // producer.shutdown();
  }

  public void consumer() throws Exception {
    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("order_consumer1");
    consumer.setNamesrvAddr("192.168.56.101:9876");
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


