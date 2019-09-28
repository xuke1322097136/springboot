package cn.edu.ustc.xk.springboot08rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 我们下载的时候是下载的带管理界面的，即：rabbit:3-manangment版本（后面的是tag）
 * docker安装rabbitmq的指令：
 * docker run -d -p 15672:15672 -p 5672:5672 --name rabbitmq ID
 *  前面的-p指定的是我们管理界面访问的时候web页面的端口，进行rabbitmq的web通信的端口，即我们在地址栏输入的端口
 *  后面的-p指定的是主机的5672端口映射到docker容器的5672端口，也就是说客户端和rabbitmq通信的端口
 *
 * rabbitmq的自动配置类:RabbitAutoConfiguration，不是RabbitMQAutoConfiguration
 * 1. RabbitAutoConfiguration里面配置了自动连接工厂，帮助我们连接RabbitMQ
 * 2. RabbitProperties里面封装了RabbitMQ的所有配置
 * 3. RabbitTemplate来负责给RabbitMQ发送和接收消息（可以在测试类里面测试）
 * 4. AmqpAdmin是RabbitMQ系统管理功能组件，帮我们创建和删除队列，交换器和绑定规则等
 * 5. @EnableRabbit+@RabbitListener来监听消息队列当中的内容
 */
@SpringBootApplication
@EnableRabbit // 开启基于注解的RabbitMQ
public class SpringBoot08RabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot08RabbitmqApplication.class, args);
    }

}

