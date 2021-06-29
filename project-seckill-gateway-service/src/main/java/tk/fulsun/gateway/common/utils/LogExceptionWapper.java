package tk.fulsun.gateway.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author fulsun
 * @description: 日志工具
 * @date 6/16/2021 11:49 AM
 */
public class LogExceptionWapper {
  /**
   * 获取完整栈轨迹
   *
   * @param aThrowable
   * @return
   */
  public static String getStackTrace(Throwable aThrowable) {
    final Writer result = new StringWriter();
    final PrintWriter printWriter = new PrintWriter(result);
    aThrowable.printStackTrace(printWriter);
    return result.toString();
  }
}
