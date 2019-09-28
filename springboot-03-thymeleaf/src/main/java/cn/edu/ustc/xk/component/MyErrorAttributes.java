package cn.edu.ustc.xk.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * Created by 徐科 on 2018/11/28.
 * 最终的效果是：响应是自适应的（MyExceptionHandler类），可以通过定制ErrorAttributes改变需要返回的内容（MyErrorAttributes类）
 */

//我们不想使用默认的DefaultErrorAttributes来进行输出数据处理，可以自定义
 @Component // 必须得添加到容器中才能使用我们自定义的ErrorAttributes
public class MyErrorAttributes extends DefaultErrorAttributes{

  // ctrl+o 重写父类的这个方法就行了

 @Override
 public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace)
 {
     // 这个返回的map就是页面和json能获取的所有字段，即包含我们自定义的异常信息（MyExceptionHandler里面的东西）
     Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);

     // 加上我们自定义的字段，改变默认行为
     map.put("company", "ustc");

     // 获取我们自定义的异常处理器携带的数据
     Map<String, Object> ext = (Map<String, Object>)webRequest.getAttribute("my_exception", 0);

     // 放到整个要返回的map中去
     map.put("ext", ext);
     return map;
 }
}
