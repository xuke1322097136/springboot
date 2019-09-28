package cn.edu.ustc.xk.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 在这可以看到默认的数据源不再是org.apache.tomcat.jdbc.pool.DataSource了，而是com.zaxxer.hikari.HikariDataSource
//数据源的相关配置都在DataSourceProperties里面

@SpringBootApplication
public class SpringBoot06JdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot06JdbcApplication.class, args);
    }
}
