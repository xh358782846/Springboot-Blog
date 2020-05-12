package com.xhan.blog.service;

import com.xhan.blog.pojo.TUser;

public interface UserService {
    
    int insert(TUser record);

    TUser checkUser(String username,String password);

}
