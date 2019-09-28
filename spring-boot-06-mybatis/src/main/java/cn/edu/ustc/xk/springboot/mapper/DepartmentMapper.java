package cn.edu.ustc.xk.springboot.mapper;

import cn.edu.ustc.xk.springboot.bean.Department;
import org.apache.ibatis.annotations.*;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-16
 * Time: 17:03
 */
// @Mapper
// 在这如果xxxMapper比较多的时候，我们需要在每一个xxxMapper上面加上mapper注解，
// 所以我们可以在MybatisConfig上面或者主配置类上加上包名进行批量扫描
public interface DepartmentMapper {

    @Select("select * from department where id = #{id}")
    public Department selectDepartmentById(Integer id);

    // 在这使用自增主键，自增主键对应的列是id这一列
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into department(department_name) values(#{departmentName})")
    public int insertDepartment(Department department);

    @Delete("delete from department where id = #{id}")
    public int deleteDepartment(Integer id);

    @Update("update department set department_name=#{departmentName} where id=#{id}")
    public int updateDepartment(Department department);
}
