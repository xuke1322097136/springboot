package cn.edu.ustc.xk.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by 徐科 on 2018/11/29.
 */
// 注意是javax包下面的filter
public class MyFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    @Override
    public void destroy()
    {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        System.out.println("MyFilter 执行了............");
        chain.doFilter(request, response);
    }
}
