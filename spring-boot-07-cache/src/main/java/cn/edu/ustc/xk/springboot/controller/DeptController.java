package cn.edu.ustc.xk.springboot.controller;

import cn.edu.ustc.xk.springboot.bean.Department;
import cn.edu.ustc.xk.springboot.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-21
 * Time: 13:42
 */
@RestController
public class DeptController {

    @Autowired
    DeptService deptService;

    @GetMapping("/dept/{id}")
    public Department getDept(@PathVariable("id") Integer id){
        Department department = deptService.getDeptById(id);
        return department;
    }
}
