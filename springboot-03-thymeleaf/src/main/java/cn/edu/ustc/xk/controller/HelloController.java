package cn.edu.ustc.xk.controller;

import cn.edu.ustc.xk.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 徐科 on 2018/11/28.
 */
@Controller
public class HelloController
{

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam("user") String user)
    {
        // 发送请求的格式为：http://localhost:8080/hello?user=aaa
        if(user.equals("aaa"))
        {
            throw new UserNotExistException();
        }
        return "hello world";
    }
}
