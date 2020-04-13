package com.xhan.blog.pojo;

import java.util.ArrayList;
import java.util.List;

public class TTag {
    private Long id;
    private String name;


    private  Integer tcount;

    private List<TBlog> blogs=new ArrayList<>();

    public TTag() {
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
        this.name = name;
    }

    public List<TBlog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<TBlog> blogs) {
        this.blogs = blogs;
    }

    public Integer getTcount() {
        return tcount;
    }

    public void setTcount(Integer tcount) {
        this.tcount = tcount;
    }

    @Override
    public String toString() {
        return "TTag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tcount='" + tcount + '\'' +
                ", blogs=" + blogs +
                '}';
    }
}