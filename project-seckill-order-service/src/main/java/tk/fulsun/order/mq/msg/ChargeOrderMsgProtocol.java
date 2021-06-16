package tk.fulsun.order.mq.msg;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.io.Serializable;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;

/**
 * @author fsun7
 * @description: 单结果通知协议
 * @date 6/16/2021 10:54 AM
 */
@Getter
@ToString
public class ChargeOrderMsgProtocol extends BaseMsg implements Serializable {

  /** 订单号 */
  private String orderId;
  /** 用户下单手机号 */
  private String userPhoneNo;
  /** 商品id */
  private String prodId;
  /** 用户交易金额 */
  private String chargeMoney;

  private Map<String, String> header;
  private Map<String, String> body;

  public ChargeOrderMsgProtocol setOrderId(String orderId) {
    this.orderId = orderId;
    return this;
  }

  public ChargeOrderMsgProtocol setUserPhoneNo(String userPhoneNo) {
    this.userPhoneNo = userPhoneNo;
    return this;
  }

  public ChargeOrderMsgProtocol setProdId(String prodId) {
    this.prodId = prodId;
    return this;
  }

  public ChargeOrderMsgProtocol setChargeMoney(String chargeMoney) {
    this.chargeMoney = chargeMoney;
    return this;
  }

  @Override
  public String encode() {
    // 组装消息协议头: ImmutableMap 不可变的map
    ImmutableMap.Builder headerBuilder =
        new ImmutableMap.Builder<String, String>()
            .put("version", this.getVersion())
            .put("topicName", MessageProtocolConst.SECKILL_CHARGE_ORDER_TOPIC.getTopic());
    header = headerBuilder.build();

    body =
        new ImmutableMap.Builder<String, String>()
            .put("orderId", this.getOrderId())
            .put("userPhoneNo", this.getUserPhoneNo())
            .put("prodId", this.getProdId())
            .put("chargeMoney", this.getChargeMoney())
            .build();

    ImmutableMap<String, Object> map =
        new ImmutableMap.Builder<String, Object>().put("header", header).put("body", body).build();

    // 返回序列化消息Json串
    String ret_string = null;
    try {
      ret_string = JSON.toJSONString(map);
    } catch (Exception e) {
      throw new RuntimeException("ChargeOrderMsgProtocol消息序列化json异常", e);
    }
    return ret_string;
  }

  @Override
  public void decode(String msg) {
    Preconditions.checkNotNull(msg);
    try {
      Map map = JSON.parseObject(msg, Map.class);
      // header
      Map<String, String> header = (Map) map.get("header");
      this.setVersion(header.get("version"));
      this.setTopicName(header.get("topicName"));
      // body
      Map<String, String> body = (Map) map.get("body");
      this.setOrderId(body.get("orderId"));
      this.setUserPhoneNo(body.get("userPhoneNo"));
      this.setChargeMoney(body.get("chargeMoney"));
      this.setProdId(body.get("prodId"));
    } catch (Exception e) {
      throw new RuntimeException("ChargeOrderMsgProtocol消息反序列化异常", e);
    }
  }
}
