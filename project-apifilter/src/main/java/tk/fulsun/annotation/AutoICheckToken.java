package tk.fulsun.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author fsun7
 * @description: 检查方法幂等
 * @date 6/10/2021 1:52 PM
 */
@Target(ElementType.METHOD) // 表示它只能放在方法上
@Retention(RetentionPolicy.RUNTIME) // etentionPolicy.RUNTIME表示它在运行时
public @interface AutoICheckToken {}
