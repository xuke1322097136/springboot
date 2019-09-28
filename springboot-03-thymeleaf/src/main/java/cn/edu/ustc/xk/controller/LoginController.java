package cn.edu.ustc.xk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by 徐科 on 2018/11/21.
 * 登录成功的用户，我们可以放到session中
 */
@Controller
public class LoginController
{

    @PostMapping(value = "/user/login")
    // PostMapping就相当于下面的发送post请求，value是请求地址，当然还有GetMapping,DeleteMapping,PutMapping
    // @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    // @RequestParam表示从请求参数(login.html页面中input框输入的属性名，必须得跟它一致才行)中获取username
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session)
    {
        if (!StringUtils.isEmpty(username) && "123".equals(password))
        {
            // 登录成功的话，我们记录下他的信息
            session.setAttribute("loginUser", username);
            // 在这为了防止表单重复提交，我们可以重定项到当前项目的主页请求（即main.html），然后经过模板引擎解析到dashboard.html页面
            // return "dashboard";
             return "redirect:/main.html";
        }
        else
        {
            // 登录失败
            map.put("msg", "用户名密码错误");
            return "login";
        }
    }
}
