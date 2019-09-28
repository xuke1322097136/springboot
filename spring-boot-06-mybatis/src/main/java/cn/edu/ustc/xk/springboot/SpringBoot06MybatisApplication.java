package cn.edu.ustc.xk.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 这个示例中我们使用注解版和配置版来实现：
// 其中注解版使用的是department这个表和对应的javabean(Department)：在这mybatis的配置我们使用的是MybatisConfig这个类
// 配置版使用的事employee这个表和对应的javabean(Employee)：在这mybatis的配置我们使用的是resources/mybatis/mybatis-config.xml这个配置文件
@MapperScan(value = "cn.edu.ustc.xk.springboot.mapper")
@SpringBootApplication
public class SpringBoot06MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot06MybatisApplication.class, args);
    }

}

