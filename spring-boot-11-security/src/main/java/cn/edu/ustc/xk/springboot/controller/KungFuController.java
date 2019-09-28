package cn.edu.ustc.xk.springboot.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-23
 * Time: 21:53
 */
@Controller
public class KungFuController {
    private final String PREFIX = "pages/";
    /**
     * 欢迎页
     * @return
     */
    @GetMapping("/")
    public String index() {

        String s = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        System.out.println(s);
        return "welcome";
    }

    /**
     * 登陆页
     * @return
     */
    @GetMapping("/userlogin")
    public String loginPage() {
        return PREFIX+"login";
    }


    /**
     * level1页面映射
     * @param path
     * @return
     */
    @GetMapping("/level1/{path}")
    public String level1(@PathVariable("path")String path) {
        return PREFIX+"level1/"+path;
    }

    /**
     * level2页面映射
     * @param path
     * @return
     */
    @GetMapping("/level2/{path}")
    public String level2(@PathVariable("path")String path) {
        return PREFIX+"level2/"+path;
    }

    /**
     * level3页面映射
     * @param path
     * @return
     */
    @GetMapping("/level3/{path}")
    public String level3(@PathVariable("path")String path) {
        return PREFIX+"level3/"+path;
    }
}
