package cn.edu.ustc.xk.springboot;

import cn.edu.ustc.xk.springboot.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot06MybatisApplicationTests {


    @Autowired
    Person person;

    @Test
    public void contextLoads() {
        // 测试lombok插件的使用
        person.setId(1);
        person.setKey("key1");
        person.setUsername("username1");
        person.setPassword("password1");

        System.out.println(person);
    }

}

