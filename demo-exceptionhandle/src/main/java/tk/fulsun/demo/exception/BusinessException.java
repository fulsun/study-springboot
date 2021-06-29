package tk.fulsun.demo.exception;

import tk.fulsun.demo.exception.constant.IResponseEnum;

/**
 * 业务异常
 *
 * <p>业务处理时，出现异常，可以抛出该异常
 *
 * @author fulsun
 * @date 7/1/2021
 */
public class BusinessException extends BaseException {
  private static final long serialVersionUID = 1L;

  public BusinessException(IResponseEnum responseEnum, Object[] args, String message) {
    super(responseEnum, args, message);
  }

  public BusinessException(
      IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
    super(responseEnum, args, message, cause);
  }
}
