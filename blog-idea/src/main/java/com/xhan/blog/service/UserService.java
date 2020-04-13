package com.xhan.blog.service;

import com.xhan.blog.pojo.TUser;

public interface UserService {

    TUser checkUser(String username,String password);

}
