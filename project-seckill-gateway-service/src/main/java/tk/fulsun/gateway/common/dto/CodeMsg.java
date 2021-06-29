package tk.fulsun.gateway.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author fulsun
 * @description: 错误码封装
 * @date 6/16/2021 10:24 AM
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CodeMsg {
  private String code;
  private String msg;

  /** 通用的错误码 */
  public static CodeMsg SUCCESS = new CodeMsg("10000", "SUCCESS");

  public static CodeMsg ORDER_INLINE = new CodeMsg("10000", "秒杀订单排队中");
  public static CodeMsg SERVER_ERROR = new CodeMsg("20000", "SERVER_ERROR");
  public static CodeMsg BIZ_ERROR = new CodeMsg("40004", "BIZ_ERROR");
  public static CodeMsg PARAM_INVALID = new CodeMsg("40005", "入参校验失败");

  public static CodeMsg PRODUCT_STOCK_NOT_ENOUGH = new CodeMsg("40006", "秒杀商品库存不足");
  public static CodeMsg PRODUCT_NOT_EXIST = new CodeMsg("40007", "秒杀商品不存在");

  public static CodeMsg SECKILL_ORDER_NOT_EXIST = new CodeMsg("41004", "秒杀订单不存在");

  public CodeMsg setCode(String code) {
    this.code = code;
    return this;
  }

  public CodeMsg setMsg(String msg) {
    this.msg = msg;
    return this;
  }

  public CodeMsg fillArgs(Object... args) {
    String code = this.code;
    String message = String.format(this.msg, args);
    return new CodeMsg(code, message);
  }
}
