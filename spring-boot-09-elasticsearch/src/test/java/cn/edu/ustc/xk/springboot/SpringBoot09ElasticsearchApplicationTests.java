package cn.edu.ustc.xk.springboot;

import cn.edu.ustc.xk.springboot.bean.Article;
import cn.edu.ustc.xk.springboot.bean.Book;
import cn.edu.ustc.xk.springboot.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot09ElasticsearchApplicationTests {

    @Autowired
    JestClient jestClient; // 具体的Jest的所有操作可以在github下面都可以找到

    @Autowired
    BookRepository bookRepository;//使用这种办法操作，一定要在文档(即Book对象)上标注这个类属于哪个索引哪个类型上，详见Book类

    @Test
    public void test02(){
        /*
        Book book  = new Book();
        book.setId(1);
        book.setAuthor("吴承恩");
        book.setBookName("西游记");
        // 当我们在地址栏输入：localhost:9200/xuke/hello/_search就能看到所有存储在xuke索引下，类型为book的所有文档
        bookRepository.index(book);
        */

//        在这演示根据书名查询书籍
        for (Book book : bookRepository.findBooksByBookNameLike("游")) {
            System.out.println(book);
        }

    }

    @Test
    public void contextLoads() {

        // 1. 给ES中索引保存一个文档
        Article article = new Article();
        article.setId(1);
        article.setAuthor("zhangsan");
        article.setTitle("这是标题");
        article.setContent("这是内容");

        // 构建一个索引功能
        // 给ES中保存，保存的是啥？放到Builder里面，具体保存到哪？需要我们指定索引位置和类型的位置
        Index index = new Index.Builder(article).index("xuke").type("hello").build();

        try {
            // 执行文档
            //当我们在地址栏输入：http://localhost:9200/xuke/hello/1  在_source里面就能看到我们保存的结果
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 测试Jest的搜索功能
    //这段json串取自：https://www.elastic.co/guide/cn/elasticsearch/guide/current/_search_with_query_dsl.html
    @Test
    public void search() {
        // 查询表达式的含义是：查询content里面匹配这字的文档
        String json = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"content\" : \"这\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        // 构建搜索功能
        Search search = new Search.Builder(json).addIndex("xuke").addType("hello").build();

        // ctrl+alt+t：快速补充try-catch块
        // 执行
        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

