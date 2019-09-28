package cn.edu.ustc.xk.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    // 配置Druid的监控：1) 配置一个管理后台的Servlet; 2)配置一个监控的filter
    //    1. 配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // 一般情况下，我们会在这配置一些初始化参数(StatViewServlet类以及它的父类ResourceServlet里面的一些参数)
        Map<String, String> initParms = new HashMap<>();
        initParms.put("loginUsername", "admin");
        initParms.put("loginPassword", "123");
        initParms.put("allow", "");// 在这默认就是允许所有访问
        initParms.put("deny", "192.168.15.21");

        bean.setInitParameters(initParms);
        return bean;
    }

//    2. 配置一个监控的filter
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String, String> initParms = new HashMap<>();
        initParms.put("exclusions", "*.js, *.css");

        bean.setInitParameters(initParms);
        bean.setUrlPatterns(Arrays.asList("/*"));

        return bean;
    }
}
