package cn.edu.ustc.xk;

import cn.edu.ustc.xk.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SpringBoot单元测试，在这使用Spring自带的单元测试@RunWith来取代Junit单元测试
 *
 * 可以在测试期间很方便的类似编码一样进行自动注入等容器的功能
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot02ymlConfigTests {

	// 把person注入进来
	@Autowired
	Person person;

	// 注入IOC容器进来
	@Autowired
	ApplicationContext ioc;

	// 需要在配置类（@SpringBootApplication标注的类）上加上注解@ImportService才能将bean注入到容器中来
	// 在Spring Boot中不推荐用@ImportService注解这种写法
	// 测试@Bean注解的时候我们得隐掉配置类上的@ImportService
    @Test
	public void testHelloService(){
    	boolean b = ioc.containsBean("helloService02");
		System.out.println(b);
	}

	@Test
	public void contextLoads() {
		System.out.println(person);
	}

}
