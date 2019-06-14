package com.unifs.behavioranalysis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @创建人 张恭雨
 * @创建时间 2018/11/8
 * @描述：cookie操作工具类
 */
public class CookieUtil {
    private static Logger log=LoggerFactory.getLogger(CookieUtil.class);
    /*向客户端发送cookie*/
    public static void sendCookie(HttpServletResponse response, String name, String value){
        Cookie cookie=new Cookie(name.trim(),value.trim());
        /*设置cookie失效时间为10分钟*/
        cookie.setMaxAge(60*10);
        cookie.setPath("/");
        response.addCookie(cookie);
        log.info("name:"+name+"  value:"+value+"  cookie发送成功");
    }

    public static String getCookie(HttpServletRequest request, String name){
        Cookie[] cookies=request.getCookies();
        if(cookies==null||cookies.length==0){
            return null;
        }
        for (Cookie cookie:cookies){
            if(name.equals(cookie.getName())||name==cookie.getName()){
                return cookie.getValue();
            }
        }
        return null;
    }
}
