package tk.fulsun.demo.exception.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tk.fulsun.demo.exception.assertion.BusinessExceptionAssert;

/**
 * @author fulsun
 * @date 7/1/2021
 */
@Getter
@AllArgsConstructor
public enum BusinessResponseEnum implements BusinessExceptionAssert {
  BAD_USER_ID(7001, "Bad User Id."),
  USER_NOT_FOUND(7002, "User not found."),
  OPERATION_SUCCESS(7001, "操作成功."),
  OPERATION_FAIL(7002, "操作失败.");

  /** 返回码 */
  private int code;
  /** 返回消息 */
  private String message;
}
