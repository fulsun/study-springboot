package tk.fulsun.order.dto.response;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.ToString;

/**
 * @author fsun7
 * @description: 平台订单查询返回实体
 * @date 6/16/2021 11:58 AM
 */
@Getter
@ToString
public class QueryOrderResponse implements Serializable {
  private static final long serialVersionUID = 8752405981800372807L;

  private String orderId;
  private Integer orderStatus;
  private String userPhoneNo;
  private String prodId;
  private String prodName;
  private String chargeMoney;
  private Date chargeTime;
  private Date finishTime;

  public QueryOrderResponse setOrderId(String orderId) {
    this.orderId = orderId;
    return this;
  }

  public QueryOrderResponse setOrderStatus(Integer orderStatus) {
    this.orderStatus = orderStatus;
    return this;
  }

  public QueryOrderResponse setUserPhoneNo(String userPhoneNo) {
    this.userPhoneNo = userPhoneNo;
    return this;
  }

  public QueryOrderResponse setProdId(String prodId) {
    this.prodId = prodId;
    return this;
  }

  public QueryOrderResponse setProdName(String prodName) {
    this.prodName = prodName;
    return this;
  }

  public QueryOrderResponse setChargeMoney(String chargeMoney) {
    this.chargeMoney = chargeMoney;
    return this;
  }

  public QueryOrderResponse setChargeTime(Date chargeTime) {
    this.chargeTime = chargeTime;
    return this;
  }

  public QueryOrderResponse setFinishTime(Date finishTime) {
    this.finishTime = finishTime;
    return this;
  }
}
