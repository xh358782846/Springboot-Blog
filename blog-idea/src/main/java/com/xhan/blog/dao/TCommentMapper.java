package com.xhan.blog.dao;

import com.xhan.blog.pojo.TComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TComment record);

    int insertSelective(TComment record);

    TComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TComment record);

    int updateByPrimaryKey(TComment record);

    List<TComment> getCommentListBybBlogId(Long bid);

    TComment findByParentCommentId(Long id);
//    TComment saveComment(TComment comment);
}