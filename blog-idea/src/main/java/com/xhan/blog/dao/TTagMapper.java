package com.xhan.blog.dao;

import com.xhan.blog.pojo.TTag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TTagMapper {

    List<TTag> getAllType();

    List<TTag> getIndexBlogTag();

    List<TTag> getBlogTag();

    int deleteByPrimaryKey(Long id);

    int insert(TTag record);

    int insertSelective(TTag record);

    TTag selectByPrimaryKey(Long id);

    TTag selectByName(String name);

    int updateByPrimaryKeySelective(TTag record);

    int updateByPrimaryKey(TTag record);
}