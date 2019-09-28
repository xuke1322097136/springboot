package cn.edu.ustc.xk.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 徐科 on 2018/11/21.
 * 拦截器检查，自己写的拦截器必须是实现HandlerInterceptor接口的
 * 目标是：没有登录成功的人不能访问后台页面和进行CRUD
 * 在这需要检查登录状态，我们可以定义一个HttpSession
 *
 * 最后记得将自己写的拦截器定制和扩展到扩展类MyMvcConfig中去
 */
public class LoginHandlerInterceptor implements HandlerInterceptor
{
    // 目标方法执行之前，我们可以来一个预检查
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        Object user = request.getSession().getAttribute("loginUser");
        if(null == user)
        {
            request.setAttribute("msg", "没有权限请先登录");
            // 未登录，返回登录页面。我们这里转发到/index.html请求，之前的扩展类MyMvcConfig
            // 会经过转发请求，模板引擎会解析到login.html
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        }else
        {
            //已登录，放行
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {

    }
}
