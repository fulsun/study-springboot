package tk.fulsun.gateway.mq.msg;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author fsun7
 * @description: 基础协议类
 * @date 6/16/2021 10:51 AM
 */
@Slf4j
@Data
@ToString
public abstract class BaseMsg {
  /** 版本号，默认1.0 */
  private String version = "1.0";
  /** 主题名 */
  private String topicName;

  public abstract String encode();

  public abstract void decode(String msg);
}
