package cn.edu.ustc.xk.springboot.controller;

import cn.edu.ustc.xk.springboot.entity.User;
import cn.edu.ustc.xk.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-16
 * Time: 22:04
 */
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable("id") Integer id){
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    // 在这由于User实体类里面已经设置了自增主键，所以我们在这无需任何操作
    @GetMapping("/user")
    public User insertUser(User user){
        User save_user = userRepository.save(user);
        return save_user;
    }
}
