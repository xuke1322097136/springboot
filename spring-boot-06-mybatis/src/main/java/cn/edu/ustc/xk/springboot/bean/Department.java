package cn.edu.ustc.xk.springboot.bean;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-16
 * Time: 16:38
 * alt+insert导入setter/getter方法
 */
public class Department {
    private Integer id;
    private String departmentName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
