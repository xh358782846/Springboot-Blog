package com.xhan.blog.service;


import com.xhan.blog.pojo.TType;
import java.util.List;


public interface TypeService {

    TType getTypeByName(String name);

    int saveType(TType type);

    TType getType(Long id);

    List<TType> getAllType();

    List<TType> getIndexBlogType();


    List<TType> getBlogType();

    int updateType(TType type);

    int updateByPrimaryKeySelective(TType type);

    int deleteType(Long id);
}
