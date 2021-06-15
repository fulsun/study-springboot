package tk.fulsun.gateway.common.dto.response;

import java.io.Serializable;
import lombok.Getter;
import lombok.ToString;

/**
 * @author fsun7
 * @description: 下单sdk接口返回参数
 * @date 6/16/2021 11:54 AM
 */
@Getter
@ToString
public class ChargeOrderResponse implements Serializable {
  private static final long serialVersionUID = -5685058946404699059L;

  /** 秒杀订单号 */
  private String orderId;
  /** 用户下单手机号 */
  private String userPhoneNo;
  /** 商品id */
  private String prodId;
  /** 用户交易金额 */
  private String chargeMoney;

  public ChargeOrderResponse setOrderId(String orderId) {
    this.orderId = orderId;
    return this;
  }

  public ChargeOrderResponse setUserPhoneNo(String userPhoneNo) {
    this.userPhoneNo = userPhoneNo;
    return this;
  }

  public ChargeOrderResponse setProdId(String prodId) {
    this.prodId = prodId;
    return this;
  }

  public ChargeOrderResponse setChargeMoney(String chargeMoney) {
    this.chargeMoney = chargeMoney;
    return this;
  }
}
