package cn.edu.ustc.xk.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 在这使用注解版的MyBatis，并且使用@MapperScan来进行批量扫描
 * 快速体验缓存的步骤：
 *    1. 开启基于注解的缓存；(使用注解@EnableCaching来完成)
 *    2. 标注缓存注解即可。(使用注解@Cacheable, @CachePut, @CacheEvict等来完成)
 *
 *    默认使用的是ConcurrentMapCacheManager来管理的ConcurrentMapCache，将数据保存在ConcurrentMap<Object, Object>里边
 *    实际开发中我们使用的是缓存中间件：redis, memcached等（redis中文网：http://www.redis.cn/）
 *     整合redis完成缓存：Redis 是一个开源（BSD许可）的，内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件
 *        1） 使用docker安装redis，注意：官网的docker hub上下载比较慢，我们可以使用docker中国：https://www.docker-cn.com/registry-mirror
 *           下载redis我们可以使用：docker pull registry.docker-cn.com/library/redis(好像是不行)，但是个人最后成功的还是docker pull redis
 *        2）redis的默认端口是6379，咱们得把端口映射一下：docker run -d -p 6379:6379 --name redis  ce25c7293564(redis的ID值)
 *
 *        导入redis的包之后，我们就可以查看自动配置类RedisAutoConfiguration中导入了啥，我们可以在test中进行测试一下。
 *
 *        引入了redis的starter之后，容器中保存的是RedisCacheManager,它会负责帮我们创建RedisCache来作为缓存组件，
 *        而从RedisCacheConfiguration的源码可以看到，操作k-v的时候，springboot 1.0中它用的是RedisTemple<Object, Object>来操作redis数据的
 *        而RedisTemple<Object, Object>使用的是默认的Jdk序列化机制来序列化对象的，不满足要求，所以我们可以自定义一个CacheManager。
 *        具体在springboot 2.0中如何自定义RedisCacheManager在MyRedisConfig里面有介绍
 *        另外，CacheManagerCustomizers可以定制缓存的一些规则，但是用的不多
 */
@MapperScan("cn.edu.ustc.xk.springboot.mapper")
@EnableCaching
@SpringBootApplication
public class SpringBoot07CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot07CacheApplication.class, args);
    }

}

