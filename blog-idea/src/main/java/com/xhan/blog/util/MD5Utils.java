package com.xhan.blog.util;
import org.springframework.util.DigestUtils;

public class MD5Utils {

    public static String Code(String str){
        //比如对密码进行 md5 加密
        String md5Pass = DigestUtils.md5DigestAsHex(str.getBytes());
        return md5Pass;
    }

//    public static void main(String[] args) {
//
//        System.out.println(Code("123456."));
//    }
}
