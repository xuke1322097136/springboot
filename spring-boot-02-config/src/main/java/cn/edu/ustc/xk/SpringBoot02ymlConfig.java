package cn.edu.ustc.xk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
/**
 * 此工程的classpath就是指src/main/java，src/main/resources，src/main/webapp，
 * 如果在main文件夹下新建一个文件properties，
 * 那么classpath在原有三个文件的基础上又多了一个src/main/properties
 */

/**
 * @SpringBootApplication :如果哪个类被这个注解标注起来的话，表明这个类是Spring Boot的主配置类，
 *                         Spring Boot就应该运行这个类的main方法来启动SpringBoot应用
 * 上边的注解点进去之后可以看到：@SpringBootConfiguration 和 @EnableAutoConfiguration
 * @SpringBootConfiguration 表示该类是SpringBoot的一个配置类。再点进去可以看到@Configuration，
 *                          一般情况下，配置类都会带着这个注解，@Configuration点进去就是我们熟悉的@Component，
 *                          综上可以知道，配置类是容器的一个组件
 *
 * @EnableAutoConfiguration ：它的意思是告诉SpringBoot开启自动配置功能，这样自动配置才会生效。
 *                           该注解点进去又可以看到 @AutoConfigurationPackage 和 @Import(AutoConfigurationImportSelector.class)
 *
 * @AutoConfigurationPackage ：自动配置包，点进去之后又可以看到@Import(AutoConfigurationPackages.Registrar.class)
 *                             Spring底层的@Import注解表示给容器导入一个组件，由AutoConfigurationPackages.Registrar.class来导入
 *                            注意：AutoConfigurationPackages.Registrar.class导入的是主配置类（@SpringBootApplication标注的类）
 *                                 所在的包以及所有子包里面的所有的组件都会被这个类扫描到Spring容器中；
 *
 * @Import(AutoConfigurationImportSelector.class) 表示的是导入哪些组件的选择器，将所有需要导入对的组件以全限定名的方式返回，
 *                                         这些组件就会被加入到容器中。虽然和@AutoConfigurationPackage 的作用都是导入组件到容器中
 *                                         但是二者导入的组件是不一样的，@Import(AutoConfigurationImportSelector.class)导入的是
 *                                         很多的自动配置类（类似于...AutoConfiguration的类），也就是说它给容器导入的是这个场景需要
 *                                         的所有的组件（类似于cache啊，aop啊，elasticsearch啊等等，
 *                                         都在org.springframework.boot.autoconfigure中），并配置好这些组件。
 *
 *  有了自动配置类，就省去了我们手写配置注入功能组件的工作了，其实它底层调用的方法是：
 *       SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.class,classLoader)；
 *
 * Spring Boot在启动的时候从类路径下所有jar包中的META-INF/spring.factories中获取EnableAutoConfiguration指定的值，将
 * 这些值作为自动配置类导入到容器中，自动配置类就生效，帮我们进行自动配置工作；以前我们需要自己配置的东西，自动配置类都帮我们配置好了；


 *J2EE的整体整合解决方案和自动配置都在spring-boot-autoconfigure-1.5.9.RELEASE.jar；
 *
 *@ImportResource 注解的作用是将指定的spring配置文件中的bean加载到容器中来，在Spring Boot中不推荐这种写法
 *
 */
// @ImportResource(locations = {"classpath:service.xml"})
@SpringBootApplication
public class SpringBoot02ymlConfig
{

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot02ymlConfig.class, args);
	}
}
