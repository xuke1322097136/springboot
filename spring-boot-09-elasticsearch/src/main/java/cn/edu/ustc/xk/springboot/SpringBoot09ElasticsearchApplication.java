package cn.edu.ustc.xk.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Springboot默认使用两种技术和ES交互：
 * 1. Jest(默认不生效，在JestAutoConfiguration类里面可以看到，要生效的话需要我们导入工具包io.searchbox.client.JestClient;)，使用JestClient来完成交互
 * 2. SpringData中的ElasticSearch（ES有可能版本不合适，即Springdata里面整合的ES和我们docker安装的ES版本不合适）
 *        具体的版本适配说明:https://github.com/spring-projects/spring-data-elasticsearch
 *        我们引用的spring-boot-starter-data-elasticsearch是2.1.1版本，里面整合的spring-data-elasticsearch版本是3.1.3,
 *        经过比对，我们在docker里需要下载的是6.2.2（docker hub里没找到对应版本）看左边我们默认导入的是6.4.3版本的ES，所以我们就下载该版本的额
 *         如果版本不适配，可以有两种办法：
 *         a. 升级Spring-boot版本；
 *         b. 重新安装对应版本的ES.【一般采用这种办法】
 *         docker run -d -e ES_JAVA_OPTS="-Xms256m -Xmx256m" -p 9200:9200 -p 9300:9300 --name ES ID值
 *         在这需要指定堆的大小，不然它会默认设置成2G，避免启动不了，咱们在这指定大小。
 *
 * SpringData中的ElasticSearch配置好的东西：
 *     1）ElasticsearchAutoConfiguration帮我们自动配置了以下内容：TransportClient，利用它我们需要配置ClusterNodes,ClusterName等信息；
 *     2）ElasticsearchDataAutoConfiguration自动配置了ElasticsearchTemplate来帮我们操作ES；
 *     3）ElasticsearchRepositoriesAutoConfiguration帮我们启用了ElasticsearchRepository这个接口，类似于JPA的那种编程方式，我们自己定义一个子接口repository来继承他，然后自定义方法来猜操作ES。
 *Jest操作：
 *    在这我们使用两种工具来操作ES（Jest和SpringData），首先我们需要去maven仓库中找到6.4.3版本的ES对应的dependency，
 *    接着我们需要配置一下uri,username,password等等属性，具体可以配置的属性可以在JestProperties中找到，接着我们可以去test里面测试了
 *
 *    具体的中文版ES官方文档的地址：https://www.elastic.co/guide/cn/index.html，具体的语法可以在这学习
 *
 *SpringData操作：
 *   在这又可以使用ElasticsearchRepository和ElasticsearchTemplate两种办法操作
 *   详细的文档在：https://github.com/spring-projects/spring-data-elasticsearch
 *   下面我们测试一下这两种办法：
 *   1） 编写一个ElasticsearchRepository的子接口BookRepository，所以BookRepository里面具有了ElasticsearchRepository所有的方法了
 *   2)  使用ElasticsearchTemplate就不测了，可以参考上面的官方文档
 *
 */
@SpringBootApplication
public class SpringBoot09ElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot09ElasticsearchApplication.class, args);
    }

}

