package cn.edu.ustc.xk.springboot.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-23
 * Time: 17:09
 */
@Service
public class ScheduledService {

    /*
          second（秒）, minute（分）, hour（时）, day of month（天）, month（月）， day of week（周）
	 *          0 * * * * MON-FRI：利用空格分割，0表示的整数的意思，周一到周五的整秒启动，即周一到周五的每一分钟都启动一次，
	 *          0 0/5 14,18 * * ? ：每14点整和18点整，每隔5分钟执行一次
	 *          0 15 10 ? * 1-6 ：每个月的周一至周六的10：15分执行一次
	 *          0 0 2 ? * 6L ： 每个月的最后一个周六凌晨2点执行一次
	 *          0 0 2 LW * ? ： 每个月的最后一个工作日凌晨2点执行一次
	 *          0 0 2-4 ? * 1#1 ：每个月的第一个周一凌晨2点到4点期间，每个整点都执行一次
	 * */
    @Scheduled(cron = "0 * * * * 0") // 和异步任务一样，必须在主配之类里面开启定时任务，整分钟的时候在控制台可以看到输出结果
    public void scheduled(){
        System.out.println("scheduled...............");
    }
}
