package cn.edu.ustc.xk.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 自定义健康检查器：在这以redis为例，引入redis的start，对redis进行健康检查
 * 1. 编写一个指示器，实现HealthIndicator接口；
 * 2. 指示器的名字必须为 XXXHealthIndicator；
 * 3. 将它加入到容器中去。
 */
@SpringBootApplication
public class SpringBoot13ActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot13ActuatorApplication.class, args);
    }

}

