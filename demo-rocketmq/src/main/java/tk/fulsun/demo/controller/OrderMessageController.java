package tk.fulsun.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.fulsun.demo.config.RocketMQProperties;
import tk.fulsun.demo.entity.OrderStep;
import tk.fulsun.demo.manager.Producer;

/**
 * @author fsun7
 * @description: 全局顺序消息：默认保证局部（broker有序）
 * @date 6/8/2021 1:39 PM
 */
@RestController
public class OrderMessageController {
  @Autowired private Producer producer;
  @Autowired private RocketMQProperties rocketMQProperties;

  @GetMapping("mq/order")
  public void orderMsg()
      throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
    String[] tags = new String[] {"TagA", "TagC", "TagD"};
    // 订单列表
    List<OrderStep> orderList = buildOrders();
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateStr = sdf.format(date);
    for (int i = 0; i < 10; i++) {
      // 加个时间前缀
      String body = dateStr + " Hello RocketMQ " + orderList.get(i);
      Message msg =
          new Message(
              rocketMQProperties.getSubscribe().get(0),
              tags[i % tags.length],
              "KEY" + i,
              body.getBytes());

      SendResult sendResult =
          producer
              .getProducer()
              .send(
                  msg,
                  new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                      Long id = (Long) arg; // 根据订单id选择发送queue
                      long index = id % mqs.size();
                      return mqs.get((int) index);
                    }
                  },
                  orderList.get(i).getOrderId()); // 订单id

      System.out.println(
          String.format(
              "SendResult status:%s, queueId:%d, body:%s",
              sendResult.getSendStatus(), sendResult.getMessageQueue().getQueueId(), body));
    }
  }

  /** 生成模拟订单数据 */
  private List<OrderStep> buildOrders() {
    List<OrderStep> orderList = new ArrayList<OrderStep>();

    OrderStep orderDemo = new OrderStep();
    orderDemo.setOrderId(15103111039L);
    orderDemo.setDesc("创建");
    orderList.add(orderDemo);

    orderDemo = new OrderStep();
    orderDemo.setOrderId(15103111065L);
    orderDemo.setDesc("创建");
    orderList.add(orderDemo);

    orderDemo = new OrderStep();
    orderDemo.setOrderId(15103111039L);
    orderDemo.setDesc("付款");
    orderList.add(orderDemo);

    orderDemo = new OrderStep();
    orderDemo.setOrderId(15103117235L);
    orderDemo.setDesc("创建");
    orderList.add(orderDemo);

    orderDemo = new OrderStep();
    orderDemo.setOrderId(15103111065L);
    orderDemo.setDesc("付款");
    orderList.add(orderDemo);

    orderDemo = new OrderStep();
    orderDemo.setOrderId(15103117235L);
    orderDemo.setDesc("付款");
    orderList.add(orderDemo);

    orderDemo = new OrderStep();
    orderDemo.setOrderId(15103111065L);
    orderDemo.setDesc("完成");
    orderList.add(orderDemo);

    orderDemo = new OrderStep();
    orderDemo.setOrderId(15103111039L);
    orderDemo.setDesc("推送");
    orderList.add(orderDemo);

    orderDemo = new OrderStep();
    orderDemo.setOrderId(15103117235L);
    orderDemo.setDesc("完成");
    orderList.add(orderDemo);

    orderDemo = new OrderStep();
    orderDemo.setOrderId(15103111039L);
    orderDemo.setDesc("完成");
    orderList.add(orderDemo);

    return orderList;
  }
}
