package cn.edu.ustc.xk.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 测试异步任务、定时任务和邮件任务
 *
 * 邮件的通信过程：
 *    zhangsan@qq.com               lisi@163.com
 *         |                           |
 *        |                           |
 *     qq邮箱服务器    ——>     163邮箱服务器
 *     所以我们得指定发件人的用户名、密码和他所在的邮箱服务器的地址
 */
@SpringBootApplication
@EnableAsync //开启异步执行
@EnableScheduling // 开启定时任务
public class SpringBoot10TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot10TaskApplication.class, args);
    }

}

