package tk.fulsun.gateway.common.dao.dataobject;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author fsun7
 * @description: 秒杀商品数据库 t_seckill_product 映射实体
 * @date 6/15/2021 5:08 PM
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class secKillProduct {
  private int id;
  private String prodId;
  private String prodName;
  private int prodStock;
  private BigDecimal prodPrice;
  private int prodStatus;
}
