package cn.edu.ustc.xk.springboot.entity;



import javax.persistence.*;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-16
 * Time: 21:22
 */
// 使用JPA注解配置映射关系
@Entity // 告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "tb_user")//@Table用来指定和哪个数据表对应，如果没有的话会帮我们创建好。如果不指定name属性的话，默认的就是javabean第一个字母小写（在这是user）
public class User {

    @Id // 表明这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)// 表明该属性对应的数据表中的是自增主键
    private Integer id;

    @Column(name = "last_name", length = 50) // 这是和数据库表对应的一列
    private String lastName;

    @Column // 省略不写的话，列名就是属性名
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
