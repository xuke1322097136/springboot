package cn.edu.ustc.xk.bean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 徐科 on 2018/11/13.
 * <p>
 * 将配置文件的每一个属性的值映射到该组件中来
 *
 * @ConfigurationProperties： 从配置文件中获取值，告诉 Spring Boot将该类中的所有属性和配置文件(yml文件)中相关的配置进行绑定
 * prefix = "person"：表示从配置文件中获取哪个属性下面的值，比如有我们配置的person，还有server等等属性，表示获取哪个属性值
 *
 * @Component 表示这个组件是容器中的组件，只有该类是容器中的组建才能提供@ConfigurationProperties功能
 *
 * @ConfigurationProperties(prefix = "person")默认从全局配置文件中获取值；
 *
 * @PropertySource 指定从哪个配置文件中读取person信息
 */

@PropertySource(value = {"classpath:person.properties"})
@Component
@ConfigurationProperties(prefix = "person")
public class Person
{
    /**
     * 用@Value()来获取值就类似于之前Spring中的
     * <bean class = "Person">
     *    <property name = "last-name" value="字面量/${key}从环境变量、配置文件中获取值/#{SpEL}"><property/>
     * <bean/>
     */
     // @Value("${person.last-name}")
    private String lastName;
    // @Value("#{2*12}")
    private int age;
    private boolean boss;
    private Date birth;

    private Map<String, Object> maps;
    private List<Object> lists;
    private Dog dog;

    @Override
    public String toString()
    {
        return "Person{" + "lastName='" + lastName + '\'' + ", age=" + age + ", boss=" + boss + ", birth=" + birth + ", maps=" + maps + ", lists=" + lists + ", dog=" + dog + '}';
    }

    public Map<String, Object> getMaps()
    {
        return maps;
    }

    public void setMaps(Map<String, Object> maps)
    {
        this.maps = maps;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public boolean isBoss()
    {
        return boss;
    }

    public void setBoss(boolean boss)
    {
        this.boss = boss;
    }

    public Date getBirth()
    {
        return birth;
    }

    public void setBirth(Date birth)
    {
        this.birth = birth;
    }

    public List<Object> getLists()
    {
        return lists;
    }

    public void setLists(List<Object> lists)
    {
        this.lists = lists;
    }

    public Dog getDog()
    {
        return dog;
    }

    public void setDog(Dog dog)
    {
        this.dog = dog;
    }
}