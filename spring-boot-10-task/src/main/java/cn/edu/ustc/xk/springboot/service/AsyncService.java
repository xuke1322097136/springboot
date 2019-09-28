package cn.edu.ustc.xk.springboot.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-23
 * Time: 16:58
 */
@Service
public class AsyncService {

    @Async // 标上这个注解之后，hello方法从同步执行就变成了异步执行了，不用每次都等待3秒才能执行后面的输出了
    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据处理中.....................");
    }
}
