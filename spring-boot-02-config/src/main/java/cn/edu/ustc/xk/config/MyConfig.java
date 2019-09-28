package cn.edu.ustc.xk.config;

import cn.edu.ustc.xk.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 徐科 on 2018/11/13.
 */

//专门写一个配置类来测试SpringBoot给容器中添加组件的方法
// 结论：SpringBoot使用的是全注解的方式来完成的

 //
/**
 * @Configuration：指明当前类是一个配置类；就是来替代之前的Spring配置文件,在这替换的是service.xml
 *
 * 在配置文件中用<bean><bean/>标签添加组件
 *
 * 其实可以看到在这原来Spring使用的是bean标签，在这使用的是@Bean注解，而之前的value标签对应的是@Value注解，这其实都是一一对应的
 */

@Configuration
public class MyConfig{

  //将方法的返回值添加到容器中；
  // 注意：容器中这个组件默认的id（其实对应的就是Spring的配置文件service.xml中的bean的id值）就是方法名
 // 如果我们这的方法名是helloService02，则IOC容器中的bean的id就是helloService02，是跟着我们的方法名走的
  @Bean
  public HelloService helloService02()
  {
    return new HelloService();
  }
 }
