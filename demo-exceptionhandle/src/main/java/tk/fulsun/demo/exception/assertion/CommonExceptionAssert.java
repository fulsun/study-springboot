package tk.fulsun.demo.exception.assertion;

import java.text.MessageFormat;
import tk.fulsun.demo.exception.ArgumentException;
import tk.fulsun.demo.exception.BaseException;
import tk.fulsun.demo.exception.constant.IResponseEnum;

/**
 * @author fulsun
 * @date 7/1/2021
 */
public interface CommonExceptionAssert extends IResponseEnum, Assert{
    @Override
    default BaseException newException(Object... args) {
        String msg = this.getMessage();
        if (null != args && args.length != 0) {
            msg = MessageFormat.format(this.getMessage(), args);
        }

        return new ArgumentException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = this.getMessage();
        if (null != args && args.length != 0) {
            msg = MessageFormat.format(this.getMessage(), args);
        }

        return new ArgumentException(this, args, msg, t);
    }

}
