package tk.fulsun.demo.databases;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description AbstractRoutingDataSource(每执行一次数据库，动态获取DataSource)
 * @Date 2021/6/14
 * @Created by 凉月-文
 */
public class DynamicDataSource  extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
