package tk.fulsun.demo.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * @Description TODO
 * @Date 2021/6/26
 * @Created by 凉月-文
 */
@Configuration
public class SecuritySecureConfig  extends WebSecurityConfigurerAdapter {
    private final String adminContextPath;

    public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(adminContextPath + "/");

        http.authorizeRequests()
                // 所有静态内容不做安全验证
                .antMatchers(adminContextPath + "/assets/**").permitAll()
                .antMatchers(adminContextPath + "/login").permitAll()
                // 其他请求均需要验证
                .anyRequest().authenticated()
                .and()
                // 配置登录
                .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
                // 配置登出
                .logout().logoutUrl(adminContextPath + "/logout").and()
                // 支持 HTTP，引导 Spring Boot Admin 客户端注册
                .httpBasic().and()
                .csrf()
                // 打开跨站点请求保护 Cookies
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringAntMatchers(
                        // 取消跨站点请求保护 "/instances"，方便 Admin 客户端注册
                        adminContextPath + "/instances",
                        // 取消跨站点请求保护 "/actuator/**"，可以让 Admin 监控到 Actuator 的相关接口
                        adminContextPath + "/actuator/**"
                );
    }
}