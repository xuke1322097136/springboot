package cn.edu.ustc.xk.springboot08rabbitmq.service;

import cn.edu.ustc.xk.springboot08rabbitmq.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-21
 * Time: 16:04
 */
@Service
public class BookService {

    // 通过监听消息队列(在这是xuke队列)后来调用的,要让RabbitListener起作用，我们还必须得开启@EnableRabbit注解
    @RabbitListener(queues = "xuke")
    public void receive(Book book){
        System.out.println("收到消息" + book);
    }

    // 也可以通过Message来获得一些属性
    @RabbitListener(queues = "kexu.1")
    public void receive02(Message message){
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
