package tk.fulsun.demo.databases;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 动态数据源上下文管理
 * @Date 2021/6/14
 * @Created by 凉月-文
 */

public class DynamicDataSourceContextHolder {

    //存放当前线程使用的数据源类型信息
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    //存放数据源id
    public static List<String> dataSourceIds = new ArrayList<String>();

    //设置数据源：提供给AOP去设置当前的线程的数据源的信息
    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    //获取数据源: 提供给AbstractRoutingDataSource的实现类，通过key选择数据源
    public static String getDataSourceType() {
        return contextHolder.get();
    }

    //清除数据源: 使用默认的数据源
    public static void clearDataSourceType() {
        contextHolder.remove();
    }

    //判断当前数据源是否存在
    public static boolean isContainsDataSource(String dataSourceId) {
        return dataSourceIds.contains(dataSourceId);
    }
}