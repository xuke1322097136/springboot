package cn.edu.ustc.xk.config;

import cn.edu.ustc.xk.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Created by 徐科 on 2018/11/17.
 */
//
/**使用WebMvcConfigurer来扩展SpringMVC的功能
 *
 * WebMvcAutoConfiguration是SpringMVC的自动配置类,它上面有一个很重要的注解：@Import(EnableWebMvcConfiguration.class)
 *
 * EnableWebMvcConfiguration的父类DelegatingWebMvcConfiguration中的一段源码：
 *
 * private final WebMvcConfigurerComposite configurers = new WebMvcConfigurerComposite();
 * 一旦被@Autowired标注，表示的就是方法中的参数（即所有的WebMvcConfigurer）需要从容器中自动注入
 *   @Autowired(required = false)
      public void setConfigurers(List<WebMvcConfigurer> configurers) {
       if (!CollectionUtils.isEmpty(configurers)) {
       this.configurers.addWebMvcConfigurers(configurers);

  查看里面的addViewControllers方法，他调用的都是上面的configures（即WebMvcConfigurerComposite类型）里面的addViewControllers方法
    @Override
     protected void addViewControllers(ViewControllerRegistry registry) {
         this.configurers.addViewControllers(registry);
     }

    点到WebMvcConfigurerComposite中来看看，
    它的作用是：将容器中所有的WebMvcConfigurer相关配置都来一起调用（包括我们自己写的配置类）；
               所以我们可以实现自己的WebMvcConfigurer，最终也会被调用和起作用的

    @Override
     public void addViewControllers(ViewControllerRegistry registry) {
        for (WebMvcConfigurer delegate : this.delegates) {
             delegate.addViewControllers(registry);
        }
    }

 */
// @EnableWebMvc表示全面接管SpringMVC,也就是说所有的SpringMVC的自动配置都失效了。不推荐使用！
// @ResponseBody
@Configuration
public class MyMvcConfig implements WebMvcConfigurer
{

    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        // 浏览器发送extendsSpringMVC请求，经过模板引擎解析到达success.html页面
        registry.addViewController("/extendsSpringMVC").setViewName("success");

    }
       //所有的WebMvcConfigurer都会起作用
       //一定注意：将组件注册到容器中去，不然得到的将会是404
       @Bean
       public WebMvcConfigurer webMvcConfigurer()
       {
           WebMvcConfigurer mvcConfigurer = new WebMvcConfigurer()
           {
               //快捷键ctrl+o提示我们实现哪个方法
               @Override
               public void addViewControllers(ViewControllerRegistry registry)
               {
                   //表示访问当前项目的话将会跳到index页面
                   registry.addViewController("/").setViewName("login");
                   registry.addViewController("/index.html").setViewName("login");
                   // main.html请求将会转发到dashboard页面，经过模板引擎渲染进行显示
                   registry.addViewController("/main.html").setViewName("dashboard");
               }

               // ctrl+o打开可以重写的方法列表，注册自己定制的拦截器
//               @Override
//               public void addInterceptors(InterceptorRegistry registry)
//               {
//                   // addPathPatterns在这避免拦截的路径太多，在这用"/**"（表示任意多层路径下的多个请求）来避免一个一个的写
//                   // excludePathPatterns在这把去登录页面的过滤掉(/index.html,/)，登录请求也不能拦(/user/login)，不然登录都登陆不了
//                   // 静态资源：如*.css,*.js，其实springboot已经做好了静态资源映射，我们不用自己处理也能访问
//                   registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                           .excludePathPatterns("/index.html", "/", "/user/login");
//               }
          };
           return mvcConfigurer;
       }

    //使用自定义的LocaleResolver来替换掉默认的LocaleResolver
    //国际化的功能还找不出来原因是啥？？？自定义的resolveLocale()方法就没执行。
    //但是另外一个demo项目中的却能执行，莫名其妙！！！
    @Bean
    public LocaleResolver localResolver()
    {
       return new MyLocaleResolver();
    }
}
