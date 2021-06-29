package tk.fulsun.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author fulsun
 * @description: 返回体包装
 * @date 6/16/2021 10:22 AM
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
  private String code;
  private String msg;
  private T data;

  /** 失败时候的调用 */
  public static <T> Result<T> error(CodeMsg codeMsg) {
    return new Result<T>(codeMsg);
  }

  public static <T> Result<T> success(CodeMsg codeMsg, T t) {
    return new Result<T>(codeMsg.getCode(), codeMsg.getMsg(), t);
  }

  private Result(CodeMsg codeMsg) {
    if (codeMsg != null) {
      this.code = codeMsg.getCode();
      this.msg = codeMsg.getMsg();
      this.data = null;
    }
  }

  public Result<T> setCode(String code) {
    this.code = code;
    return this;
  }

  public Result<T> setMsg(String msg) {
    this.msg = msg;
    return this;
  }

  public Result<T> setData(T data) {
    this.data = data;
    return this;
  }
}
