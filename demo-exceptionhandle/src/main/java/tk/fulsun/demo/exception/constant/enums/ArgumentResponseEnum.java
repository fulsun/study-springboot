package tk.fulsun.demo.exception.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tk.fulsun.demo.exception.assertion.ArgumentExceptionAssert;

/**
 * @author fulsun
 * @date 7/1/2021
 */
@Getter
@AllArgsConstructor
public enum ArgumentResponseEnum implements ArgumentExceptionAssert {

  /** 绑定参数校验异常 */
  VALID_ERROR(6000, "参数校验异常"),
  ;

  /** 返回码 */
  private int code;
  /** 返回消息 */
  private String message;
}
