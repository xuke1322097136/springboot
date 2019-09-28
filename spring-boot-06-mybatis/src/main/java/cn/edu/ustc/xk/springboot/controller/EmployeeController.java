package cn.edu.ustc.xk.springboot.controller;

import cn.edu.ustc.xk.springboot.bean.Employee;
import cn.edu.ustc.xk.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-16
 * Time: 20:31
 */
@RestController
public class EmployeeController {

    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
        return employeeMapper.selectEmployeeById(id);
    }
}
