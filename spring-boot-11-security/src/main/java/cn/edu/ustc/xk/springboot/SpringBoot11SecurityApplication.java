package cn.edu.ustc.xk.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 市面上有两个著名的安全框架：shiro和spring security,咱们在这只介绍后者
 *
 * 当我们还没有引入安全模块的时候，访问localhost:8080的时候，报500的错，Exception parsing document，
 * 这表明我们的thymeleaf模板引擎版本太低，需要修改一下下面两个配置：（不过我们测试的时候这是没问题）
 * 点进pom.xml -> spring-boot-starter-parent -> 点进来找到spring-boot-dependencies -> ctrl+f找到下面两个配置：
 * <thymeleaf.version>3.0.11.RELEASE</thymeleaf.version>
 * <thymeleaf-layout-dialect.version>2.3.0</thymeleaf-layout-dialect.version>
 * 如果需要配置的话，是去maven 仓库里面找到这两个参数：3版本的thymeleaf必须搭配2版本以上的layout
 *
 * 安全的两个概念：认证和授权（也可以说是访问控制），认证的意思是证明你是这个人，而授权的意思是你能干啥
 *
 * 第一步;引入Spring Security的相关模块
 * 第二步：编写Spring Security的相关配置类:
 * @EnableWebSecurity
 * public class MySecurityConfig extends WebSecurityConfigurerAdapter {}
 */
@SpringBootApplication
public class SpringBoot11SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot11SecurityApplication.class, args);
    }

}

