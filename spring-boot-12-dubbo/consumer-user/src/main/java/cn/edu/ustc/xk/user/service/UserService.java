package cn.edu.ustc.xk.user.service;

import cn.edu.ustc.xk.ticket.service.TicketService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-26
 * Time: 19:15
 */
@Service // 这里表明它这是spring的一个组件
public class UserService {

    /**
     * 表示远程引用过来的，它是根据TicketService的全类名来进行匹配的，看谁在注册中心里面注册了TicketService的服务。
     *  而我们在provider-ticket里面发布(dubbo里面的@Service注解)的时候就是按照TicketService的全类名来发布的
     *  所以我们在这就能获取过来
     */

    @Reference
    TicketService ticketService;

    public void hello(){
        String ticket = ticketService.getTicket();
        System.out.println("买到票了" + ticket);
    }
}
