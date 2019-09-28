package cn.edu.ustc.xk.springboot;

import cn.edu.ustc.xk.springboot.bean.Employee;
import cn.edu.ustc.xk.springboot.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot07CacheApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired // k-v都是对象
    RedisTemplate redisTemplate;

    @Autowired // 专门提取出来了操作字符串的
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<Object, Employee> employeeRedisTemplate;

    /**
     * Redis五大常见数据类型：
     * String(字符串)、List(列表)、Set(集合)、Hash(散列)、ZSet(有序集合)
     * stringRedisTemplate.opsForValue()【操作字符串的】
     * stringRedisTemplate.opsForList()【操作列表的】
     * stringRedisTemplate.opsForSet()【操作集合的】
     * stringRedisTemplate.opsForHash()【操作散列的】
     * stringRedisTemplate.opsForZSet()【操作有序集合的】
     */
    @Test
    public void Test01(){
        // 在redis中保存一个键值对
      // stringRedisTemplate.opsForValue().append("msg", "hello");
      // 从redis中获取到msg对应的值
        String s = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(s);

    }

    @Test // 测试保存对象
    public void Test02(){
        Employee employee = employeeMapper.getEmployeeById(1);
        // 保存对象的时候，默认使用的是Jdk序列化机制来序列化，序列化之后的数据会保存在Redis中
       //  redisTemplate.opsForValue().set("emp-01", employee);// 这里Employee不实现Serializable接口的话，会报错

        /**
         *   1. 如何将数据以json的方式保存：
         *        方法一：使用Json转换工具将对象转换成json数据；
         *        方法二：修改redisTemplate默认的序列化规则，自定义序列化规则。
         *        打开RedisTemple里面的源码可以找到默认的序列化器：
         *        if (this.defaultSerializer == null) {
         *             this.defaultSerializer = new JdkSerializationRedisSerializer(this.classLoader != null ? this.classLoader : this.getClass().getClassLoader());
         *         }
         */
       employeeRedisTemplate.opsForValue().set("emp-01", employee);// 这里Employee无需Serializable接口也不会报错，而且最终的结果是json数据

    }

    @Test
    public void contextLoads() {
        Employee employee = employeeMapper.getEmployeeById(1);
        System.out.println(employee);
    }

}

