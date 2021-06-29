package tk.fulsun.demo.exception;

import tk.fulsun.demo.exception.assertion.Assert;

/**
 * 只包装了 错误信息 的 {@link RuntimeException}.
 * 用于 {@link Assert} 中用于包装自定义异常信息
 *
 * @author sprainkle
 * @date 2020/6/20
 */
public class WrapMessageException extends RuntimeException {

    public WrapMessageException(String message) {
        super(message);
    }

    public WrapMessageException(String message, Throwable cause) {
        super(message, cause);
    }

}
