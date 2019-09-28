package cn.edu.ustc.xk.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *    1. 和provider-ticket一样，引入相关依赖(dubbo和zkclient)；
 *    2. 配置dubbo的注册中心地址
 *    3. 引用服务
 */
@SpringBootApplication
public class ConsumerUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerUserApplication.class, args);
    }

}

