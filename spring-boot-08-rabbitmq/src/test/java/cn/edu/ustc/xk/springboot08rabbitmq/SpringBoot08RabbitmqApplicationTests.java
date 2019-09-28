package cn.edu.ustc.xk.springboot08rabbitmq;

import cn.edu.ustc.xk.springboot08rabbitmq.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot08RabbitmqApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    /**
     * 1. 单播（点对点传播）
     */
    @Test
    public void contextLoads() {
// message需要自己构造一个含有消息体内容和消息头的消息
//        rabbitTemplate.send(exchange, routingKey, message);

//        object默认被当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq即可
//        rabbitTemplate.convertAndSend(exchange, routingKey,object);

        Map<String, Object> map = new HashMap<>();
        map.put("msg", "这是第一个消息");
        map.put("data", Arrays.asList("hello", 1234, true));

        // 对象被默认序列化之后发送过去了,
        // 之所以会造成乱码的形式，就是因为RabbitTemplate里面的MessageConverter的实现类SimpleMessageConverter帮我们实现的序列化
        // 具体我们实现的MessageConverter在MyAMQPConfig中
//        rabbitTemplate.convertAndSend("exchange.direct", "xuke", map);
        rabbitTemplate.convertAndSend("exchange.direct", "xuke", new Book("西游记", "吴承恩"));
    }

    // 接受数据.
    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("xuke");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    /**
     * 广播,第二个参数不能空着，虽然是广播也得带上一个参数
     */
    @Test
    public void sendMesg(){
        rabbitTemplate.convertAndSend("exchange.fanout","",  new Book("红楼梦", "曹雪芹"));
    }

    @Test
    public void create(){
        Exchange exchange = new DirectExchange("111");//为了测试方便，在这只取名字，其他的也是可以制定了
        // declareXXX表示创建交换器，队列,绑定规则等
        amqpAdmin.declareExchange(exchange);
        System.out.println("创建完成了");
    }

}

