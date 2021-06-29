package tk.fulsun.demo.controller;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.fulsun.demo.config.RocketMQProperties;
import tk.fulsun.demo.manager.Producer;

/**
 * @author fulsun
 * @description: 发送延时消息
 * @date 6/8/2021 1:45 PM
 */
@RestController
public class DelayMessage {
  @Autowired private Producer producer;

  @Autowired private RocketMQProperties rocketMQProperties;

  @GetMapping("mq/delay")
  public void delay()
      throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
    int totalMessagesToSend = 10;
    for (int i = 0; i < totalMessagesToSend; i++) {
      Message message =
          new Message(
              rocketMQProperties.getSubscribe().get(0),
              ("Hello scheduled message " + i).getBytes());
      // 设置延时等级3,这个消息将在10s之后发送(现在只支持固定的几个时间,详看delayTimeLevel)
      message.setDelayTimeLevel(3);
      // 发送消息
      producer.getProducer().send(message);
    }
  }
}
