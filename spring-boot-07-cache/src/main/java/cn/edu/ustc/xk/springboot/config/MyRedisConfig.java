package cn.edu.ustc.xk.springboot.config;

import cn.edu.ustc.xk.springboot.bean.Employee;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.net.UnknownHostException;
import java.time.Duration;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-21
 * Time: 12:28
 */
@Configuration
public class MyRedisConfig {
    //    模仿RedisAutoConfiguration里面的写法自己来定义序列化器
    @Bean
    public RedisTemplate<Object, Employee> employeeRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Employee> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // 设置我们定义的序列化器，里面的参数是需要传一个RedisSerializer，可以ctrl+H查看RedisSerializer的实现类
        RedisSerializer<Employee> redisSerializer = new Jackson2JsonRedisSerializer(Employee.class);
        template.setDefaultSerializer(redisSerializer);
        return template;
    }

    /**
     * 在springboot1.0 中如果向自定义我们直接创建CacheManager 然后传入RedisTemple模板对象，就可以了
     * 即：
     *
     * @Bean public RedisCacheManager employeeCacheManager(RedisTemplate<Object, Employee> empRedisTemplate){
     * RedisCacheManager cacheManager = new RedisCacheManager(empRedisTemplate);
     *
     * //key多了一个前缀使用前缀，默认会将CacheName作为key的前缀
     * cacheManager.setUsePrefix(true);
     * return cacheManager;
     * }
     * 在springboot 2.0中换了一种实现方式了，有点差别，看下面的实现
     */

    // 我们配置了RedisCacheManager之后，容器中的CacheManager就不起作用了
    @Bean
    public RedisCacheManager employeeCacheManager(RedisConnectionFactory factory) {
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//            .entryTtl(Duration.ofDays(1))// 设置缓存有效期一小时
             .disableCachingNullValues()
                //只序列化Employee
          .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new Jackson2JsonRedisSerializer(Employee.class)));
        return RedisCacheManager
                .builder(factory)
                .cacheDefaults(cacheConfiguration)
                .build();
    }

    // 适用于序列化所有的对象，注意：当我们定义了多个缓存管理器的时候，需要指定一个为Primary的，即我们要设定一个默认的
    // 所以一般情况下我们就下面的这种就够了，也就不需要指定Primary的缓存管理器了。
    @Primary
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
           .entryTtl(Duration.ofDays(1))// 设置缓存有效期一小时
                .disableCachingNullValues()

                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        return RedisCacheManager
                .builder(factory)
                .cacheDefaults(cacheConfiguration)
                .build();
    }
}
