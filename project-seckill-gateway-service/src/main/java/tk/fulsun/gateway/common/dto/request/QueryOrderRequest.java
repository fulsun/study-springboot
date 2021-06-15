package tk.fulsun.gateway.common.dto.request;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author fsun7
 * @description: 查单请求实体
 * @date 6/16/2021 10:34 AM
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QueryOrderRequest implements Serializable {
  private static final long serialVersionUID = 7143860158400568786L;

  /** 用户下单手机号 */
  private String userPhoneNum;
  /** 商品id */
  private String prodId;

  public QueryOrderRequest setUserPhoneNum(String userPhoneNum) {
    this.userPhoneNum = userPhoneNum;
    return this;
  }

  public QueryOrderRequest setProdId(String prodId) {
    this.prodId = prodId;
    return this;
  }
}
