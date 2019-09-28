package cn.edu.ustc.xk.springboot.bean;

import io.searchbox.annotations.JestId;

/**
 * Created by xuke
 * Description:
 * Date: 2018-12-23
 * Time: 15:46
 */
public class Article {

    @JestId // 标注Id字段时候主键
    private Integer id;
    private String author;
    private String title;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
