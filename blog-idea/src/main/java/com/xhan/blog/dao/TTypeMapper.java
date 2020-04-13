package com.xhan.blog.dao;

import com.xhan.blog.pojo.TType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TTypeMapper {

    List<TType> getAllType();

    List<TType> getAdminType();

    List<TType> getIndexBlogType();

    List<TType> getBlogType();

    int deleteByPrimaryKey(Long id);

    int insert(TType record);

    int insertSelective(TType record);

    TType selectByPrimaryKey(Long id);

    TType selectByName(String name);

    int updateByPrimaryKeySelective(TType record);

    int updateByPrimaryKey(TType record);
}