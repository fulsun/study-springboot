package tk.fulsun.demo.exception.assertion;

import cn.hutool.core.util.ArrayUtil;
import java.text.MessageFormat;
import tk.fulsun.demo.exception.BaseException;
import tk.fulsun.demo.exception.BusinessException;
import tk.fulsun.demo.exception.constant.IResponseEnum;

/**
 * <p>业务异常断言</p>
 * @author fulsun
 * @date 7/1/2021
 */
public interface BusinessExceptionAssert extends IResponseEnum, Assert {
    @Override
    default BaseException newException(Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }
        return new BusinessException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }

        return new BusinessException(this, args, msg, t);
    }

}
