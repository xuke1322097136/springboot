package cn.edu.ustc.xk.controller;

import cn.edu.ustc.xk.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 徐科 on 2018/11/28.
 */
// 在SpringMVC要成为一个异常处理器，则必须加上@ControllerAdvice注解
@ControllerAdvice
public class MyExceptionHandler
{
    /*
    // 方法一：这种方法的坏处就是：它没有自适应效果，是直接写死的，客户端和浏览器返回的都是json数据
    @ResponseBody // 在这用json数据返回,响应json数据
    @ExceptionHandler(UserNotExistException.class)// 在这也可以是Exception.class，就是处理所有的异常
    public Map<String, Object> handlerException(Exception e)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "user not exist");
        map.put("message", e.getMessage());

        return map;

    }
    */
    // 为什么可以转发到/error请求？这种写法的原理就是BasicErrorController类里面的具体实现了，针对不同的客户端请求返回不同的数据
    @ExceptionHandler(UserNotExistException.class)
    public String handlerException(Exception e, HttpServletRequest request)
    {
        Map<String, Object> map = new HashMap<>();

        /* 在这一定要记得传入我们的错误状态码，如4xx,5xx。不然都是200了
         该属性位于BasicErrorController类里面的errorHtml()方法里面的getStatus()方法里面的statusCode属性
         我们可以使用request域来设置自己定制的状态码

         Integer statusCode = (Integer) request
				.getAttribute("javax.servlet.error.status_code");
        */
       request.setAttribute("javax.servlet.error.status_code", 500);

        map.put("message", e.getMessage());// 取得是我们在UserNotExistException类中定义的异常消息

        /*
          如何将我们定制的信息携带出去？
          出现错误以后，会来到/error请求，会被BasicErrorController处理，响应出去可以获取的数据是由
            getErrorAttributes得到的（是AbstractErrorController（ErrorController）规定的方法）；
              方法1、完全来编写一个ErrorController的实现类【或者是编写AbstractErrorController的子类】，放在容器中；
              方法2、页面上能用的数据，或者是json返回能用的数据都是通过errorAttributes.getErrorAttributes得到；

            ErrorMvcAutoConfiguration的部分源码：
              @Bean
	          @ConditionalOnMissingBean(value = ErrorAttributes.class, search = SearchStrategy.CURRENT)
	           public DefaultErrorAttributes errorAttributes() {
		              return new DefaultErrorAttributes(
				      this.serverProperties.getError().isIncludeException());
	           }
              上面的源码可以看到：要输出哪些数据都是通过容器中的DefaultErrorAttributes.getErrorAttributes()来进行默认数据处理的；
                                所以我们可以自定义个MyErrorAttributes类来处理输出数据处理，
                                详见我们自己定义的MyErrorAttributes类
         */
        map.put("code", "user not exist");
        map.put("mess", "用户出错啦！");

        request.setAttribute("my_exception", map);// 将我们添加的异常信息加到request域中，便于后边我们自定的MyErrorAttributes来获取
        // 转发到/error请求
        return "forward:/error";

    }

}
