package com.xhan.blog.dao;

import com.xhan.blog.pojo.HibernateSequence;

public interface HibernateSequenceMapper {
    int insert(HibernateSequence record);

    int insertSelective(HibernateSequence record);
}