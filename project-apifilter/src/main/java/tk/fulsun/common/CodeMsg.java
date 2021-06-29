package tk.fulsun.common;

/**
 * @author fulsun
 * @description: 返回的枚举常量
 * @date 6/9/2021 3:06 PM
 */
public enum CodeMsg {
  ACCESS_LIMIT_REACHED(601, "超出访问次数"),
  ILLEGAL_REQUEST(602, "非法请求");

  private String message;
  private int code;

  CodeMsg(String msg) {
    this.message = msg;
  }

  CodeMsg(int code, String msg) {
    this.code = code;
    this.message = msg;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }
}
