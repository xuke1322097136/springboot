package cn.edu.ustc.xk.springboot.controller;

import cn.edu.ustc.xk.springboot.bean.Employee;
import cn.edu.ustc.xk.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-18
 * Time: 21:25
 */
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee searchEmployee(@PathVariable("id") Integer id){
        Employee employee = employeeService.getEmp(id);
        return employee;
    }

    @GetMapping("/emp")
    public Employee update(Employee employee){
        Employee e = employeeService.updateEmp(employee);
        return e;
    }
    @GetMapping("/delEmp")
    public String deleteEmployee(Integer id){
        employeeService.deleteEmp(id);
        return "success";
    }

    @GetMapping("/emp/lastname/{lastName}")
    public Employee searchEmployeeByLastName(@PathVariable("lastName") String lastName){
        Employee employee = employeeService.getEmpByLastName(lastName);
        return employee;
    }
}
