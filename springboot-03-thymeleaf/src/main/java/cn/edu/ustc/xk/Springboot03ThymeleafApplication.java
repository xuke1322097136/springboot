package cn.edu.ustc.xk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 *Thymeleaf的使用，我们可以在SpringBoot的自动配置包
 * \org\springframework\boot\autoconfigure\thymeleaf\ThymeleafAutoConfiguration
 * 中有详细的解释。而我们使用的默认规则则在ThymeleafProperties这个类中，下面摘了一部分源码：
 *
 * @ConfigurationProperties(prefix = "spring.thymeleaf")
public class ThymeleafProperties {

private static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;

//默认的前缀
public static final String DEFAULT_PREFIX = "classpath:/templates/";
//默认的后缀
public static final String DEFAULT_SUFFIX = ".html";

可以看到我们只需要将HTML页面放在classpath:/templates/，templates就能自动渲染了
 */

@SpringBootApplication
public class Springboot03ThymeleafApplication
{
	public static void main(String[] args) {
		SpringApplication.run(Springboot03ThymeleafApplication.class, args);
	}
}
