package cn.edu.ustc.xk.component;

//import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import org.thymeleaf.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by 徐科 on 2018/11/18.
 * <p>
 * 实现点击链接切换国际化，即页面上的中文/英文来切换国际化
 */
public class MyLocaleResolver implements LocaleResolver
{
    @Override
    public Locale resolveLocale(HttpServletRequest request)
    {
        //System.out.println("..................resolveLocale执行了..................");
        // 得到请求参数里面的i18n_language的值
        String i18n_language = request.getParameter("i18n_language");
        System.out.println("...................." + i18n_language);
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(i18n_language))
        {
            String[] split = i18n_language.split("_");
            locale = new Locale(split[0], split[1]);
        }
        return locale;
    }


    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale)
    {

    }
}

