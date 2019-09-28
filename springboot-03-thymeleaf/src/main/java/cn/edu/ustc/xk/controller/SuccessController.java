package cn.edu.ustc.xk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by 徐科 on 2018/11/17.
 */
@Controller
public class SuccessController
{
    @RequestMapping("/success")
    public String success(Map<String, Object> map)
    {
        map.put("hello", "<h1>你好</h1>");//把hello放入到request域中
        map.put("users", Arrays.asList("zhangsan", "lisi", "wangwu"));

        //自动渲染放在classpath:/templates/success.html的页面
        return "success";
    }
}
