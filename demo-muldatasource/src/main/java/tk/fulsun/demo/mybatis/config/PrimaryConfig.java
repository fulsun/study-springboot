package tk.fulsun.demo.mybatis.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fulsun
 * @description: MyBatis配置
 * @date 6/11/2021 4:50 PM
 */
@Configuration
@MapperScan(
    basePackages = "tk.fulsun.demo.mybatis.mapper.p",
    sqlSessionFactoryRef = "sqlSessionFactoryPrimary",
    sqlSessionTemplateRef = "sqlSessionTemplatePrimary")
public class PrimaryConfig {
  private DataSource primaryDataSource;

  public PrimaryConfig(@Qualifier("primaryDataSource") DataSource primaryDataSource) {
    this.primaryDataSource = primaryDataSource;
  }

  @Bean
  public SqlSessionFactory sqlSessionFactoryPrimary() throws Exception {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(primaryDataSource);
    return bean.getObject();
  }

  @Bean
  public SqlSessionTemplate sqlSessionTemplatePrimary() throws Exception {
    return new SqlSessionTemplate(sqlSessionFactoryPrimary());
  }
}
