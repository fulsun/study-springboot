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
 * @author fsun7
 * @description: MyBatis配置
 * @date 6/11/2021 4:50 PM
 */
@Configuration
@MapperScan(
    basePackages = "tk.fulsun.demo.mybatis.mapper.s",
    sqlSessionFactoryRef = "sqlSessionFactorySecondary",
    sqlSessionTemplateRef = "sqlSessionTemplateSecondary")
public class SecondaryConfig {
  private DataSource secondaryDataSource;

  public SecondaryConfig(@Qualifier("secondaryDataSource") DataSource secondaryDataSource) {
    this.secondaryDataSource = secondaryDataSource;
  }

  @Bean
  public SqlSessionFactory sqlSessionFactorySecondary() throws Exception {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(secondaryDataSource);
    return bean.getObject();
  }

  @Bean
  public SqlSessionTemplate sqlSessionTemplateSecondary() throws Exception {
    return new SqlSessionTemplate(sqlSessionFactorySecondary());
  }
}
