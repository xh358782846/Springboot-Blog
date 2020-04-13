package com.xhan.blog.service;

import com.xhan.blog.pojo.TBlog;

import java.util.List;
import java.util.Map;

public interface BlogService {

    TBlog getBlog(Long id);

    List<TBlog> getAllBlog();

    List<TBlog> getIndexBlog();

    List<TBlog> getBlogByTypeId(Long typeId);  //根据类型id获取博客

    List<TBlog> getBlogByTagId(Long tagId);

    List<TBlog> getListBold(TBlog blog);

    List<TBlog> searchAllBlog(TBlog tBlog);

    List<TBlog> getAllRecommendBlog();

    int saveBlog(TBlog blog);

    int updateByPrimaryKeySelective(TBlog tBlog);

    int deleteBlog(Long id);

    List<TBlog> getSearchBlog(String query);

    TBlog getDetailedBlog(Long id);

    Map<String,List<TBlog>> archiveBlog();  //归档博客

    int BlogCount();

    void updateView(TBlog blog);
}
