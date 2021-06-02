package tk.fulsun.demo.message;

import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author fsun7
 * @description: 通过消息队列 发送 | 消费 消息
 * @date 6/3/2021 1:49 PM
 */
public class MessageSentTest {

  public static void main(String[] args) throws Exception {
    MessageSentTest instance = new MessageSentTest();
    instance.producer();
    instance.consumer();
  }

  public void consumer() throws InterruptedException, MQClientException {

    // 声明并初始化一个consumer
    // 需要一个consumer group名字作为构造方法的参数，这里为consumer1
    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer1");

    // 同样也要设置NameServer地址 192.168.56.101:9876;10.1.54.122:9876
    consumer.setNamesrvAddr("192.168.56.101:9876");

    // 这里设置的是一个consumer的消费策略
    // CONSUME_FROM_LAST_OFFSET 默认策略，从该队列最尾开始消费，即跳过历史消息
    // CONSUME_FROM_FIRST_OFFSET 从队列最开始开始消费，即历史消息（还储存在broker的）全部消费一遍
    // CONSUME_FROM_TIMESTAMP 从某个时间点开始消费，和setConsumeTimestamp()配合使用，默认是半个小时以前
    // consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

    // 设置消息最大拉取的最大数
    consumer.setConsumeMessageBatchMaxSize(2);

    // 设置consumer所订阅的Topic和Tag， *：代表全部的Tag ， ||:选择多个标签
    // consumer.subscribe("TopicTest", "*");
    // consumer.subscribe("TopicTest", "TagA || TagB");
    consumer.subscribe("TopicTest", "TagA");

    // 设置一个Listener，主要进行消息的逻辑处理
    consumer.registerMessageListener(
        new MessageListenerConcurrently() {

          @Override
          public ConsumeConcurrentlyStatus consumeMessage(
              List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

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

            // 返回消费状态
            // CONSUME_SUCCESS 消费成功
            // RECONSUME_LATER 消费失败，需要稍后重新消费
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
          }
        });

    // 调用start()方法启动consumer
    consumer.start();

    System.out.println("Consumer Started.");
  }

  public void producer() throws MQClientException, InterruptedException {

    // 声明并初始化一个producer
    // 需要一个producer group名字作为构造方法的参数，这里为producer1
    DefaultMQProducer producer = new DefaultMQProducer("producer1");

    // 设置NameServer地址,此处应改为实际NameServer地址，多个地址之间用；分隔
    // NameServer的地址必须有，但是也可以通过环境变量的方式设置，不一定非得写死在代码里
    producer.setNamesrvAddr("192.168.56.101:9876");

    // 调用start()方法启动一个producer实例
    producer.start();

    // 发送10条消息到Topic为TopicTest，tag为TagA，消息内容为“Hello RocketMQ”拼接上i的值
    for (int i = 0; i < 2; i++) {
      try {
        Message msg =
            new Message(
                "TopicTest", // topic 创建主题
                "TagA", // tag 用于过滤
                "keys 1", // 消息的唯一值 定位消息
                // body：RemotingHelper指定编码格式
                ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));

        // 调用producer的send()方法发送消息
        // 这里调用的是同步的方式，所以会有返回结果
        SendResult sendResult = producer.send(msg);

        // 打印返回结果，可以看到消息发送的状态以及一些相关信息
        System.out.println(sendResult);
      } catch (Exception e) {
        e.printStackTrace();
        Thread.sleep(1000);
      }
    }

    // 发送完消息之后，调用shutdown()方法关闭producer
    producer.shutdown();
  }
}
