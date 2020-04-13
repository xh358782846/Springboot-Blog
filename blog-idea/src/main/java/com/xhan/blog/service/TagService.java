package com.xhan.blog.service;

import com.xhan.blog.pojo.TTag;

import java.util.List;

public interface TagService {

    TTag getTagByName(String name);

    int saveTag(TTag tag);

    TTag getTag(Long id);

    List<TTag> getAllTag();

    List<TTag> getTagByString(String text);

    List<TTag> getIndexBlogTag();


    List<TTag> getBlogTag();

    int updateType(TTag tag);

    int updateByPrimaryKeySelective(TTag tag);

    int deleteTag(Long id);
}
