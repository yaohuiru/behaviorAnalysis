package com.unifs.behavioranalysis.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version V1.0
 * @title: LoginInterceptor
 * @projectName behavioranalysis
 * @description: TODO
 * @author： 张恭雨
 * @date 2019/6/14 15:06
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("LOGIN_USER");
        System.out.println("into LoginHandlerInterceptor... " + user);
        System.out.println(request.getRequestURI());
        if (user == null) {
            //未登陆，返回登陆页面
            request.setAttribute("message","您没有权限访问，请先登陆！");
            request.getRequestDispatcher("/login").forward(request,response);
            return false;
        } else {
            return true;
        }
    }
}
