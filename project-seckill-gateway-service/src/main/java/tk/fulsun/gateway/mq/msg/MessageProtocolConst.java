package tk.fulsun.gateway.mq.msg;

/**
 * @author fulsun
 * @description: 消息协议常量
 * @date 6/16/2021 11:16 AM
 */
public enum MessageProtocolConst {
  /** SECKILL_CHARGE_ORDER_TOPIC 秒杀下单消息协议 */
  SECKILL_CHARGE_ORDER_TOPIC(
      "SECKILL_ORDER_TOPIC", "GID_FULSUN_PRODUCE", "GID_GULSUN_CONSUMER", "秒杀下单消息协议"),
  ;
  /** 消息主题 */
  private String topic;
  /** 生产者组 */
  private String producerGroup;
  /** 消费者组 */
  private String consumerGroup;
  /** 消息描述 */
  private String desc;

  MessageProtocolConst(String topic, String producerGroup, String consumerGroup, String desc) {
    this.topic = topic;
    this.producerGroup = producerGroup;
    this.consumerGroup = consumerGroup;
    this.desc = desc;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getProducerGroup() {
    return producerGroup;
  }

  public void setProducerGroup(String producerGroup) {
    this.producerGroup = producerGroup;
  }

  public String getConsumerGroup() {
    return consumerGroup;
  }

  public void setConsumerGroup(String consumerGroup) {
    this.consumerGroup = consumerGroup;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }
}
