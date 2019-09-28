package cn.edu.ustc.xk.springboot.bean;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-23
 * Time: 16:27
 */
//使用BookRepository操作时，一定要在文档(即Book对象)上标注这个类属于哪个索引哪个类型上
@Document(indexName = "xuke", type = "hello")
public class Book {

    private Integer id;
    private String bookName;
    private String author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
