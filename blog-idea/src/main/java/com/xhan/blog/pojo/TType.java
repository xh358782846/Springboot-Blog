package com.xhan.blog.pojo;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class TType {


    private Long id;

    @NotBlank(message = "分类名称不能为空")
    private String name;

    private List<TBlog> blogs=new ArrayList<>();

    public TType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public List<TBlog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<TBlog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "TType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blogs=" + blogs +
                '}';
    }
}