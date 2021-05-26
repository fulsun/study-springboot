package tk.fulsun.demo.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.util.ResourceUtils;

/**
 * @author fsun7
 * @title: GeneratorSqlmap
 * @projectName springboot-study
 * @description: Java代码方式生成数据库的 Mapper 相关映射文件
 * @date 5/26/2021 3:12 PM
 */
public class GeneratorSqlmap {

  public void generator() throws Exception {
    List<String> warnings = new ArrayList<String>();
    boolean overwrite = true;
    //指定 逆向工程配置文件
    // 注意：xml 文件在 src 目录下，路径为 src/generatorConfig.xml
    File configFile = ResourceUtils.getFile("classpath:db-generator/generatorConfig.xml");
    ConfigurationParser cp = new ConfigurationParser(warnings);
    Configuration config = cp.parseConfiguration(configFile);
    DefaultShellCallback callback = new DefaultShellCallback(overwrite);
    MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
    myBatisGenerator.generate(null);

  }

  public static void main(String[] args) throws Exception {
    try {
      GeneratorSqlmap generatorSqlmap = new GeneratorSqlmap();
      generatorSqlmap.generator();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
