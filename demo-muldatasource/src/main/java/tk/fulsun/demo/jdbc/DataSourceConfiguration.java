package tk.fulsun.demo.jdbc;

import org.springframework.context.annotation.Configuration;

/**
 * @author fulsun
 * @description: 数据源的配置信息
 * @date 6/11/2021 3:20 PM
 */
//@Configuration
public class DataSourceConfiguration {
  // @Bean
  // @ConfigurationProperties(prefix = "spring.datasource.primary")
  // public DataSource primaryDataSource() {
  //   return DataSourceBuilder.create().build();
  // }
  //
  // @Bean
  // @ConfigurationProperties(prefix = "spring.datasource.secondary")
  // public DataSource secondaryDataSource() {
  //   return DataSourceBuilder.create().build();
  // }
  //
  // @Bean
  // public JdbcTemplate primaryJdbcTemplate(
  //     @Qualifier("primaryDataSource") DataSource primaryDataSource) {
  //   return new JdbcTemplate(primaryDataSource);
  // }
  //
  // @Bean
  // public JdbcTemplate secondaryJdbcTemplate(
  //     @Qualifier("secondaryDataSource") DataSource secondaryDataSource) {
  //   return new JdbcTemplate(secondaryDataSource);
  // }
}
