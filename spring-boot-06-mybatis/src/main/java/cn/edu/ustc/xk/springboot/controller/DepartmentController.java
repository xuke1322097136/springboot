package cn.edu.ustc.xk.springboot.controller;

import cn.edu.ustc.xk.springboot.bean.Department;
import cn.edu.ustc.xk.springboot.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-16
 * Time: 17:14
 */
@RestController
public class DepartmentController {

    @Autowired
    DepartmentMapper departmentMapper;

    @GetMapping("/dept/{id}") // id以请求参数（或者说占位符）的形式传过来
    public Department getDepartment(@PathVariable("id") Integer id) {
        return departmentMapper.selectDepartmentById(id);
    }

    @GetMapping("/dept")
    public Department insertDept(Department department) {
        departmentMapper.insertDepartment(department);
        return department;
    }

}
