package cn.edu.ustc.xk.springboot.mapper;

import cn.edu.ustc.xk.springboot.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-18
 * Time: 20:54
 */
@Mapper
public interface DepartmentMapper {

    @Select("SELECT * FROM department WHERE id = #{id}")
    public Department getDeptById(Integer id);

}
