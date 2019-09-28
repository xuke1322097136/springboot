package cn.edu.ustc.xk.springboot.controller;

import cn.edu.ustc.xk.springboot.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-23
 * Time: 17:00
 */
@RestController
public class AsyncController {

    @Autowired
    AsyncService asyncService;

    @GetMapping("/hello")
    public String hello(){

        asyncService.hello();// 在这发出hello请求之后，hello()方法中的输出和这个返回“success”就不用等待了
        return "success";
    }
}
