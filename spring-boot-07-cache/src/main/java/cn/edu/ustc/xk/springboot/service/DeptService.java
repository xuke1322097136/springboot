package cn.edu.ustc.xk.springboot.service;

import cn.edu.ustc.xk.springboot.bean.Department;
import cn.edu.ustc.xk.springboot.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-21
 * Time: 13:37
 */
@CacheConfig(cacheNames = "dept", cacheManager = "cacheManager")
@Service
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired // 在这为了演示方法体里如何使用缓存，而不用注解的方式，所以在这我们先注入缓存管理器
    RedisCacheManager cacheManager;

    /**
     * 最终的效果是：缓存的数据能存入Redis，但是从Redis中查询到的数据不能反序列化回来，
     * 因为我们在配置类（MyRedisConfig）中只指定了Employee的序列化和反序列化操作,所以我么可以修改一下，换成泛型形式的缓存管理器
     * @param id
     * @return
     */
   /* @Cacheable(cacheNames = "dept")
    public Department getDeptById(Integer id){
        System.out.println("查找到了" + id + "号部门");
        Department dept = departmentMapper.getDeptById(id);
        return dept;
    }*/

    // 咱们也可以使用编码的方式来使用缓存,直接注入缓存管理器，操作缓存进行CRUD即可
    public Department getDeptById(Integer id){
        System.out.println("查找到了" + id + "号部门");
        Department dept = departmentMapper.getDeptById(id);

        // 获取某个缓存
        Cache cache = cacheManager.getCache("dept");// 第一次没有的话，会负责创建
        cache.put("dept:01", dept);
        return dept;
    }
}
