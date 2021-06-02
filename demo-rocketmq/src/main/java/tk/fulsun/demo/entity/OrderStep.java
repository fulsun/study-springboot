package tk.fulsun.demo.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author fsun7
 * @description: 订单的步骤
 * @date 6/8/2021 1:42 PM
 */
@Data
@ToString
public class OrderStep {
  private long orderId;
  private String desc;
}
