package tk.fulsun.demo.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.fulsun.demo.config.RocketMQProperties;
import tk.fulsun.demo.manager.Producer;

/**
 * @authorrefsun7
 * @description: 基本的消息发送
 * @date 6/8/2021 1:47 PM
 */
@RestController
public class BasicMessage {
  @Autowired private Producer producer;
  @Autowired private RocketMQProperties rocketMQProperties;

  private List<String> mesList;

  /** 初始化消息 */
  public BasicMessage() {
    mesList = new ArrayList<>();
    mesList.add("小小");
    mesList.add("爸爸");
    mesList.add("妈妈");
    mesList.add("爷爷");
    mesList.add("奶奶");
  }

  @RequestMapping("/text/rocketmq")
  public Object callback() throws Exception {
    // 总共发送五次消息
    for (String s : mesList) {
      // 创建生产信息
      Message message =
          new Message(
              rocketMQProperties.getSubscribe().get(0), "testtag", ("小小一家人的称谓:" + s).getBytes());
      // 发送
      SendResult sendResult = producer.getProducer().send(message);
      System.out.println("输出生产者信息={" + sendResult + "}");
    }
    return "成功";
  }
}
