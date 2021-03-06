package com.xhan.blog.interceptor;


import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (request.getRequestURI().equals("/admin/register")){
            return true;
        }else if (request.getSession().getAttribute("user")==null){
            System.out.println(request.getRequestURI());
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
