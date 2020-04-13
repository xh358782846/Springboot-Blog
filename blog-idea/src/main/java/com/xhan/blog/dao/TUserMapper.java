package com.xhan.blog.dao;

import com.xhan.blog.pojo.TUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TUserMapper {

    TUser findByUsernameAndPassword(String username,String password);

    int deleteByPrimaryKey(Long id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);
}