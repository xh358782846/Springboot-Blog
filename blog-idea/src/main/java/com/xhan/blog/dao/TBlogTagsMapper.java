package com.xhan.blog.dao;

import com.xhan.blog.pojo.TBlogTags;

public interface TBlogTagsMapper {
    int insert(TBlogTags record);

    int insertSelective(TBlogTags record);
}