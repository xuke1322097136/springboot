package cn.edu.ustc.xk.springboot.repository;

import cn.edu.ustc.xk.springboot.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-23
 * Time: 16:26
 */
// 第一个参数是我们要操作的哪个bean，第二个参数是该bean的主键
//   ctrl+fn+f12: BookRepository里面所有进行CRUD能用的方法都能找到，也可以自定义方法，但是有命名规则
//    方法的定义规则：https://docs.spring.io/spring-data/elasticsearch/docs/3.1.3.RELEASE/reference/html/#repositories.query-methods.details
public interface BookRepository extends ElasticsearchRepository<Book, Integer> {

    public List<Book> findBooksByBookNameLike(String bookName);
}
