package com.xhan.blog.service.impl;

import com.xhan.blog.dao.TUserMapper;
import com.xhan.blog.pojo.TUser;
import com.xhan.blog.service.UserService;
import com.xhan.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import org.springframework.util.DigestUtils;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TUserMapper tUserMapper;

    @Override
    public int insert(TUser record) {

        record.setPassword(MD5Utils.Code(record.getPassword()));
        record.setCreateTime(new Date());
        record.setType(1);
        record.setAvatar("/images/logo.jpg");
        return tUserMapper.insert(record);

    }
    @Override
    public TUser checkUser(String username, String password) {
        TUser tUser=tUserMapper.findByUsernameAndPassword(username,MD5Utils.Code(password));
        return tUser;
    }
}
