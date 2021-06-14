package tk.fulsun.demo.databases.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import tk.fulsun.demo.databases.DynamicDataSourceContextHolder;
import tk.fulsun.demo.databases.annotation.DataSource;

import java.lang.reflect.Method;

/**
 * @Description 多数据源，切面处理类
 * @Date 2021/6/14
 * @Created by 凉月-文
 */
@Aspect
@Component
@Slf4j
//@Order(1)
public class DataSourceAspect implements Ordered {
    //@within在类上设置
    //@annotation在方法上进行设置
    @Pointcut("@within(tk.fulsun.demo.databases.annotation.DataSource)||@annotation(tk.fulsun.demo.databases.annotation.DataSource)")
    public void dataSourcePointCut() {

    }


    @Around("dataSourcePointCut()")
    public Object before(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        //获取方法上的注解
        DataSource ds = method.getAnnotation(DataSource.class);
        if (ds == null || !DynamicDataSourceContextHolder.isContainsDataSource(ds.name())) {
            //获取类上面的注解
            ds = point.getTarget().getClass().getAnnotation(DataSource.class);
            if (ds != null && DynamicDataSourceContextHolder.isContainsDataSource(ds.name())) {
                DynamicDataSourceContextHolder.setDataSourceType(ds.name());
            } else {
                DynamicDataSourceContextHolder.setDataSourceType("master");
            }
        } else {
            DynamicDataSourceContextHolder.setDataSourceType(ds.name());
        }
        log.info("AOP动态切换数据源;dataSourceKey:" + (ds.name() == "" ? "默认数据源" : ds.name()));

        try {
            return point.proceed();
        } finally {
            //清理掉当前设置的数据源，让默认的数据源不受影响
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }


    @Override
    public int getOrder() {
        return 1;
    }

}
