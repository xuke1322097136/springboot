package cn.edu.ustc.xk.springboot.bean;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * Created by xuke
 * Description:
 * Date: 2019-01-13
 * Time: 18:01
 */
@Data
@Component
public class Person {
    private String username;
    private String password;
    private Integer id;
    private String key;
}
