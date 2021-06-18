package tk.fulsun.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author fsun7
 * @description: 数据源的配置信息
 * @date 6/11/2021 3:20 PM
 */
@Configuration
public class DataSourceConfiguration {
  @Bean
  @Primary
  @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.primary")
  public DataSource primaryDataSource() {
    return new AtomikosDataSourceBean();
  }

  @Bean
  @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.secondary")
  public DataSource secondaryDataSource() {
    return new AtomikosDataSourceBean();
  }

  @Bean
  public JdbcTemplate primaryJdbcTemplate(
      @Qualifier("primaryDataSource") DataSource primaryDataSource) {
    return new JdbcTemplate(primaryDataSource);
  }

  @Bean
  public JdbcTemplate secondaryJdbcTemplate(
      @Qualifier("secondaryDataSource") DataSource secondaryDataSource) {
    return new JdbcTemplate(secondaryDataSource);
  }
}
