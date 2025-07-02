package pers.sfl.actuator.indicator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @author : fulsun
 * @description : 自定义健康检查指标
 */
@Component(value = "CPUHealth")
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        double random = Math.random();
        // 这里用随机数模拟健康检查的结果
        if (random > 0.5) {
            return Health.status("FATAL").withDetail("error code", "CPU转疯了").build();
        } else {
            return Health.up().withDetail("success code", "CPU检查一切正常").build();
        }

    }
}
