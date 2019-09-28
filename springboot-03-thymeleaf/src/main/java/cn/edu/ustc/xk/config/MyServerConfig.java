package cn.edu.ustc.xk.config;

import cn.edu.ustc.xk.filter.MyFilter;
import cn.edu.ustc.xk.listener.MyListener;
import cn.edu.ustc.xk.servlet.MyServlet;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextListener;
import java.util.Arrays;

/**
 * Created by 徐科 on 2018/11/29.
 *
 *  EmbeddedServletContainerCustomizer在springboot2.0中已经被替代了
 * springboot1.x的相关类如下：
 * org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer
 * org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer
 * org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer
 * org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory
 * springboot2.x的相关类如下：
 * org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory
 * org.springframework.boot.web.server.WebServerFactoryCustomizer
 * org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer
 * org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
 */
@Configuration
public class MyServerConfig
{
    // 注册三大组件：servlet;filter;listener
    @Bean //一定得记得加入到容器中去
//    SpringBoot帮我们启动SpringMVC的时候，自动注册了SpringMVC的前端控制器：DIspatcherServlet；
//    源码可以在DispatcherServletAutoConfiguration中看到：
    public ServletRegistrationBean servletRegistrationBean()
    {
        // 使用我们自定义的MyServlet处理/myServlet请求
        ServletRegistrationBean<MyServlet> myServlet = new ServletRegistrationBean<>(new MyServlet(), "/myServlet");
        return myServlet;
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean()
    {
        FilterRegistrationBean<MyFilter> myFilter = new FilterRegistrationBean<>();
        myFilter.setFilter(new MyFilter());
        // 拦截"/helloMyFilter", "/myFilter"等请求
        myFilter.setUrlPatterns(Arrays.asList("/helloMyFilter", "/myFilter"));
        return myFilter;
    }
    @Bean
     public ServletListenerRegistrationBean listenerRegistrationBean()
     {
         // 注意我们自己测试contextDestroyed()方法的时候，不要直接关掉红色按钮，我们模拟的时候点的是左下角run-> exit按钮
         ServletListenerRegistrationBean<MyListener> myListener = new ServletListenerRegistrationBean<>(new MyListener());
         return myListener;
     }
    // 配置嵌入式的Servlet容器
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer()
    {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>()
        {
            @Override
            public void customize(ConfigurableWebServerFactory factory)
            {
                // 修改tomcat的端口号
                factory.setPort(8081);
            }
        };
    }
}
