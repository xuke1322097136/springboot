package cn.edu.ustc.xk.springboot.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-27
 * Time: 13:45
 */
@Component
public class MyAppHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        // 自定义的检查方法
       // return Health.up().build();// 代表健康
        return Health.down().withDetail("message", "服务异常").build();

    }
}
