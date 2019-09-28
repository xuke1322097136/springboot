package cn.edu.ustc.xk.springboot.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-16
 * Time: 17:00
 */

@Configuration
public class MybatisConfig {

    // 在这里面可以完成自定义的mybatis配置
    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                // 开启驼峰命名法，即javabean里面的departmentName和数据库中的department_name能完成映射
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
