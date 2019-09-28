package cn.edu.ustc.xk.user;

import cn.edu.ustc.xk.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerUserApplicationTests {


    @Autowired
    UserService userService;

    @Test
    public void contextLoads() {
        userService.hello();// 这里我们调用了UserService里面的hello方法，而hello里面远程调用了ticketService里面的getTicket()方法
    }

}

