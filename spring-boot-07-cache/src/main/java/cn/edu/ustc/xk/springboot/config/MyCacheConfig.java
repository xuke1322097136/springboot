package cn.edu.ustc.xk.springboot.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;


/**
 * Created by xuke
 * Description:
 * Date: 2018-12-19
 * Time: 13:33
 */
@Configuration
public class MyCacheConfig {

    // KeyGenerator别导错了，是在cache包里面的这个
    @Bean("myKeyGenerator")//在这指定一个名字，默认的话是keyGenerator即id就是我们的方法名
    public KeyGenerator keyGenerator(){
       return new KeyGenerator() {
           @Override
           public Object generate(Object target, Method method, Object... params) {
//               为了拼接成  方法名[id]  的形式，即getEmp[1]这种形式
               return method.getName()+ Arrays.asList(params).toString();
           }
       };
    }
}
