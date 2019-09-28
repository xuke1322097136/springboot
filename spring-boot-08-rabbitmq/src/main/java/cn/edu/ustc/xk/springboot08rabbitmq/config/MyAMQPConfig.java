package cn.edu.ustc.xk.springboot08rabbitmq.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-21
 * Time: 15:51
 */
@Configuration
public class MyAMQPConfig {

    // ctrl+h可以查看MessageConverter的实现类，可以找到跟Json相关的MessageConverter
    @Bean
    public MessageConverter messageConverter(){
       return new Jackson2JsonMessageConverter();
    }
}
