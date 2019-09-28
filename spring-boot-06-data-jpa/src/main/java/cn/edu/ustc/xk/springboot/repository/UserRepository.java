package cn.edu.ustc.xk.springboot.repository;

import cn.edu.ustc.xk.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-16
 * Time: 21:50
 */
// JpaRepository继承的是PagingAndSortingRepository和CRUDRepository，前者是用于分页使用的，后者是进行CRUD的
// 在这继承JpaRepository来完成对数据库的操作，不需要我们任何实现
// 在这泛型的第一个参数是你要操作的实体类，第二个参数是实体类里面主键的类型
public interface UserRepository extends JpaRepository<User, Integer> {
}
