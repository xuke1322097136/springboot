package cn.edu.ustc.xk.springboot.mapper;

import cn.edu.ustc.xk.springboot.bean.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-18
 * Time: 20:54
 */
@Mapper
public interface EmployeeMapper {

    @Select("SELECT * FROM employee WHERE id = #{id}")
     public Employee getEmployeeById(Integer id);

    @Update("UPDATE employee SET lastName=#{lastName}, email=#{email}, gender=#{gender}, d_id=#{dId} WHERE id=#{id}")
    public void updateEmployee(Employee employee);

    @Insert("INSERT INTO employee(lastName, email, gender, d_id) VALUES (#{lastName}, #{email}, #{gender}, #{dId})")
    public void addEmployee(Employee employee);

    @Delete("DELETE FROM employee WHERE id = #{id}")
    public void deleteEmployeeById(Integer id);

    @Select("SELECT * FROM employee WHERE lastName = #{lastName}")
    public Employee getEmployeeByLastName(String lastName);
}
