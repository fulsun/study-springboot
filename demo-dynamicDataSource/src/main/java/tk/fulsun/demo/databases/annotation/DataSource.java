package tk.fulsun.demo.databases.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 多数据源注解
 * @Date 2021/6/14
 * @Created by 凉月-文
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    /**
     * 路由的DataSource名称，默认为MASTER
     */
    String name() default "MASTER";


}
