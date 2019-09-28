package com.example.demo.config;

import com.example.demo.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by 徐科 on 2018/11/20.
 */
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter
{
    @Bean
    public LocaleResolver localeResolver()
    {
        return new MyLocaleResolver();
    }
}
