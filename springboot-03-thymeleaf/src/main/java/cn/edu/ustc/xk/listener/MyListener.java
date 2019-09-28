package cn.edu.ustc.xk.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by 徐科 on 2018/11/29.
 */
public class MyListener implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        System.out.println("contextInitialized起作用啦");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        System.out.println("contextDestroyed起作用啦");
    }
}
