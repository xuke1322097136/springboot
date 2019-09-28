package cn.edu.ustc.xk.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用docker安装zookeeper: docker run --name zookeeper -p 2181:2181 --restart always -d ID
 *
 * 整个项目的创建过程：先创建一个空的project，接着创建一个provider-ticket的moudle和一个consumer-user的moudle
 * 1. 将服务提供者注册到注册中心
 *      a.) 引入dobbo和springboot的整合包以及zkclient的相关依赖包；
 *      b.) 配置dobbo的扫描包和注册中心地址；
 *      c.) 使用@Service发布服务出去。
 *
 *   由于UserService里面要用TicketService，所以我们需要在UserSrevice里面要放一份一模一样的TicketService，而且是全限定名必须一样
 */
@SpringBootApplication
public class ProviderTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderTicketApplication.class, args);
    }

}

