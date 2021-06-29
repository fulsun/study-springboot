package tk.fulsun.demo;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.fulsun.demo.config.RocketMQProperties;
import tk.fulsun.demo.manager.Producer;

/**
 * @author fulsun
 * @description: 测试生产消费
 * @date 6/2/2021 5:25 PM
 */
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private Producer producer;
    @Autowired
    private RocketMQProperties rojectMQProperties;

    @Test
    public void sendMsg() throws Exception {
        DefaultMQProducer producer = this.producer.getProducer();
        byte[] body = "hello 1".getBytes(RemotingHelper.DEFAULT_CHARSET);
        Message message = new Message(rojectMQProperties.getSubscribe().get(0), "tag1", body);
        SendResult send = producer.send(message);
        System.out.println(send);
    }

    // @Test
    // public void test1() {
    //     rocketMQTemplate.convertAndSend(rojectMQProperties.getConsumerGroupName(), "hello springboot rocketmq");
    //     // convertSendAndReceive只有确定消费者接收到消息，才会发送下一条信息，每条消息之间会有间隔时间
    //
    // }
}
