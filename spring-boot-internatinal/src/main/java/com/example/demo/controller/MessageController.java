package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 徐科 on 2018/11/20.
 */
@Controller
public class MessageController
{
    @RequestMapping("/message")
    public String hello()
    {
        return "message";
    }
}
