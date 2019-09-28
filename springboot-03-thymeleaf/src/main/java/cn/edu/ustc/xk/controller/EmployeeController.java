package cn.edu.ustc.xk.controller;

import cn.edu.ustc.xk.dao.DepartmentDao;
import cn.edu.ustc.xk.dao.EmployeeDao;
import cn.edu.ustc.xk.entities.Department;
import cn.edu.ustc.xk.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by 徐科 on 2018/11/21.
 */
@Controller
public class EmployeeController
{
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @GetMapping("/emps")
    public String list(Model model)
    {
        Collection<Employee> employees = employeeDao.getAll();

        // 放在请求域中
        model.addAttribute("emps", employees);

        // thymeleaf默认就会拼接，classpath:/templates/xxx.html
        return "emp/list";
    }

    // 添加操作分为两个步骤：先来到员工添加页面，然后点击按钮添加，跳到显示页面
    @GetMapping("/emp")
    public String toAddPage(Model model)
    {
        //由于部门不是写死的，所以来到添加页面（add.html）之前，我们需要查询出部门信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    // 员工添加操作。当我们添加完了之后，我们还是应该来到员工列表页面
    /* SpringMVC自动将请求参数和入参对象的属性进行一一绑定:要求请求参数的名字(add.html中input中的name属性)
                                     和javabean(Employee对象中的每个属性)入参对象里面的属性进行一一对应
    */
    @PostMapping("/emp")
    public String addEmp(Employee employee)
    {
        //先输出看看我们添加的员工信息对不对
        System.out.println("添加的员工的信息" + employee);
        employeeDao.save(employee);
        // 来到员工列表页面
        /*来到的不是当前项目下的emps请求，而是模板引擎会进行解析，所以我们可以采用forward或者redirect的方式发送请求
        *return "/emps";
        * 为什么是这样的？我们可以查看源码：ThymeleafViewResolver里面的createView()方法，它会创建一个viewName,
        * viewName.startsWith(REDIRECT_URL_PREFIX)表示的就是创建一个重定向请求的RedirectView，RedirectView里面
        * 有一个专门用于渲染的方法renderMergedOutputModel()，最后一个sendRedirect()就是进行重定向
        */
        // redirect: 表示重定向到一个地址
        // forward: 表示转发到一个地址
        return "redirect:/emps";// "/"代表的就是当前项目下
    }

    // 更新操作也是两步：在列表页面点击编辑按钮来到修改页面（在这也是add.html）进行信息回显(所以需要一个model)，然后点保存回到显示页面
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model)
    {
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);
         // 在这使用了一个小技巧来区分添加页面还是修改页面：我们修改页面保存的是emp，而添加页面我们这是添加的是depts
        // 所以在页面回显(每个属性的th:value)的时候，我们使用了一个判断：${emp!=null}

        // add.html页面是一个修改功能和添加功能二合一的页面
        return "emp/add";
    }
   /*
    发送put请求修改员工数据：因为表单只支持post和get请求，需要转成我们指定的put请求
	 1、SpringMVC中配置HiddenHttpMethodFilter;（SpringBoot自动配置好的，WebMvcAutoConfiguration中OrderedHiddenHttpMethodFilter类可以看到）
     2、页面创建一个post表单(从上面可以看到，我们目前就是post表单了)
	 3、创建一个input项，name="_method";值就是我们指定的请求方式
   */
    // 员工更新操作。修改的时候一定要提交员工的id，即add.html页面中的form表单必须一块提交
    @PutMapping("/emp")
    public String updateEmp(Employee employee)
    {
        System.out.println("修改的员工数据" + employee);
        employeeDao.save(employee);// 将我们编辑好的文本框里的内容通过th:value回显来进行保存
        return "redirect:/emps";// 还是回到显示页面
    }

    // 员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id)
    {
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
