package cn.edu.ustc.xk.ticket.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-26
 * Time: 19:07
 */
@Component //加入到容器中去
@Service //将服务发布出去，注意这里的service不是之前Springframework里面的@Service注解
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "<厉害了，我的国>";
    }
}
