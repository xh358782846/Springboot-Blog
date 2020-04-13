package com.xhan.blog.dao;

import com.xhan.blog.pojo.TBlog;
import com.xhan.blog.pojo.TBlogTags;
import com.xhan.blog.pojo.TTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Mapper
@Repository
public interface TBlogMapper {

    List<TBlog> getAllBlog();

    List<TBlog> getIndexBlog();

    List<TBlog> getBlogByTypeId(Long typeId);

    List<TBlog> getBlogByTagId(Long tagId);

    List<TBlog> getListBold(TBlog blog);

    List<TBlog> searchAllBlog(TBlog tBlog);

    List<TBlog> getAllRecommendBlog();

    int saveBlogAndTag(TBlogTags blogTags);

    int deleteBlogAndTag(Long id);

    int deleteByPrimaryKey(Long id);

    int insert(TBlog record);

    int insertSelective(TBlog record);

    TBlog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TBlog record);

    int updateByPrimaryKey(TBlog record);

    List<String> getBlogYear();

    List<TBlog> getBlogByYear( String year);

    List<TBlog> getSearchBlog(String query);

    TBlog getDetailedBlog(Long id);

    void updateView(TBlog blog);
}