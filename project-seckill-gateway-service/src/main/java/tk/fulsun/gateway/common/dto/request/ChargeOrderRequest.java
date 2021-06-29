package tk.fulsun.gateway.common.dto.request;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author fulsun
 * @description: 下单接口请求参数
 * @date 6/16/2021 10:31 AM
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChargeOrderRequest implements Serializable {
  private static final long serialVersionUID = 2596328097263464531L;
  /** 充值手机号 */
  private String userPhoneNum;
  /** 下单金额 */
  private String chargePrice;
  /** 商品id */
  private String prodId;

  public ChargeOrderRequest setUserPhoneNum(String userPhoneNum) {
    this.userPhoneNum = userPhoneNum;
    return this;
  }

  public ChargeOrderRequest setChargePrice(String chargePrice) {
    this.chargePrice = chargePrice;
    return this;
  }

  public ChargeOrderRequest setProdId(String prodId) {
    this.prodId = prodId;
    return this;
  }
}
