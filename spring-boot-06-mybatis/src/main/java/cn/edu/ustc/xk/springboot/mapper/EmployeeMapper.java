package cn.edu.ustc.xk.springboot.mapper;

import cn.edu.ustc.xk.springboot.bean.Employee;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-16
 * Time: 20:17
 */
public interface EmployeeMapper {

    public Employee selectEmployeeById(Integer id);

    public void inserEmployee(Employee employee);

}
